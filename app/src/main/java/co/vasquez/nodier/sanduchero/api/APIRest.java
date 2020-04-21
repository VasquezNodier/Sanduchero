package co.vasquez.nodier.sanduchero.api;

import java.util.List;

import co.vasquez.nodier.sanduchero.modelo.Usuarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIRest {

    final String URL_PHP = "ApiRestUsuarios.php";

    @GET(URL_PHP)
    Call<List<Usuarios>> obtenerUsuarios();

    @GET(URL_PHP)
    Call<Usuarios> obtenerUsuario(@Query("id_Usu") int idUsu);

    @POST(URL_PHP)
    Call<Void> crearUsuario(@Body Usuarios usuarios);

    @PUT(URL_PHP)
    Call<Void> editarUsuario(@Body Usuarios usuarios);

    @DELETE(URL_PHP)
    Call<Void> eliminarUsuarios(@Query("id_Usu") int idUsu);


}
