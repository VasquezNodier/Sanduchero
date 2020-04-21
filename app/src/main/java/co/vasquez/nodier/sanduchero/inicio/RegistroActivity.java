package co.vasquez.nodier.sanduchero.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.vasquez.nodier.sanduchero.R;

public class RegistroActivity extends AppCompatActivity {

    private Button btnSigReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        asociarElementos();
    }

    public void asociarElementos(){
        btnSigReg = findViewById(R.id.btn_sig_reg);
    }

    public void siguiente (View view){
        Intent i = new Intent(RegistroActivity.this,RegistroDosActivity.class);
        startActivity(i);
    }
}
