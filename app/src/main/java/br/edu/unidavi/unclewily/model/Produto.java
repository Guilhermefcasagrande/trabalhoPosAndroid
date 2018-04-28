package br.edu.unidavi.unclewily.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Craitson on 28/04/2018.
 */

public class Produto {

    @Setter
    @Getter
    private int cod;

    @Setter
    @Getter
    private String nome;

    @Setter
    @Getter
    private String descricao;

    @Setter
    @Getter
    private int avaliacao;

    @Setter
    @Getter
    private int disponibilidade;

}
