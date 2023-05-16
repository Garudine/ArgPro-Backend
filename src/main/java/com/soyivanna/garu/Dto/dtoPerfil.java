package com.soyivanna.garu.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPerfil {
    @NotBlank
    private String imgPerfil;
    
    ///CONSTRUCTORES

    public dtoPerfil() {
    }

    public dtoPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
    
    ///GETTERS Y SETTERS

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
    
    
}
