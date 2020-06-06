package com.example.monitorRotinaBebe.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.controller.ControllerAtor;
import com.example.monitorRotinaBebe.controller.ControllerFilme;
import com.example.monitorRotinaBebe.fragments.FragmentoEditarRotinaBebe;
import com.example.monitorRotinaBebe.fragments.FragmentoRecyclerRotinaDoDia;
import com.example.monitorRotinaBebe.fragments.FragmentoRegistroEventoBebe;
import com.example.monitorRotinaBebe.fragments.RecyclerFragmentAtor;
import com.example.monitorRotinaBebe.fragments.RecyclerFragmentDiretor;
import com.example.monitorRotinaBebe.fragments.RecyclerFragmentFilme;
import com.example.monitorRotinaBebe.fragments.RegisterPerson;
import com.example.monitorRotinaBebe.fragments.RegisterFilm;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RegisterPerson registerPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this) ;

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        ControllerFilme controllerFilme = ControllerFilme.getInstance();
        ControllerAtor controllerAtor = ControllerAtor.getInstance();
        Log.i("log", String.valueOf(controllerAtor.getListAtor()==null));

        setTitle("Monitor de rotina de bebe");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);


        int menuItem = item.getItemId();

        if(menuItem == R.id.menuAtores){
            initializeFragment(new RecyclerFragmentAtor(this));
        }
        if(menuItem == R.id.menuDiretores){
            initializeFragment(new RecyclerFragmentDiretor(this));
        }
        if(menuItem == R.id.menuFilmes){
            initializeFragment(new RecyclerFragmentFilme(this));
        }
        if(menuItem == R.id.menuCadastrarAtor){
            registerPerson.typeRegister = "Cadastrar Ator";
            initializeFragment(new RegisterPerson(this));
        }

        if(menuItem == R.id.menuItemCadastrarDiretor){
            registerPerson.typeRegister = "Cadastrar Diretor";
            initializeFragment(new RegisterPerson(this));
        }

        if(menuItem == R.id.menuCadastrarFilme){
            initializeFragment(new RegisterFilm(this));
        }

        if(menuItem == R.id.menuRegistrarEvento){
            initializeFragment(new FragmentoRegistroEventoBebe());
        }

        if(menuItem == R.id.menuMostrarRotinaDoDia){
            initializeFragment(new FragmentoRecyclerRotinaDoDia());
        }

        if(menuItem == R.id.menuEditarRotina){
            initializeFragment(new FragmentoEditarRotinaBebe());
        }

        return true;
    }

    private void initializeFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }
}
