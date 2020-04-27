package co.vasquez.nodier.sanduchero.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.vasquez.nodier.sanduchero.modelo.Restaurantes;

@Dao
public interface RestaurantesDAO {
    @Insert
    void crearRestaurante(Restaurantes miRestaurante);

    @Query("SELECT * FROM Restaurantes")
    List<Restaurantes> obtenerRestaurantes();

    @Update
    void actualizarRestaurante(Restaurantes miRestaurante);

    @Delete
    void eliminarRestaurante (Restaurantes miRestaurante);

    /*Eliminar Tabla
    @Query("DELETE FROM Restaurantes")
    void borrarTablaRestaurantes();*/

    @Query("SELECT * FROM Restaurantes WHERE idRes =:idRes")
    void obtenerRestaurantesPorId(int idRes);

    @Query("SELECT * FROM Restaurantes WHERE nombreRes =:nombreRes")
    void obtenerRestaurantesPorNombre(String nombreRes);

}
