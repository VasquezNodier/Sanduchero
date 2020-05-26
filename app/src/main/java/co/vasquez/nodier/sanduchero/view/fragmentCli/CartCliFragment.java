package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Consumer;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Direcciones;
import co.vasquez.nodier.sanduchero.model.entity.ProductoPedir;
import co.vasquez.nodier.sanduchero.view.MapsActivity;
import co.vasquez.nodier.sanduchero.view.adapter.CarritoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartCliFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 2;
    private RecyclerView rvCarrito;
    private ArrayList productos = (ArrayList<ProductoPedir>) DetalleSanducheFragment.lista_producto_sanduche;
    private CarritoAdapter carritoAdapter;
    private ProductoPedir productoPedir;
    private TextView txTotalPedido, txDireccion;
    private int totalPedido;
    public static String valorDireccion = "";

    public CartCliFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_cli, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (productos == null) {
            Toast.makeText(getContext(), "No tienes productos agregados", Toast.LENGTH_SHORT).show();
        } else {
            //OBTENER EL TOATAL DEL PEDIDO
            for (int i = 0; i < productos.size(); i++) {
                productoPedir = (ProductoPedir) productos.get(i);
                Log.d("CARRITO", "Prueba rara de sanduche " + productoPedir.getNombre() + ": " + productoPedir.getPrecio());
                totalPedido += productoPedir.getPrecio();
            }
        }
        txTotalPedido.setText("$ " + totalPedido);
        mostrarProductos();
        buscarDireccion();

        if (valorDireccion != null) txDireccion.setText(valorDireccion);
    }


    public void mostrarProductos() {

        carritoAdapter = new CarritoAdapter(productos);

        carritoAdapter.setMiEscuchador(new CarritoAdapter.NombreDeInterface() {
            @Override
            public void metodoOnClick(ProductoPedir productoPedir, int posicion) {
                Toast.makeText(getContext(), "perrito " + productoPedir.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvCarrito.setLayoutManager(manager);
        rvCarrito.setAdapter(carritoAdapter);
        rvCarrito.setHasFixedSize(true);
    }

    public void buscarDireccion() {
        txDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);*/

                // La App esta en ejecución
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Explicamos porque necesitamos el permiso
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)) {


                    } else {

                        // El usuario no necesitas explicación, puedes solicitar el permiso:
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                        //
                    }
                } else {
                    Navigation.findNavController(v).navigate(R.id.mapaFragment);
                }


            }
        });
    }

    public void asociarElementos(View view) {
        txTotalPedido = view.findViewById(R.id.tx_Total_Pedido);
        rvCarrito = view.findViewById(R.id.rv_Carrito);
        txDireccion = view.findViewById(R.id.tx_Direccion);
    }

}
