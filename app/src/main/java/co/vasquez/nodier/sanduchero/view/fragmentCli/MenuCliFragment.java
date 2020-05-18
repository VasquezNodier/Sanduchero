package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;
import co.vasquez.nodier.sanduchero.model.repository.SanducheRepo;
import co.vasquez.nodier.sanduchero.view.adapter.SanduNuestrosAdapter;
import co.vasquez.nodier.sanduchero.view.adapter.SanduCreadosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCliFragment extends Fragment {

    private SanduNuestrosAdapter sanduNuestrosAdapter;
    private SanduCreadosAdapter sanduCreadossAdapter;
    private SanducheRepo sanducheRepo;
    private List<Sanduches> nuestrosSanduches;
    private List<Sanduches> creadosSanduches;
    private RecyclerView rvNuestros;
    private RecyclerView rvCreados;
    private Button btnCrearSanduche;


    public MenuCliFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_cli, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asociarElementos(view);
        mostrarDatos(view);
        obtenerNuestrosSanduches();
        crearSanduche();

    }

    private void asociarElementos(View v) {
        rvNuestros = v.findViewById(R.id.rv_Categoria1);
        rvCreados = v.findViewById(R.id.rv_Categoria2);
        btnCrearSanduche = v.findViewById(R.id.btn_CrearSanduche);

    }

    private void getDataFirestore() {
        sanducheRepo.obtenerNuestrosSanduches(new CallBackFirestore<List<Sanduches>>() {
            @Override
            public void correcto(List<Sanduches> respuesta) {
                    sanduNuestrosAdapter.setSanduches(respuesta);
                    sanduNuestrosAdapter.notifyDataSetChanged();
            }
        });

        sanducheRepo.obtenerSanduchesCreados(new CallBackFirestore<List<Sanduches>>() {
            @Override
            public void correcto(List<Sanduches> respuesta) {
                sanduCreadossAdapter.setSanduches(respuesta);
                sanduCreadossAdapter.notifyDataSetChanged();
            }
        });
    }


    private void mostrarDatos(final View view) {
        sanducheRepo = new SanducheRepo(getContext());
        nuestrosSanduches = new ArrayList<>();
        sanduNuestrosAdapter = new SanduNuestrosAdapter(nuestrosSanduches, new SanduNuestrosAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(Sanduches sanduche, int posicion) {
                Toast.makeText(getContext(), "seleccioné " + sanduche.getNombre(), Toast.LENGTH_SHORT).show();
                //Log.d("Prueba-Click","Hice Click Clase en: "+sanduche.getNombre());
                final NavController navController = Navigation.findNavController(view);
                navController.navigate(MenuCliFragmentDirections.
                        actionMenuCliFragmentToDetalleSanducheFragment(sanduche));
            }
        });

        sanducheRepo = new SanducheRepo(getContext());
        creadosSanduches = new ArrayList<>();
        sanduCreadossAdapter = new SanduCreadosAdapter(creadosSanduches, new SanduCreadosAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(Sanduches sanduche, int posicion) {
                //Toast.makeText(getContext(), "seleccioné " + sanduche.getNombre(), Toast.LENGTH_SHORT).show();

                final NavController navController = Navigation.findNavController(view);
                navController.navigate(MenuCliFragmentDirections.
                        actionMenuCliFragmentToDetalleSanducheFragment(sanduche));
            }
        });

        // CATEGORIA 1
        LinearLayoutManager manager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        //RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        rvNuestros.setLayoutManager(manager1);
        rvNuestros.setAdapter(sanduNuestrosAdapter);
        rvNuestros.setHasFixedSize(true);

        // CATEGORIA 2
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(getContext());
        //RecyclerView.LayoutManager manager2 = new GridLayoutManager(getContext(), 2);
        rvCreados.setLayoutManager(manager2);
        rvCreados.setAdapter(sanduCreadossAdapter);
        rvCreados.setHasFixedSize(true);

        getDataFirestore();
    }

    private void obtenerNuestrosSanduches(){
        sanducheRepo.obtenerNuestrosSanduches(new CallBackFirestore<List<Sanduches>>() {
            @Override
            public void correcto(List<Sanduches> respuesta) {

            }
        });
    }

    private void crearSanduche(){
        btnCrearSanduche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.crearSanducheFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFirestore();
        sanduNuestrosAdapter.setSanduches(nuestrosSanduches);
        sanduNuestrosAdapter.notifyDataSetChanged();

        sanduCreadossAdapter.setSanduches(creadosSanduches);
        sanduCreadossAdapter.notifyDataSetChanged();

    }
}
