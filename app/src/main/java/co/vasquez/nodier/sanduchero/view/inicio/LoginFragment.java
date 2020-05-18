package co.vasquez.nodier.sanduchero.view.inicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;
import co.vasquez.nodier.sanduchero.view.fragmentCli.HostClienteActivity;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btnIngresar;
    private TextView tvOlvido;
    private TextView tvRegistro;
    private NavController navController;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asociarElementos(view);
        botones(view);
    }

    public void botones(View view){
        final Usuarios miUsuario = new Usuarios("Nodier","Vasquez",
                "nodier","3143212611","nv@av.com","123");
        //opción uno de cambio de pantalla
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(v).navigate(R.id.menuCliFragment);
                //Navigation.findNavController(v).navigate(LoginFragmentDirections.ac);
                Intent i = new Intent(getActivity(), HostClienteActivity.class);
                startActivity(i);
            }
        });

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

    public void asociarElementos(View v){
        etUsuario = v.findViewById(R.id.et_usuario);
        etPassword = v.findViewById(R.id.et_password);
        btnIngresar = v.findViewById(R.id.btn_ingresar);
        tvOlvido = v.findViewById(R.id.tv_olvido);
        tvRegistro = v.findViewById(R.id.tv_registro);
    }
}