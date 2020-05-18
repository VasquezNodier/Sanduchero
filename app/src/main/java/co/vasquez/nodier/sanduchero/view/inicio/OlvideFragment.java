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
 * Use the {@link OlvideFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class OlvideFragment extends Fragment {
    private Button btnOlvideEnviar;

    public OlvideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_olvide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOlvideEnviar = view.findViewById(R.id.btn_olvideEnviar);

        btnOlvideEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

    }
}
