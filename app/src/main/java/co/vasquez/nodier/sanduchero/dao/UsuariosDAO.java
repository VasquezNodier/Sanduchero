package co.vasquez.nodier.sanduchero.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.modelo.Usuarios;

@Dao
public interface UsuariosDAO {

    @Insert
    void crearUsuario(Usuarios miUsuario);

    @Query("SELECT * FROM Usuarios")
    List<Usuarios> obtenerUsuarios();

    @Update
    void actualizarUsuario(Usuarios miUsuario);

    @Delete
    void eliminarUsuario(Usuarios miUsuario);

    @Query("SELECT * FROM USUARIOS WHERE nombreUsu=:nombreUsu & contraUsu =:contraUsu")
    List<Usuarios> verificarUsuario(String nombreUsu, String contraUsu);

    /*Eliminar Tabla
    @Query("DELETE FROM Usuarios")
    void borrarTablaUsuarios();*/

    @Query("SELECT * FROM Usuarios WHERE idUsu =:idUsu ")
    Usuarios obtenerUsuarioPorId (int idUsu);

    @Query("SELECT * FROM Usuarios WHERE nombreUsu =:nombreUsu")
    Usuarios obtenerUsuarioPorId (String nombreUsu);



}
