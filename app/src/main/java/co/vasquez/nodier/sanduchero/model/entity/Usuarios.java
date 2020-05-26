package co.vasquez.nodier.sanduchero.model.entity;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String nombre;
    private String correo;
    private String contra;
    private String tipo;

    public Usuarios() {
    }

    public Usuarios(String nombre, String correo, String contra, String tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                ", nombreUsu='" + nombre + '\'' +
                ", correoUsu='" + correo + '\'' +
                ", contraUsu='" + contra + '\'' +
                '}';
    }


}


