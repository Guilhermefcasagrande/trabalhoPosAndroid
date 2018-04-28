package br.edu.unidavi.unclewily.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Guilherme on 11/04/2018.
 */

public class User {

    @Getter
    @Setter
    private int codigo;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String profile_img_url;

    @Getter
    @Setter
    private String token;
}
