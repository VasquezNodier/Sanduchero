package co.vasquez.nodier.sanduchero.model.entity;

import java.io.Serializable;

public class Direcciones implements Serializable {
    private String direccion;
    private String ciudad;
    private String pais;
    private String postal;

    public Direcciones(String direccion, String ciudad, String pais, String postal) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.postal = postal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Override
    public String toString() {
        return direccion + ", " + ciudad + ", " + pais;
    }
}