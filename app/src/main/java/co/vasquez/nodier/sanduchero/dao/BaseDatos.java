package co.vasquez.nodier.sanduchero.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import co.vasquez.nodier.sanduchero.modelo.Restaurantes;
import co.vasquez.nodier.sanduchero.modelo.Usuarios;

@Database(entities = {Usuarios.class},
        version = 1,exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

    public abstract UsuariosDAO usuariosDAO();
    private static BaseDatos instancia;

    public static BaseDatos getInstancia(Context contexto) {
        if (instancia==null){
            instancia = Room.databaseBuilder(contexto.getApplicationContext(), BaseDatos.class,
                    "sanduchero.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
