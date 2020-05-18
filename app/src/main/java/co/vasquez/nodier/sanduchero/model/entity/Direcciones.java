package co.vasquez.nodier.sanduchero.model.entity;



public class Direcciones {
    private int idDir;
    private int idUsuDir;
    private String direccion;
    private String localizacion;

    public Direcciones(String direccion, String localizacion) {

        //this.idUsuDir = 0;
        this.direccion = direccion;
        this.localizacion = localizacion;
    }

    public int getIdDir() {
        return idDir;
    }

    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

    public int getIdUsuDir() {
        return idUsuDir;
    }

    public void setIdUsuDir(int idUsuDir) {
        this.idUsuDir = idUsuDir;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
