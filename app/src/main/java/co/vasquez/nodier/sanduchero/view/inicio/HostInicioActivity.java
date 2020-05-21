package co.vasquez.nodier.sanduchero.view.inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class HostInicioActivity extends AppCompatActivity {
    //REGISTRO
    private EditText etNombre, etCorreo, etContra, etConfirContra;
    private RadioButton rbAcepto;
    private Button btnCrearUsuario;
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
        asociarElementos();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        crearUsuario();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void asociarElementos() {

        //REGISTRO
        etNombre = findViewById(R.id.et_Nombre);
        etCorreo = findViewById(R.id.et_Correo);
        etContra = findViewById(R.id.et_Contra);
        etConfirContra = findViewById(R.id.et_ConfirContra);
        rbAcepto = findViewById(R.id.rb_Acepto);
        btnCrearUsuario = findViewById(R.id.btn_CrearUsuario);

        //LOGIN
        etUsuario = findViewById(R.id.et_Usuario);
        etPassword = findViewById(R.id.et_Password);
        btnIngresar = findViewById(R.id.btn_Ingresar);
        //btnRegistrar =findViewById(R.id.btn_Registrar);


    }

    public void crearUsuario() {

        /*btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = etCorreo.getText().toString().trim();
                String contra = etContra.getText().toString().trim();

                if (TextUtils.isEmpty(correo)) {
                    Toast.makeText(getApplicationContext(), "Se debe ingresar un correo", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(contra)) {
                    Toast.makeText(getApplicationContext(), "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
                } else {

                    progressDialog.setMessage("Creando nuevo usuario");
                    mAuth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Usuario creado con éxito");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "Fallo en la creación del Usuario", task.getException());
                                Toast.makeText(getApplicationContext(), "Fallo en la autenticación",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });*/


    }


    private boolean validarFormulario() {
        boolean valid = true;


        return valid;
    }

    private void inicioSesion() {

        String correo = etUsuario.getText().toString();
        String contra = etPassword.getText().toString();

        if (!validarFormulario()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(correo, contra).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Inicio de sesión correcto");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Fallo en inicio de sesión", task.getException());
                            Toast.makeText(getApplicationContext(), "Fallo en la autenticación.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void verificarCorreo() {
        // Disable button
        //mBinding.verifyEmailButton.setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        /*user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        mBinding.verifyEmailButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]*/
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        /*if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }*/
    }


}
