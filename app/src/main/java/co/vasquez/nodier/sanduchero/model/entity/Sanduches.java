package co.vasquez.nodier.sanduchero.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sanduches implements Serializable  {

    private String nombre;
    private int precio;
    private List<String> id_ingredientes;
    //private String ingrediente;
    private String imagen;
    private String categoria;

    public Sanduches() {
    }


    /*public Sanduches(String nombre, int precio, List<HashMap<String, Object>> ingrediente, String imagen, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingrediente = ingrediente;
        this.imagen = imagen;
        this.categoria = categoria;
    }*/

    public Sanduches(String nombre, int precio, List<String> id_ingredientes, String imagen, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.id_ingredientes = id_ingredientes;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    /*public Sanduches(String nombre, int precio, String ingrediente, String imagen, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingrediente = ingrediente;
        this.imagen = imagen;
        this.categoria = categoria;
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<String> getId_ingredientes() {
        return id_ingredientes;
    }

    public void setId_ingredientes(List<String> id_ingredientes) {
        this.id_ingredientes = id_ingredientes;
    }

    /*public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }*/

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



}
