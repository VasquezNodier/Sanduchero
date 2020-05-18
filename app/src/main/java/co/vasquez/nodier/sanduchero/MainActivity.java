package co.vasquez.nodier.sanduchero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import co.vasquez.nodier.sanduchero.view.adapter.UsuarioAdapter;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;


public class MainActivity extends AppCompatActivity {

    private ImageView imgRecomendados, imgArmalo, imgCreados;
    private RecyclerView rvUsuarios;
    private List<Usuarios> usuarios;
    private UsuarioAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.asociarElementos();
        this.getDataFake();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        //RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),2);
        miAdaptador = new UsuarioAdapter(usuarios, new UsuarioAdapter.NombreDeInterface() {
            @Override
            public void metodoOnclick(Usuarios usuario, int posicion) {
                Toast.makeText(MainActivity.this, "Hice click en: "+usuario, Toast.LENGTH_SHORT).show();
            }
        });

        rvUsuarios.setLayoutManager(manager);
        rvUsuarios.setAdapter(miAdaptador);

    }

    public void asociarElementos(){
        /*imgRecomendados = findViewById(R.id.img_Recomendados);
        imgArmalo = findViewById(R.id.img_Armalo);
        imgCreados = findViewById(R.id.img_Creados);
        rvUsuarios = findViewById(R.id.rv_usuarios);*/
    }

    public void getDataFake(){


    }



}
