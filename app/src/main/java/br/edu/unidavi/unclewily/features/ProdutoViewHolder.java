package br.edu.unidavi.unclewily.features;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.unclewily.R;

class ProdutoViewHolder extends RecyclerView.ViewHolder{

    ImageView imagem;
    TextView labelNome;
    TextView labelPreco;

    public ProdutoViewHolder(View itemView){
        super(itemView);

        imagem = itemView.findViewById(R.id.img_cell);
        labelNome = itemView.findViewById(R.id.label_name);
        labelPreco = itemView.findViewById(R.id.label_preco);
    }
}
