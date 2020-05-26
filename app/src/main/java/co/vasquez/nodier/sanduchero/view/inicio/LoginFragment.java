package co.vasquez.nodier.sanduchero.view.inicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;
import co.vasquez.nodier.sanduchero.view.fragmentCli.HostClienteActivity;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class
LoginFragment extends Fragment {

    private static final String TAG = "CorreoContraseña";
    private EditText etCorreo;
    private EditText etPassword;
    private Button btnIngresar;
    private TextView tvOlvido;
    private TextView tvRegistro;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        iniciarSesion();
        botones(view);
    }

    public void iniciarSesion() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String correo = etCorreo.getText().toString();
                final String contra = etPassword.getText().toString();

                if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contra)) {
                    Toast.makeText(getContext(), "Se deben completar los campos", Toast.LENGTH_SHORT).show();
                } else {

                    mAuth.signInWithEmailAndPassword(correo, contra).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(getActivity(), HostClienteActivity.class);
                                        startActivity(i);
                                    } else {
                                        Log.w(TAG, "Fallo el Ingreso", task.getException());
                                        Toast.makeText(getContext(), "Falló la autenticación",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.getCurrentUser();
    }

    public void botones(View view) {
        final Usuarios miUsuario = new Usuarios("Nodier Vasquez",
                "nv@av.com", "123", "Cliente");
        //opción uno de cambio de pantalla
        /*btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(v).navigate(R.id.menuCliFragment);
                //Navigation.findNavController(v).navigate(LoginFragmentDirections.ac);
                Intent i = new Intent(getActivity(), HostClienteActivity.class);
                startActivity(i);
            }
        });*/
        //otra opción para el cambio de pantalla
        // es más útil para cuando tenemos varios llamados en una misma pantalla
        final NavController navController = Navigation.findNavController(view);
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registroUnoFragment);
            }
        });

        tvOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.olvideFragment);
            }
        });
    }

    public void asociarElementos(View v) {
        etCorreo = v.findViewById(R.id.et_Correo);
        etPassword = v.findViewById(R.id.et_Password);
        btnIngresar = v.findViewById(R.id.btn_Ingresar);
        tvOlvido = v.findViewById(R.id.tx_Olvido);
        tvRegistro = v.findViewById(R.id.tx_Registro);
        //btnRegistrar = v.findViewById(R.id.btn_Registrar);
    }
}
