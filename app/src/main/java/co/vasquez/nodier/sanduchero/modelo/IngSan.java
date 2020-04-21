package co.vasquez.nodier.sanduchero.modelo;

public class IngSan {

    private int idIS;
    private int SanIS;
    private int IngIs;
    private int precio;
    private int cantIS;

    public IngSan(int idIS, int sanIS, int ingIs, int precio, int cantIS) {
        this.idIS = 0;
        SanIS = sanIS;
        IngIs = ingIs;
        this.precio = precio;
        this.cantIS = cantIS;
    }

    public int getIdIS() {
        return idIS;
    }

    public void setIdIS(int idIS) {
        this.idIS = idIS;
    }

    public int getSanIS() {
        return SanIS;
    }

    public void setSanIS(int sanIS) {
        SanIS = sanIS;
    }

    public int getIngIs() {
        return IngIs;
    }

    public void setIngIs(int ingIs) {
        IngIs = ingIs;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantIS() {
        return cantIS;
    }

    public void setCantIS(int cantIS) {
        this.cantIS = cantIS;
    }
}
