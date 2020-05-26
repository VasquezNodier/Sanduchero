package co.vasquez.nodier.sanduchero.view;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Direcciones;
import co.vasquez.nodier.sanduchero.view.fragmentCli.CartCliFragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng miPosicion;
    private double latitud;
    private double longitud;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText etBuscarDireccion;
    private ImageButton ibBuscarDireccion;
    private Button btnGuardarDireccion;
    private Marker marcador;
    private String direccion;

    private Direcciones direccionEntrega;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        asociarElementos();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.containerMapa);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient
                (this);

        buscarDireccion();



    }

    public void asociarElementos() {
        etBuscarDireccion = findViewById(R.id.et_BuscarDireccion);
        ibBuscarDireccion = findViewById(R.id.ib_BuscarDireccion);
        btnGuardarDireccion = findViewById(R.id.btn_GuardarDireccion);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarmiubicacion();
        //LatLng actual = new LatLng(latitud, longitud);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(actual));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void getdirecciondetalles(double lat, double lon) {

        double latitud = lat;
        double longitud = lon;
        final MarkerOptions usermarkeroptions = new MarkerOptions();

        Geocoder geocoder;
        List<Address> direcciones;
        geocoder = new Geocoder(this, Locale.getDefault());

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

            valoress += direccion + ", " + city + ", " + state + ", " + country + ", " + postalcode + ", " + knonname;

            direccionEntrega = new Direcciones(direccion,city,country,postalcode);

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }

            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
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
                Toast.makeText(this, "Activar manualmente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void buscarDireccion() {
        ibBuscarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.clear();
                direccion = etBuscarDireccion.getText().toString();

                List<Address> listadireccion = null;
                final MarkerOptions usermarkeroptions = new MarkerOptions();

                if (!TextUtils.isEmpty(direccion)) {
                    Geocoder geocoder = new Geocoder(getBaseContext());
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
                                Toast.makeText(getBaseContext(), "Has creado un marcador", Toast.LENGTH_SHORT).show();

            /*Por último se invoca al método encargado de actualizar el movimiento de la cámara y
            el tiempo de duración para actualizar dicho movimiento.*/
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);

                            }

                            if (bander == false) {
                                Toast.makeText(getBaseContext(), "Dirección no encontrada", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getBaseContext(), "Dirección no encontrada", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "por favor escriba una dirección correcta", Toast.LENGTH_SHORT).show();
                }
                etBuscarDireccion.setText("");


            }
        });

    }

    public void guardarDireccion(){
        btnGuardarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MapsActivity.this, CartCliFragment.class);
                intent.putExtra("direccion",direccionEntrega);
                startActivity(intent)*/;

                Bundle bundle = new Bundle();
                bundle.putSerializable("direccion",direccionEntrega);
                CartCliFragment direc = new CartCliFragment();
                direc.setArguments(bundle);

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.cartCliFragment,direc,"fragmento");

            }
        });
    }

}
