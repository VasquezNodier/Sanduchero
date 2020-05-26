package co.vasquez.nodier.sanduchero.view.inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;
import co.vasquez.nodier.sanduchero.model.repository.UsuarioRepo;

public class HostInicioActivity extends AppCompatActivity {
    //REGISTRO
    private EditText etNombre, etCorreo, etContra, etConfirContra;
    private RadioButton rbAcepto;
    private Button btnCrearUsuario;
    private UsuarioRepo usuarioRepo;

    //LOGIN
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnIngresar, btnRegistrar;

    private FirebaseAuth mAuth;
    private static final String TAG = "CorreoContra";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_inicio);

    }



}
