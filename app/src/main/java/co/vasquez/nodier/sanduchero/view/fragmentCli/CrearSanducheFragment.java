package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
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

    private static final int CODIGO_FOTO_CAMARA = 1;
    private static final int CODIGO_FOTO_GALERIA = 2;

    private RecyclerView rvListaIngredientes;
    private IngredienteAdapter ingredienteAdapter;
    private List<Ingredientes> ingredientes;
    private IngredienteRepo ingredienteRepo;
    private SanducheRepo sanducheRepo;
    private Button btnCrearAgregar;
    private ImageButton ibFoto,ibGaleria;
    private EditText edtNombreSanduche;
    private TextView txPrecioCreado;

    private int precio;
    private ArrayList<String> ingrediente_sanduche;
    //STORAGE
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private Uri fotoUri;
    private String pathFoto;

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
        storageRef = FirebaseStorage.getInstance().getReference();
        ingrediente_sanduche = new ArrayList<>();
        mostrarDatos(view);
        obtenerIngredientes();
        imagenSanduche();

        crearSanduche();
    }

    public void asociarElementos(View view) {
        rvListaIngredientes = view.findViewById(R.id.rv_ListaIngredientes);
        btnCrearAgregar = view.findViewById(R.id.btn_CrearAgregar);
        edtNombreSanduche = view.findViewById(R.id.edt_NombreSanduche);
        ibFoto = view.findViewById(R.id.ib_Foto);
        ibGaleria = view.findViewById(R.id.ib_Galeria);
        txPrecioCreado = view.findViewById(R.id.tx_PrecioCreado);
    }

    //OBTENER DATOS DE FIRESTORE
    public void getDataFirestore() {
        ingredienteRepo.obtenerIngredientes(new CallBackFirestore<List<Ingredientes>>() {
            @Override
            public void correcto(List<Ingredientes> respuesta) {
                ingredienteAdapter.setIngredientes(respuesta);
                ingredienteAdapter.notifyDataSetChanged();
            }
        });
    }

    //**********************************************************************************************
    //MOSTRAR LOS SANDUCHES EN MI RECYCLER VIEW
    public void mostrarDatos(final View view) {
        ingredienteRepo = new IngredienteRepo(getContext());
        ingredientes = new ArrayList<>();
        //ingrediente_sanduche = new ArrayList<>();
        ingredienteAdapter = new IngredienteAdapter(ingredientes);
        final Sanduches nuevoSanduche = new Sanduches();

        ingredienteAdapter.setMiEscuchador(new IngredienteAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(final Ingredientes ingrediente, int posicion, boolean chk) {
                //Toast.makeText(getContext(), "Seleccioné desde CLASE: " + ingrediente.getNombre(), Toast.LENGTH_LONG).show();

                //SELECCION DE CHECKBOX PARA SUMAR Y RESTAR EL PRECIO DEL SANDUCHE
                if (chk) {
                    Log.d("Prueba-Click", "Selección: " + ingrediente.getNombre());
                    precio += ingrediente.getPrecio();
                    ingrediente_sanduche.add(ingrediente.getNombre());
                    txPrecioCreado.setText("$ " + precio);
                } else {
                    Log.d("Prueba-Click", "Retiraste: " + ingrediente.getNombre());
                    precio -= ingrediente.getPrecio();
                    txPrecioCreado.setText("$ " + precio);

                    for (int i = 0; i < ingrediente_sanduche.size(); i++) {
                        if (ingrediente.getNombre().equals(ingrediente_sanduche.get(i))) {
                            ingrediente_sanduche.remove(i);
                            Log.d("Prueba-Click", "Removido: " + ingrediente.getNombre());
                        }
                    }
                }
                //PRUEBA DE ELEMENTOS EN EL ARRAYLIST DE INGREDIENTES
                if (ingrediente_sanduche.size() != 0) {
                    for (int i = 0; i < ingrediente_sanduche.size(); i++) {
                        //Log.d("Elemento","Elemento: "+ingrediente_sanduche.get(i));
                        System.out.println("Ingrediente " + (i + 1) + ": " + ingrediente_sanduche.get(i));

                    }
                    //nuevoSanduche.setId_ingredientes(ingrediente_sanduche);
                }
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvListaIngredientes.setLayoutManager(manager);
        rvListaIngredientes.setAdapter(ingredienteAdapter);
        rvListaIngredientes.setHasFixedSize(true);
        getDataFirestore();
    }

    //**********************************************************************************************
    //OBTENER LOS SANDUCHES DE LA BD
    public void obtenerIngredientes() {
        ingredienteRepo.obtenerIngredientes(new CallBackFirestore<List<Ingredientes>>() {
            @Override
            public void correcto(List<Ingredientes> respuesta) {

            }
        });
    }

    //**********************************************************************************************
    //CREAR UN NUEVO SANDUCHE
    public void crearSanduche() {
        btnCrearAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ingrediente_sanduche = new ArrayList<>();
                final Sanduches nuevoSanduche = new Sanduches();
                final String nombre = edtNombreSanduche.getText().toString();
                if (nombre.isEmpty()) {
                    Toast.makeText(getContext(), "Falta el nombre", Toast.LENGTH_SHORT).show();
                } else if (precio < 0) {
                    Toast.makeText(getContext(), "Precio Erróneo", Toast.LENGTH_SHORT).show();
                } else if (ingrediente_sanduche.isEmpty()) {
                    Toast.makeText(getContext(), "Debes seleccionar ingredientes", Toast.LENGTH_SHORT).show();
                } else if (fotoUri == null) {
                    Toast.makeText(getContext(), "Debes tomar una foto", Toast.LENGTH_SHORT).show();
                } else {

                    nuevoSanduche.setNombre(nombre);
                    nuevoSanduche.setPrecio(precio);
                    nuevoSanduche.setCategoria("creados");
                    nuevoSanduche.setId_ingredientes(ingrediente_sanduche);

                    //AGREGAR LA FOTO A STORAGE Y DESCARGAR LA URL PARA ASIGNARLA A PRODUCTO PARA LA BD
                    String nomfoto = fotoUri.getPath().substring(fotoUri.getPath().lastIndexOf("/"));
                    StorageReference misFotos = storageRef.child("sanduches/" + nomfoto);

                    misFotos.putFile(fotoUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            String urlFoto = task.getResult().toString();
                                            nuevoSanduche.setImagen(urlFoto);
                                            sanducheRepo = new SanducheRepo(getContext());
                                            sanducheRepo.agregarSanducheR(nuevoSanduche, new CallBackFirestore<Sanduches>() {
                                                @Override
                                                public void correcto(Sanduches respuesta) {

                                                }
                                            }, edtNombreSanduche.getText().toString());
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }

    //**********************************************************************************************
    //TOMAR FOTO
    public void imagenSanduche() {
        ibFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    File foto = null;
                    try {
                        foto = crearFoto();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (foto != null) {
                        Uri fotoURI = FileProvider.getUriForFile(getContext(),
                                "co.vasquez.nodier.sanduchero.fileprovider", foto);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
                        startActivityForResult(intent, CODIGO_FOTO_CAMARA);
                    }
                }
            }
        });

        ibGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(intent,CODIGO_FOTO_GALERIA);
                }
            }
        });

    }

    //CREAR ARCHIVO DE FOTO
    private File crearFoto() throws IOException {
        File directorioImagenes = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File foto = File.createTempFile("JPEG_", ".jgp", directorioImagenes);
        pathFoto = foto.getAbsolutePath();
        return foto;
    }

    //ASIGNAR FOTO TOMADA A UN IMAGE VIEW QUE TENGA EN MI INTERFAZ
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_FOTO_CAMARA && resultCode == getActivity().RESULT_OK) {
            fotoUri = Uri.fromFile(new File(pathFoto));
        }
        if (requestCode == CODIGO_FOTO_GALERIA && resultCode == getActivity().RESULT_OK) {
            fotoUri = data.getData();
        }
    }

    //**********************************************************************************************

}
