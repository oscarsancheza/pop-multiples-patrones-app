package com.oscarsancz.biblioapp.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.fragments.ListadoPrestamoFragment;
import com.oscarsancz.biblioapp.helpers.ActivityUtils;
import com.oscarsancz.biblioapp.presenters.ListadoPrestamosPresenter;
import com.oscarsancz.biblioapp.repositories.ListadoPrestamoRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;

  @BindView(R.id.nav_view)
  NavigationView navigationView;

  private final String LISTADO_PRESTAMOS_TAG = "listadoPrestamos";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    setSupportActionBar(toolbar);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);

    ListadoPrestamoFragment vista = new ListadoPrestamoFragment();

    ActivityUtils.replaceFragment(
        getSupportFragmentManager(), vista, R.id.content_main, LISTADO_PRESTAMOS_TAG);

    ListadoPrestamosPresenter presenter =
        new ListadoPrestamosPresenter(ListadoPrestamoRepository.getInstance(), vista, this);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_ini_sistema) {
      // Handle the camera action
    } else if (id == R.id.nav_prestar_libro) {

    } else if (id == R.id.nav_devolver_libro) {

    } else if (id == R.id.nav_cambiar_tipo_usuario) {

    } else if (id == R.id.nav_libro_resurtir) {

    } else if (id == R.id.nav_configuracion) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
