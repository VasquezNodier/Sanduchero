package co.vasquez.nodier.sanduchero.modelo;

public class Usuarios {
    private int idUsu;
    private String nombreUsu;
    private String apellidoUsu;
    private String usuarioUsu;
    private String telefonoUsu;
    private String correoUsu;
    private String contraUsu;
    private int tipoUsu;

    public Usuarios() {
    }

    public Usuarios(String nombreUsu, String apellidoUsu, String usuarioUsu, String telefonoUsu, String correoUsu, String contraUsu, int tipoUsu) {
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.usuarioUsu = usuarioUsu;
        this.telefonoUsu = telefonoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
        this.tipoUsu = tipoUsu;
    }

    public Usuarios(String nombreUsu, String apellidoUsu, String correoUsu, String contraUsu, int tipoUsu) {
        this.nombreUsu = nombreUsu;
        this.apellidoUsu = apellidoUsu;
        this.correoUsu = correoUsu;
        this.contraUsu = contraUsu;
        this.tipoUsu = tipoUsu;
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

    public int getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(int tipoUsu) {
        this.tipoUsu = tipoUsu;
    }
}


