package br.edu.unidavi.unclewily.features;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.data.Session;
import br.edu.unidavi.unclewily.model.Produto;
import br.edu.unidavi.unclewily.web.WebTaskProduto;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProdutoAdapter adapter;

    public ListFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_produtos);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new ProdutoAdapter(new ArrayList<Produto>(),getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Subscribe
    public void onEvent(List<Produto> produtoList){

        if(produtoList.size() == 0){
            getView().findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.recycler_produtos).setVisibility(View.GONE);
        }else{
            getView().findViewById(R.id.recycler_produtos).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapter.produtoList = produtoList;
            adapter.notifyDataSetChanged();
        }

    }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("LIFECYCLE", "START");

        Session mySession = new Session(getActivity());
        WebTaskProduto taskMemes = new WebTaskProduto(getActivity(),
                mySession.getTokenInSession());
        taskMemes.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.d("LIFECYCLE", "STOP");
    }



    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(getView(), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

}
