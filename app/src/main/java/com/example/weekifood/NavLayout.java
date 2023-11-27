package com.example.weekifood;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class NavLayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawner_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IncioFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_inicio){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IncioFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_informacion) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InformacionFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_guardar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GuardadosFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoritosFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_recetas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecetasFragment()).commit();
        }
        else if(item.getItemId()==R.id.nav_cafeteria){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CafeteriaFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_votaciones) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VotacionesFragment()).commit();
        }
        else if (item.getItemId()==R.id.nav_logout) {
            Toast.makeText(this, "Se ha cerrado la sesion!",Toast.LENGTH_SHORT).show();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();

            Intent intent = new Intent(NavLayout.this,MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}