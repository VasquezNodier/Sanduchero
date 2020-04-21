package co.vasquez.nodier.sanduchero.modelo;

public class Ingredientes {

    private int idInd;
    private String nombreIng;
    private String categoriaIng;
    private String fotoIng;

    public Ingredientes(int idInd, String nombreIng, String categoriaIng, String fotoIng) {
        this.idInd = 0;
        this.nombreIng = nombreIng;
        this.categoriaIng = categoriaIng;
        this.fotoIng = fotoIng;
    }

    public int getIdInd() {
        return idInd;
    }

    public void setIdInd(int idInd) {
        this.idInd = idInd;
    }

    public String getNombreIng() {
        return nombreIng;
    }

    public void setNombreIng(String nombreIng) {
        this.nombreIng = nombreIng;
    }

    public String getCategoriaIng() {
        return categoriaIng;
    }

    public void setCategoriaIng(String categoriaIng) {
        this.categoriaIng = categoriaIng;
    }

    public String getFotoIng() {
        return fotoIng;
    }

    public void setFotoIng(String fotoIng) {
        this.fotoIng = fotoIng;
    }
}
