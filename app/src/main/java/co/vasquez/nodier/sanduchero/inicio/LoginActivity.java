package co.vasquez.nodier.sanduchero.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.MainActivity;
import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.actividades.MenuInicioActivity;
import co.vasquez.nodier.sanduchero.modelo.Usuarios;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtPassword;
    private Button btnLogin;
    private TextView txvOlvido, txvRegistro;
    private List<Usuarios> usuarios;
    Usuarios usuario;

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
        edtUsuario = findViewById(R.id.edt_usuario);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txvOlvido = findViewById(R.id.txv_olvido);
        txvRegistro = findViewById(R.id.txv_registro);
    }



    public void login() {
        //final SharedPreferences preferencias =getSharedPreferences("Mis Datos",MODE_PRIVATE);
        //Boolean logueado = preferencias.getBoolean()

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = "Nodier";
                String pass = "123";
                
                String user = edtUsuario.getText().toString();
                String contra = edtPassword.getText().toString();

                if(user.equals("") || user.equals("")){
                    Toast.makeText(getApplicationContext(), "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                }else if (user.equals(username) && contra.equals(pass)){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void olvido(){
        txvOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, OlvideActivity.class);
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
