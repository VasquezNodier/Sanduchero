package co.vasquez.nodier.sanduchero.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Sanduches {
    @PrimaryKey (autoGenerate = true)
    private int idSan;
    private String nombreSan;
    private int precioSan;
    private int cantidadSan;
    private List<Ingredientes> ingredientes;
    private String imagenSan;
    private String categoriaSan;

    public Sanduches(String nombreSan, int precioSan, int cantidadSan,
                     List<Ingredientes> ingredientes, String imagenSan, String categoriaSan) {
        this.nombreSan = nombreSan;
        this.precioSan = precioSan;
        this.cantidadSan = cantidadSan;
        this.ingredientes = ingredientes;
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

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
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
