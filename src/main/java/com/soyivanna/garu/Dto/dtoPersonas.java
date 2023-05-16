package com.soyivanna.garu.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPersonas {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String puesto;
    @NotBlank
    private String imgBanner;
    
    // CONSTRUCTORES

    public dtoPersonas() {
    }

    public dtoPersonas(String nombre, String apellido, String puesto, String imgBanner) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.imgBanner = imgBanner;
    }
    
    // GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner;
    }


    
}
