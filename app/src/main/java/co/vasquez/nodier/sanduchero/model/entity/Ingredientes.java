package co.vasquez.nodier.sanduchero.model.entity;


public class Ingredientes {

    private String nombre;
    private int precio;
    private String Imagen;
    private String categoria;

    public Ingredientes() {
    }

    public Ingredientes(String nombre) {
        this.nombre = nombre;
    }

    public Ingredientes(String nombre, int precio, String Imagen, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.Imagen = Imagen;
        this.categoria = categoria;
    }

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

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
