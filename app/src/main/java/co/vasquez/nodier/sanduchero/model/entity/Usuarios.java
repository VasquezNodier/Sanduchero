package co.vasquez.nodier.sanduchero.model.entity;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String idUsu;
    private String nombreUsu;
    private String apellidoUsu;
    private String usuarioUsu;
    private String telefonoUsu;
    private String correoUsu;
    private String contraUsu;

    public Usuarios(String nombreUsu, String apellidoUsu, String usuarioUsu, String telefonoUsu, String correoUsu, String contraUsu) {
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.usuarioUsu = usuarioUsu;
        this.telefonoUsu = telefonoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
    }

    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getApellidoUsu() {
        return apellidoUsu;
    }

    public void setApellidoUsu(String apellidoUsu) {
        this.apellidoUsu = apellidoUsu;
    }

    public String getUsuarioUsu() {
        return usuarioUsu;
    }

    public void setUsuarioUsu(String usuarioUsu) {
        this.usuarioUsu = usuarioUsu;
    }

    public String getTelefonoUsu() {
        return telefonoUsu;
    }

    public void setTelefonoUsu(String telefonoUsu) {
        this.telefonoUsu = telefonoUsu;
    }

    public String getCorreoUsu() {
        return correoUsu;
    }

    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }

    public String getContraUsu() {
        return contraUsu;
    }

    public void setContraUsu(String contraUsu) {
        this.contraUsu = contraUsu;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsu=" + idUsu +
                ", nombreUsu='" + nombreUsu + '\'' +
                ", apellidoUsu='" + apellidoUsu + '\'' +
                ", usuarioUsu='" + usuarioUsu + '\'' +
                ", telefonoUsu='" + telefonoUsu + '\'' +
                ", correoUsu='" + correoUsu + '\'' +
                ", contraUsu='" + contraUsu + '\'' +
                '}';
    }


}


