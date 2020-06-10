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
import android.view.MenuItem;
import com.example.monitorRotinaBebe.Adapter.AdapterRotina;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.fragments.FragmentoRecyclerRotinaDoDia;
import com.example.monitorRotinaBebe.fragments.FragmentoRegistroEventoBebe;
import com.example.monitorRotinaBebe.fragments.FragmentoRecyclerRotinaEvento;
import com.example.monitorRotinaBebe.fragments.FragmentoRelatorioRotina;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AdapterRotina adapterRotina;
    private DaoEventoBebe daoEventoBebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoEventoBebe = new DaoEventoBebe(this);
        daoEventoBebe.getRotinaDoDia();


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        setTitle("Monitor de rotina de bebe");

        initializeFragment(new FragmentoRecyclerRotinaDoDia(this));
        daoEventoBebe.getRotinaDoDia();
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        int menuItem = item.getItemId();

        if (menuItem == R.id.menuRegistrarEvento) {
            initializeFragment(new FragmentoRegistroEventoBebe(this));
        }

        if (menuItem == R.id.menuMostrarRotinaDoDia) {
            initializeFragment(new FragmentoRecyclerRotinaDoDia(this));
        }
        if (menuItem == R.id.menuDormiu){
            initializeFragment(new FragmentoRecyclerRotinaEvento(this,"Dormiu"));
        }
        if (menuItem == R.id.menuAcordou){
            initializeFragment(new FragmentoRecyclerRotinaEvento(this,"Acordou"));
        }
        if (menuItem == R.id.menuTrocou){
            initializeFragment(new FragmentoRecyclerRotinaEvento(this,"Trocou"));
        }
        if (menuItem == R.id.menuMamou){
            initializeFragment(new FragmentoRecyclerRotinaEvento(this,"Mamou"));
        }
        if(menuItem == R.id.menuRelatorioUltimosDias){
            daoEventoBebe.getAllDatas();
            daoEventoBebe.getAll();
            initializeFragment(new FragmentoRelatorioRotina());
        }

        daoEventoBebe.getRotinaDoDia();
        return true;
    }

    private void initializeFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }


    public  void setAdapterRotina(AdapterRotina adapterRotina) {
        this.adapterRotina = adapterRotina;
    }
}
