package br.edu.unidavi.unclewily.features;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.unclewily.R;

class ProdutoViewHolder extends RecyclerView.ViewHolder{

    ImageView imagem;
    TextView labelNome;
    TextView labelDescricao;
    TextView labelAvaiacao;
    TextView labelDisponobilidade;

    public ProdutoViewHolder(View itemView){
        super(itemView);

        imagem = itemView.findViewById(R.id.img_cell);
        labelNome = itemView.findViewById(R.id.label_name);
        labelDescricao = itemView.findViewById(R.id.label_descricao);
        labelAvaiacao = itemView.findViewById(R.id.label_avaliacao);
        labelDisponobilidade = itemView.findViewById(R.id.label_disponibilidade);
    }
}
