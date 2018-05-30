package br.edu.unidavi.unclewily.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.data.ProdutoDAO;
import br.edu.unidavi.unclewily.model.Produto;

public class WebTaskProduto extends WebTaskBase {
    private static final String SERVICE_NAME = "/produtos";
    private String token;
    private ProdutoDAO produtoDAO;

    public WebTaskProduto(Context context, String token) {
        super(context, SERVICE_NAME);
        this.token = token;
    }

    @Override
    public String getRequestBody() {
        Map<String, String> myMap = new HashMap<>();
        myMap.put("token", token);

        JSONObject requestJson = new JSONObject(myMap);

        return requestJson.toString();
    }

    @Override
    public void handleResponse(String response) {

        List<Produto> produtoList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int index = 0; index < jsonArray.length(); index++) {
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

                //insere no DB
                produtoDAO = new ProdutoDAO(getContext());
                produtoDAO.insertProduto(meuProduto);
            }

            EventBus.getDefault().post(produtoList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }


    public String tsqt = "[\n" +
            "\t{\n" +
            "\t\t\"codigo\": 1,\n" +
            "\t\t\"nome\": \"Bolinho\",\n" +
            "\t\t\"preco\": \"3.50\",\n" +
            "\t\t\"disponibilidade\": 1,\n" +
            "\t\t\"descricao\": \"Ótimo bolinho de carne\",\n" +
            "\t\t\"avaliacao\": 8,\n" +
            "\t\t\"imagem\" : \"https://upload.wikimedia.org/wikipedia/commons/2/2e/Fast_food_meal.jpg\"\n" +
            "\t}, \n" +
            "\t{\n" +
            "\t\t\"codigo\": 2,\n" +
            "\t\t\"nome\": \"Pastel frito\",\n" +
            "\t\t\"preco\": \"2.50\",\n" +
            "\t\t\"disponibilidade\": 1,\n" +
            "\t\t\"descricao\": \"Ótimo pastel frito\",\n" +
            "\t\t\"avaliacao\": 5,\n" +
            "\t\t\"imagem\" : \"https://upload.wikimedia.org/wikipedia/commons/2/2e/Fast_food_meal.jpg\"\n" +
            "\t},\n" +
            "]";
}
