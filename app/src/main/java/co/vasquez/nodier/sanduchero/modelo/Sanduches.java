package co.vasquez.nodier.sanduchero.modelo;

public class Sanduches {

    private int idSan;
    private String nombreSan;
    private int precioSan;
    private int cantidadSan;
    private String ingredienteSan;
    private String imagenSan;
    private String categoriaSan;

    public Sanduches(int idSan, String nombreSan, int precioSan, int cantidadSan, String ingredienteSan, String imagenSan, String categoriaSan) {
        this.idSan = 0;
        this.nombreSan = nombreSan;
        this.precioSan = precioSan;
        this.cantidadSan = cantidadSan;
        this.ingredienteSan = ingredienteSan;
        this.imagenSan = imagenSan;
        this.categoriaSan = categoriaSan;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public String getNombreSan() {
        return nombreSan;
    }

    public void setNombreSan(String nombreSan) {
        this.nombreSan = nombreSan;
    }

    public int getPrecioSan() {
        return precioSan;
    }

    public void setPrecioSan(int precioSan) {
        this.precioSan = precioSan;
    }

    public int getCantidadSan() {
        return cantidadSan;
    }

    public void setCantidadSan(int cantidadSan) {
        this.cantidadSan = cantidadSan;
    }

    public String getIngredienteSan() {
        return ingredienteSan;
    }

    public void setIngredienteSan(String ingredienteSan) {
        this.ingredienteSan = ingredienteSan;
    }

    public String getImagenSan() {
        return imagenSan;
    }

    public void setImagenSan(String imagenSan) {
        this.imagenSan = imagenSan;
    }

    public String getCategoriaSan() {
        return categoriaSan;
    }

    public void setCategoriaSan(String categoriaSan) {
        this.categoriaSan = categoriaSan;
    }
}
