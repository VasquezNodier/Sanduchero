package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Ingredientes;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;
import co.vasquez.nodier.sanduchero.model.repository.IngredienteRepo;
import co.vasquez.nodier.sanduchero.model.repository.SanducheRepo;
import co.vasquez.nodier.sanduchero.view.adapter.IngredienteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearSanducheFragment extends Fragment {

    RecyclerView rvListaIngredientes;
    IngredienteAdapter ingredienteAdapter;
    List<Ingredientes> ingredientes;
    IngredienteRepo ingredienteRepo;
    SanducheRepo sanducheRepo;
    Button btnCrearAgregar, btnGaleria;
    EditText edtNombreSanduche;
    TextView txPrecioCreado;

    CheckBox chkSeleccionIng;
    boolean check;
    int precio;

    private int GALERIA_INTENT = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int CAMERA_PERM_CODE;

    public CrearSanducheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_sanduche, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asociarElementos(view);
        mostrarDatos(view);
        obtenerIngredientes();
        crearSanduche();

    }

    public void asociarElementos(View view) {
        rvListaIngredientes = view.findViewById(R.id.rv_ListaIngredientes);
        chkSeleccionIng = view.findViewById(R.id.chk_SeleccionIng);
        btnCrearAgregar = view.findViewById(R.id.btn_CrearAgregar);
        edtNombreSanduche = view.findViewById(R.id.edt_NombreSanduche);
        btnGaleria = view.findViewById(R.id.btn_Galeria);
        txPrecioCreado = view.findViewById(R.id.tx_PrecioCreado);
    }

    public void getDataFirestore() {
        ingredienteRepo.obtenerIngredientes(new CallBackFirestore<List<Ingredientes>>() {
            @Override
            public void correcto(List<Ingredientes> respuesta) {
                ingredienteAdapter.setIngredientes(respuesta);
                ingredienteAdapter.notifyDataSetChanged();
            }
        });
    }

    public void mostrarDatos(final View view) {
        ingredienteRepo = new IngredienteRepo(getContext());
        ingredientes = new ArrayList<>();

        ingredienteAdapter = new IngredienteAdapter(ingredientes, new IngredienteAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(final Ingredientes ingrediente, int posicion, boolean chk) {
                //Toast.makeText(getContext(), "Seleccioné desde CLASE: " + ingrediente.getNombre(), Toast.LENGTH_LONG).show();
                Log.d("Prueba-Click", "Hice click Clase en: " + ingrediente.getNombre());
                /*chkSeleccionIng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Toast.makeText(getContext(), "Seleccionaste: "+ingrediente.getNombre(), Toast.LENGTH_LONG).show();
                            precio += ingrediente.getPrecio();
                            txPrecioCreado.setText("$ " + precio);
                        }else{
                            Toast.makeText(getContext(), "Retiraste: "+ingrediente.getNombre(), Toast.LENGTH_LONG).show();
                            precio -= ingrediente.getPrecio();
                            txPrecioCreado.setText("$ " + precio);
                        }
                    }
                });*/
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvListaIngredientes.setLayoutManager(manager);
        rvListaIngredientes.setAdapter(ingredienteAdapter);
        rvListaIngredientes.setHasFixedSize(true);
        getDataFirestore();

    }

    public void obtenerIngredientes() {
        ingredienteRepo.obtenerIngredientes(new CallBackFirestore<List<Ingredientes>>() {
            @Override
            public void correcto(List<Ingredientes> respuesta) {

            }
        });
    }

    public void crearSanduche() {
        seleccionarFoto();
        btnCrearAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Sanduches nuevoSanduche = new Sanduches();

                nuevoSanduche.setNombre(edtNombreSanduche.getText().toString());
                nuevoSanduche.setPrecio(15000);
                nuevoSanduche.setImagen("URL");
                nuevoSanduche.setCategoria("creados");
                //STORAGE
                FirebaseStorage storage = FirebaseStorage.getInstance("gs://androidsanduchero.appspot.com/");
                StorageReference storageRef = storage.getReference();
                StorageReference imagsRef = storageRef.child("sanduches");


                sanducheRepo = new SanducheRepo(getContext());
                /*sanducheRepo.agregarSanduche(nuevoSanduche, new CallBackFirestore<Sanduches>() {
                    @Override
                    public void correcto(Sanduches respuesta) {

                    }
                });*/
                sanducheRepo.agregarSanducheR(nuevoSanduche, new CallBackFirestore<Sanduches>() {
                    @Override
                    public void correcto(Sanduches respuesta) {

                    }
                }, edtNombreSanduche.getText().toString());
            }
        });
    }

    public void seleccionarFoto() {
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALERIA_INTENT);
                /*askCameraPermissions();
                Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(tomarFoto.resolveActivity()!=null) {
                    startActivityForResult(tomarFoto, REQUEST_IMAGE_CAPTURE);
                }*/


            }
        });
    }

    /*private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CAMERA_PERM_CODE);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else {
                Toast.makeText(getContext(), "Los permisos de la cámara son necesarios para usarla",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Toast.makeText(getContext(), "Solicitando apertura de cámara",
                Toast.LENGTH_SHORT).show();
    }*/
}
