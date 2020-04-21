package co.vasquez.nodier.sanduchero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import co.vasquez.nodier.sanduchero.api.APIRest;
import co.vasquez.nodier.sanduchero.api.RestAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView idUsu;
    private TextView nombreUsu;
    private TextView apellidoUsu;
    private TextView correoUsu;
    private TextView contraUsu;
    private TextView tipoUsu;

    APIRest APIRest;
    RestAdapter retrofit = new RestAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIRest = retrofit.getAdapter().create(APIRest.class);
    }

    public void registrar(View v){

    }

    public void asociarElementos(){
        idUsu.setText(0);
        nombreUsu = findViewById(R.id.edt_nombre);
        apellidoUsu = findViewById(R.id.edt_apellido);
        correoUsu = findViewById(R.id.edt_correo);
        contraUsu = findViewById(R.id.edt_contra);
        tipoUsu.setText(1);

    }





}
