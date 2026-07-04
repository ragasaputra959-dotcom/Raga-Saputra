package com.example.siakad_ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Bottom Navigation View
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);

        // Inisialisasi NavHostFragment dari layout activity_main.xml
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            // Menghubungkan BottomNavigationView dengan NavController agar navigasi antar fragment otomatis
            NavigationUI.setupWithNavController(navView, navController);
        }
    }
}
