package br.edu.unidavi.unclewily.features;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {

    List<Produto> produtoList;
    Context contexto;

    public ProdutoAdapter( List<Produto> produtoList, Context contexto){
        this.produtoList = produtoList;
        this.contexto = contexto;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_produtos, parent, false);
        ProdutoViewHolder myViewHolder = new ProdutoViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProdutoViewHolder holder, int position) {
        final Produto myProduto = produtoList.get(position);

        holder.labelNome.setText(myProduto.getNome());
        holder.labelPreco.setText(myProduto.getDescricao());

        Picasso.with(contexto).load(myProduto.getPhotoUrl()).into(holder.imagem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(myProduto);
                Intent intent = new Intent(holder.itemView.getContext(), DetalheProdutoActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }
}
