package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Direcciones;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;

    private GoogleMap mMap;
    private LatLng miPosicion;
    private double latitud;
    private double longitud;
    private EditText etBuscarDireccion;
    private ImageButton ibBuscarDireccion;
    private Button btnGuardarDireccion;
    private String direccion = CartCliFragment.valorDireccion;
    private Marker marcador;
    private Direcciones direccionEntrega;
    FragmentManager manager;

    public MapaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        asociarElementos(view);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.containerMapa);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mostrarmiubicacion();
        //buscarDireccion();
        guardarDireccion(view);
    }

    public void asociarElementos(View view) {
        etBuscarDireccion = view.findViewById(R.id.et_BuscarDireccion);
        ibBuscarDireccion = view.findViewById(R.id.ib_BuscarDireccion);
        btnGuardarDireccion = view.findViewById(R.id.btn_GuardarDireccion);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarmiubicacion();
        //LatLng actual = new LatLng(latitud, longitud);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(actual));
        if (!direccion.equals("")) {
            buscarDireccion();
            //etBuscarDireccion.setText(direccion);
        }
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        ibBuscarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccion = "";
                buscarDireccion();
            }
        });

    }

    public void getdirecciondetalles(double lat, double lon) {

        double latitud = lat;
        double longitud = lon;
        final MarkerOptions usermarkeroptions = new MarkerOptions();

        Geocoder geocoder;
        List<Address> direcciones;
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        String direccion = "";
        String city = "";
        String state = "";
        String country = "";
        String postalcode = "";
        String knonname = "";
        String valoress = "";

        try {
            direcciones = geocoder.getFromLocation(latitud, longitud, 1);

            direccion = direcciones.get(0).getAddressLine(0);
            city = direcciones.get(0).getLocality();
            state = direcciones.get(0).getAdminArea();
            country = direcciones.get(0).getCountryName();
            postalcode = direcciones.get(0).getPostalCode();
            knonname = direcciones.get(0).getFeatureName();

            valoress += direccion + ", " + postalcode + ", " + knonname;
            CartCliFragment.valorDireccion = direccion;

            direccionEntrega = new Direcciones(direccion, city, country, postalcode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        miPosicion = new LatLng(latitud, longitud);

        usermarkeroptions.position(miPosicion);
        usermarkeroptions.title(city);
        usermarkeroptions.snippet(city + " - " + country + " - " + state);
        usermarkeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        marcador = mMap.addMarker(new MarkerOptions().position(miPosicion).
                title(direccion).snippet(postalcode).icon(BitmapDescriptorFactory.
                defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(miPosicion, 17);
        mMap.animateCamera(cameraUpdate);

    }

    public void mostrarmiubicacion() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }

            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.d("MAPA", "Latitud: " + location.getLatitude()
                                    + " Longitud " + location.getLongitude());
                            latitud = location.getLatitude();
                            longitud = location.getLongitude();
                            getdirecciondetalles(latitud, longitud);
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                // contacts-related task you need to do.
            } else {
                Toast.makeText(getContext(), "Activar manualmente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void buscarDireccion() {

        mMap.clear();

        if (direccion.equals("")) {
            //etBuscarDireccion.setText(direccion);
            direccion = etBuscarDireccion.getText().toString();
        }

        List<Address> listadireccion = null;
        final MarkerOptions usermarkeroptions = new MarkerOptions();

        if (!TextUtils.isEmpty(direccion)) {
            Geocoder geocoder = new Geocoder(getContext());
            boolean bander = false;
            try {
                listadireccion = geocoder.getFromLocationName(direccion, 6);

                if (listadireccion != null) {

                    for (int i = 0; i < listadireccion.size(); i++) {

                        bander = true;
                        Address useradres = listadireccion.get(i);
                        latitud = useradres.getLatitude();
                        longitud = useradres.getLongitude();
                        LatLng latilog = new LatLng(latitud, longitud);
                        usermarkeroptions.position(latilog);
                        //usermarkeroptions.title(direccion);
                        getdirecciondetalles(latitud, longitud);

                        usermarkeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                        marcador = mMap.addMarker(new MarkerOptions().position(latilog).title(direccion).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latilog, 20));
                        CameraPosition cameraPosition = CameraPosition.builder()
                                .target(latilog)
                                .zoom(16.0f)
                                .tilt(45.0f)
                                .bearing(45.0f)
                                .build();
                        Toast.makeText(getContext(), "Has creado un marcador", Toast.LENGTH_SHORT).show();

            /*Por último se invoca al método encargado de actualizar el movimiento de la cámara y
            el tiempo de duración para actualizar dicho movimiento.*/
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);

                    }

                    if (bander == false) {
                        Toast.makeText(getContext(), "Dirección no encontrada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Dirección no encontrada", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "por favor escriba una dirección correcta", Toast.LENGTH_SHORT).show();
        }
        etBuscarDireccion.setText("");

    }

    public void guardarDireccion(final View view) {

        btnGuardarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final NavController navController = Navigation.findNavController(view);
                navController.navigate(MapaFragmentDirections.actionMapaFragmentToCartCliFragment(direccionEntrega));

            }
        });
    }

}
