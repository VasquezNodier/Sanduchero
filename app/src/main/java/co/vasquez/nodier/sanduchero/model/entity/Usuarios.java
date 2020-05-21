package co.vasquez.nodier.sanduchero.model.entity;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String nombreUsu;
    private String correoUsu;
    private String contraUsu;

    public Usuarios(String nombreUsu, String correoUsu, String contraUsu) {
        this.nombreUsu = nombreUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
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
                ", nombreUsu='" + nombreUsu + '\'' +
                ", correoUsu='" + correoUsu + '\'' +
                ", contraUsu='" + contraUsu + '\'' +
                '}';
    }


}


