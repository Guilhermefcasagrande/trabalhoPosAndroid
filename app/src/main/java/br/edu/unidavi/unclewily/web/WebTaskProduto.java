package br.edu.unidavi.unclewily.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.model.Produto;

public class WebTaskProduto extends WebTaskBase{
    private static final String SERVICE_NAME = "/produtos";
    private String token;

    public WebTaskProduto(Context context, String token) {
        super(context, SERVICE_NAME);
        this.token = token;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> myMap = new HashMap<>();
        myMap.put("token", token);

        JSONObject requestJson = new JSONObject(myMap);

        return requestJson.toString();
    }

    @Override
    public void handleResponse(String response) {

        List<Produto> produtoList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject produtoJSON = (JSONObject)
                        jsonArray.get(index);
                Produto meuProduto = new Produto();
                meuProduto.setCodigo(produtoJSON.getInt("codigo"));
                meuProduto.setNome(produtoJSON.getString("nome"));
                meuProduto.setDescricao(produtoJSON.getString("descricao"));
                meuProduto.setPhotoUrl(produtoJSON.getString("imagem"));
                meuProduto.setAvaliacao(produtoJSON.getInt("avaliacao"));
                meuProduto.setDisponibilidade(produtoJSON.getInt("disponibilidade"));
                produtoList.add(meuProduto);
            }

            EventBus.getDefault().post(produtoList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }
}
