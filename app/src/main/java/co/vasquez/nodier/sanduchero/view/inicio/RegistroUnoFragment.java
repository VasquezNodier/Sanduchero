package co.vasquez.nodier.sanduchero.view.inicio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import co.vasquez.nodier.sanduchero.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroUnoFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class RegistroUnoFragment extends Fragment {



    public RegistroUnoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_uno, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*btnSigReg = view.findViewById(R.id.btn_sig_reg);

        btnSigReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.registroDosFragment);
            }
        });*/
    }






}
