package co.vasquez.nodier.sanduchero.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sanduches extends Ingredientes implements Serializable  {

    private String nombre;
    private int precio;
    private List<HashMap<String, Object>> ingrediente;
    //private List<Ingredientes>
    //private String ingrediente;
    private String imagen;
    private String categoria;

    public Sanduches() {
    }


    public Sanduches(String nombre, int precio, List<HashMap<String, Object>> ingrediente, String imagen, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingrediente = ingrediente;
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

    public List<HashMap<String, Object>> getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(List<HashMap<String, Object>> ingrediente) {
        this.ingrediente = ingrediente;
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
