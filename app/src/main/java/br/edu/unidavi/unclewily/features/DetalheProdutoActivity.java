package br.edu.unidavi.unclewily.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.model.Produto;

public class DetalheProdutoActivity extends AppCompatActivity {

    private TextView textNome;
    private TextView textDescricao;
    private TextView textValor;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);

        textNome = findViewById(R.id.label_name);
        textDescricao = findViewById(R.id.label_descricao);
        textValor = findViewById(R.id.label_preco);
        imageView = findViewById(R.id.img_cell);


        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(Produto produto){

        textNome.setText(produto.getNome());
        textDescricao.setText(produto.getDescricao());
        textValor.setText(produto.getNome());

        Picasso.with(this).load(produto.getPhotoUrl()).into(imageView);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
