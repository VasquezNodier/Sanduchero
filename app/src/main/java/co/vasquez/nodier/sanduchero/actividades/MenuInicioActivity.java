package co.vasquez.nodier.sanduchero.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.MainActivity;
import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.adaptadores.UsuarioAdapter;
import co.vasquez.nodier.sanduchero.dao.BaseDatos;
import co.vasquez.nodier.sanduchero.dao.UsuariosDAO;
import co.vasquez.nodier.sanduchero.modelo.Usuarios;

public class MenuInicioActivity extends AppCompatActivity{

    private ImageView imgRecomendados, imgArmalo, imgCreados;
    private RecyclerView rvUsuarios;
    private List<Usuarios> usuarios;
    private UsuarioAdapter miAdaptador;
    UsuariosDAO usuariosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

    }

    public void asociarElementos(){
        imgRecomendados = findViewById(R.id.img_Recomendados);
        imgArmalo = findViewById(R.id.img_Armalo);
        imgCreados = findViewById(R.id.img_Creados);
        rvUsuarios = findViewById(R.id.rv_usuarios);
    }

    public void getDataFake(){

        usuarios = usuariosDAO.obtenerUsuarios();

        if(usuarios.size()==0){

            usuariosDAO.crearUsuario(new Usuarios(
                    "Jose","Garcia","jose","3211234567",
                    "jvasquez@gmail.com","jose"));
            usuariosDAO.crearUsuario(new Usuarios(
                    "Maria","Pavas","luz","3171234567",
                    "mariapavas@gmail.com","luz"));
            usuariosDAO.crearUsuario(new Usuarios(
                    "Nodier","Vasquez","Nodier","3143212611",
                    "nvasquez280@unab.edu.co","123"));
            usuariosDAO.crearUsuario(new Usuarios(
                    "Alexander","Pavas","Alex","3223172827",
                    "apavas@email.com","Alex"));
            usuariosDAO.crearUsuario(new Usuarios(
                    "Jorge","Pavas","Alex","3223172827",
                    "apavas@email.com","456"));
        }

        usuarios = usuariosDAO.obtenerUsuarios();

    }

    public void recomendados(){
        imgRecomendados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInicioActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void creados(){
        imgCreados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInicioActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void armalo(){
        imgArmalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInicioActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}