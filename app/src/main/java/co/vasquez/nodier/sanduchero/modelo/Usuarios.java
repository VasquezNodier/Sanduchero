package co.vasquez.nodier.sanduchero.modelo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity
public class Usuarios {

    @PrimaryKey(autoGenerate = true)
    private int idUsu;
    private String nombreUsu;
    private String apellidoUsu;
    private String usuarioUsu;
    private String telefonoUsu;
    private String correoUsu;
    private String contraUsu;

    @Ignore
   public Usuarios(int idUsu, String nombreUsu, String apellidoUsu, String usuarioUsu, String telefonoUsu,
                   String correoUsu, String contraUsu) {
        this.idUsu = idUsu;
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.usuarioUsu = usuarioUsu;
        this.telefonoUsu = telefonoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
    }

    public Usuarios(String nombreUsu, String apellidoUsu, String usuarioUsu, String telefonoUsu,
                    String correoUsu, String contraUsu) {
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.usuarioUsu = usuarioUsu;
        this.telefonoUsu = telefonoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
    }

    @Ignore
    public Usuarios(String nombreUsu, String apellidoUsu, String correoUsu, String contraUsu) {
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
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


