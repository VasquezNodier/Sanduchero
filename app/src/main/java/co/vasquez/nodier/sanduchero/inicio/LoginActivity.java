package co.vasquez.nodier.sanduchero.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import co.vasquez.nodier.sanduchero.MainActivity;
import co.vasquez.nodier.sanduchero.R;

public class LoginActivity extends AppCompatActivity {
    private ImageView imgBack;
    private Button btnLogin;
    private TextView txvOlvido, txvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Picasso.get().load(R.drawable.back_uno).into(imgBack);

        asociarElementos();
        login();
        olvido();
        registro();

    }

    public void asociarElementos(){
        //imgBack = findViewById(R.id.img_backuno);
        btnLogin = findViewById(R.id.btn_login);
        txvOlvido = findViewById(R.id.txv_olvido);
        txvRegistro = findViewById(R.id.txv_registro);
    }

    public void login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void olvido(){
        txvOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void registro(){
        txvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
    }

}
