package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.vasquez.nodier.sanduchero.R;

public class HostClienteActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_cliente);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_cli);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }
}
