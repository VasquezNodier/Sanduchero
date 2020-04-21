package co.vasquez.nodier.sanduchero.modelo;

public class Restaurantes {

    private int idRes;
    private String  nombreRes;
    private String telefonoRes;
    private String usuarioRes;
    private String correoRes;
    private String contraRes;
    private String direccionRes;

    public Restaurantes() {
    }

    public Restaurantes(String nombreRes, String telefonoRes, String usuarioRes, String correoRes, String contraRes, String direccionRes) {
        this.idRes = 0;
        this.nombreRes = nombreRes;
        this.telefonoRes = telefonoRes;
        this.usuarioRes = usuarioRes;
        this.correoRes = correoRes;
        this.contraRes = contraRes;
        this.direccionRes = direccionRes;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public String getNombreRes() {
        return nombreRes;
    }

    public void setNombreRes(String nombreRes) {
        this.nombreRes = nombreRes;
    }

    public String getTelefonoRes() {
        return telefonoRes;
    }

    public void setTelefonoRes(String telefonoRes) {
        this.telefonoRes = telefonoRes;
    }

    public String getUsuarioRes() {
        return usuarioRes;
    }

    public void setUsuarioRes(String usuarioRes) {
        this.usuarioRes = usuarioRes;
    }

    public String getCorreoRes() {
        return correoRes;
    }

    public void setCorreoRes(String correoRes) {
        this.correoRes = correoRes;
    }

    public String getContraRes() {
        return contraRes;
    }

    public void setContraRes(String contraRes) {
        this.contraRes = contraRes;
    }

    public String getDireccionRes() {
        return direccionRes;
    }

    public void setDireccionRes(String direccionRes) {
        this.direccionRes = direccionRes;
    }
}
