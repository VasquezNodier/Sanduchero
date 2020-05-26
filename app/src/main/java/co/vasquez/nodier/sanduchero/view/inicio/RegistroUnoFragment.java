package co.vasquez.nodier.sanduchero.view.inicio;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegistroUnoFragment extends Fragment {

    private static final String TAG = "CorreoContraseña";
    private EditText etNombre, etCorreo, etContra, etConfirContra;
    private RadioButton rbAcepto;
    private Button btnCrearUsuario;
    private UsuarioRepo usuarioRepo;
    private ProgressDialog progressDialog;


    //AUTENTICACIÓN
    private FirebaseAuth mAuth;

    public RegistroUnoFragment() {
        // Required empty public constructor
    }

    private void asociarElementos(View view) {
        etNombre = view.findViewById(R.id.et_Nombre);
        etCorreo = view.findViewById(R.id.et_Correo);
        etContra = view.findViewById(R.id.et_Contra);
        etConfirContra = view.findViewById(R.id.et_ConfirContra);
        rbAcepto = view.findViewById(R.id.rb_Acepto);
        btnCrearUsuario = view.findViewById(R.id.btn_CrearCliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_uno, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        registrarCliente();
    }

    public void registrarCliente() {
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nombre = etNombre.getText().toString();
                final String correo = etCorreo.getText().toString();
                final String contra = etContra.getText().toString();

                final Usuarios nuevoUsuario = new Usuarios();

                if (validarFormulario() && getActivity()!= null) {

                    mAuth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(getActivity(),
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Usuario Creado con Éxito");

                                        Toast.makeText(getContext(), "Registro Firebase Exitoso", Toast.LENGTH_SHORT).show();
                                        nuevoUsuario.setNombre(nombre);
                                        nuevoUsuario.setCorreo(correo);
                                        nuevoUsuario.setContra(contra);
                                        nuevoUsuario.setTipo("Cliente");

                                        usuarioRepo = new UsuarioRepo(getContext());
                                        usuarioRepo.agregarUsuario(nuevoUsuario, new CallBackFirestore<Usuarios>() {
                                            @Override
                                            public void correcto(Usuarios respuesta) {
                                                Toast.makeText(getContext(), "Usuario Registrado DB con éxito", Toast.LENGTH_SHORT).show();
                                                Navigation.findNavController(getView()).navigate(R.id.loginFragment);
                                                onDetach();
                                            }
                                        });

                                    } else {
                                        Log.w(TAG, "Falló el registro", task.getException());
                                        Toast.makeText(getContext(), "Falló el Registro", Toast.LENGTH_SHORT).show();
                                        progressDialog.setMessage("No se pudo realizar el registro");
                                        progressDialog.show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });
    }

    //VALIDAR FORMULARIO
    private boolean validarFormulario() {

        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString().trim();
        String contra = etContra.getText().toString();
        String confirContra = etConfirContra.getText().toString();
        boolean acepto = rbAcepto.isChecked();

        boolean validar = false;

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contra)  ||
                TextUtils.isEmpty(confirContra)) {
            Toast.makeText(getContext(), "Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();
            validar = false;
        }else if (contra.length()<8){
            Toast.makeText(getActivity(), "La contraseña debe contener al menos 8 dígitos", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.equals(contra, confirContra)) {
            Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            validar = false;
        }else  if (!acepto) {
            Toast.makeText(getContext(), "Aceptar términos y condiciones", Toast.LENGTH_SHORT).show();
            validar = false;
        } else {

            validar = true;
        }

        return validar;

    }

    public void crearNuevoUsuarioAuth(String correo, String contra) {


    }


}
