package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;
import co.vasquez.nodier.sanduchero.model.repository.SanducheRepo;
import co.vasquez.nodier.sanduchero.view.adapter.SanduNuestrosAdapter;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuInicioFragment#newInstance} factory method to
 * img_create an instance of this fragment.
 */
public class MenuInicioFragment extends Fragment {

    private SanduNuestrosAdapter sanducheAdapter;
    private SanducheRepo sanducheRepo;
    private List<Sanduches> sanduches;

    public MenuInicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_inicio, container, false);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asociarElementos(view);
        mostrarDatos();
        obtenerNuestrosSanduches();
    }

    private void mostrarDatos() {
        sanducheRepo = new SanducheRepo(getContext());
        sanduches = new ArrayList<>();

        sanducheAdapter = new SanduNuestrosAdapter(sanduches, new SanduNuestrosAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(Sanduches sanduche, int posicion) {
                Toast.makeText(getContext(), "seleccioné " + sanduche.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        //rvSanduches.setLayoutManager(manager);
        //rvSanduches.setAdapter(sanducheAdapter);
        //rvSanduches.setHasFixedSize(true);
        getDataFirestore();
    }

    private void asociarElementos(View v) {
        //rvSanduches = v.findViewById(R.id.rv_sanducheSan);
    }

    private void getDataFirestore() {
        sanducheRepo.obtenerNuestrosSanduches(new CallBackFirestore<List<Sanduches>>() {
            @Override
            public void correcto(List<Sanduches> respuesta) {
                sanducheAdapter.setSanduches(respuesta);
                sanducheAdapter.notifyDataSetChanged();
            }
        });
    }

    private void obtenerNuestrosSanduches(){
        sanducheRepo.obtenerNuestrosSanduches(new CallBackFirestore<List<Sanduches>>() {
            @Override
            public void correcto(List<Sanduches> respuesta) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFirestore();
        sanducheAdapter.setSanduches(sanduches);
        sanducheAdapter.notifyDataSetChanged();
    }
}
