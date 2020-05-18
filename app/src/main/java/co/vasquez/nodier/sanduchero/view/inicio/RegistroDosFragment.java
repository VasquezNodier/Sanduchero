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

import co.vasquez.nodier.sanduchero.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroDosFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class RegistroDosFragment extends Fragment {
    private Button btnCrearReg;

    public RegistroDosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_dos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCrearReg = view.findViewById(R.id.btn_crear_reg);
        btnCrearReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

    }
}
