package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.ProductoPedir;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSanducheFragment extends Fragment {

    private TextView txNombreSanduche, txPrecioSanduche, txCantidad;
    private ListView lvIngredienteSan;
    private Button btnAgregarCarrito;
    private ImageView imgSan, imgRestar, imgAdicionar;
    ArrayList<String> ingrediente;
    private int cantidad = 0;

    public static List<ProductoPedir> lista_producto_sanduche = new ArrayList<>();;
    private ProductoPedir productoPedir;

    public DetalleSanducheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_sanduche, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        final Sanduches sanduche = DetalleSanducheFragmentArgs.fromBundle(requireArguments()).getMiSanduche();

        final String nombre = sanduche.getNombre();
        final int precio = sanduche.getPrecio();
        final String categoria = sanduche.getCategoria();
        final int[] total = {0};
        String imagen = sanduche.getImagen();
        Picasso.get().load(imagen).into(imgSan);
        txNombreSanduche.setText(nombre);

        txPrecioSanduche.setText("$ " + precio);
        imgAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad += 1;
                txCantidad.setText("" + cantidad);
                total[0] = precio*cantidad;
                txPrecioSanduche.setText("$ " + total[0]);
            }
        });

        imgRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad -= 1;
                if (cantidad < 1) {
                    cantidad = 1;
                    txPrecioSanduche.setText("$ " + precio);
                } else {
                    txCantidad.setText("" + cantidad);
                    txPrecioSanduche.setText("$ " + total[0]);
                }
            }
        });

        ingrediente = new ArrayList<>();
        // ASIGNO MI LISTA DE MAP DE INGREDIENTES AL LIST VIEW
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.
                simple_list_item_1, sanduche.getId_ingredientes());
        lvIngredienteSan.setAdapter(arrayAdapter);

        btnAgregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cantidad > 0) {
                    Navigation.findNavController(getView()).navigate(R.id.menuCliFragment);
                    Toast.makeText(getContext(), "Se agregó al carrito", Toast.LENGTH_SHORT).show();

                    int totalsan = total[0];
                    productoPedir = new ProductoPedir(nombre,categoria,totalsan,cantidad);

                } else {
                    Toast.makeText(getContext(), "Debes agregar al menos un producto", Toast.LENGTH_SHORT).show();
                }
                lista_producto_sanduche.add(productoPedir);
                Log.d("CARRO", "Lista_Producto "+ lista_producto_sanduche);
            }
        });



        for (int i = 0; i < lista_producto_sanduche.size(); i++) {
            Log.d("CARRO", "Producto "+(i+1)+": "+ lista_producto_sanduche.get(i));
        }

    }

    public void asociarElementos(View view) {
        txNombreSanduche = view.findViewById(R.id.tx_NombreSanduche);
        txPrecioSanduche = view.findViewById(R.id.tx_PrecioSanduche);
        lvIngredienteSan = view.findViewById(R.id.lv_IngredienteSan);
        imgSan = view.findViewById(R.id.img_San);
        imgAdicionar = view.findViewById(R.id.img_Adicionar);
        imgRestar = view.findViewById(R.id.img_Restar);
        txCantidad = view.findViewById(R.id.tx_Cantidad);
        btnAgregarCarrito = view.findViewById(R.id.btn_AgregarCarrito);
    }
}
