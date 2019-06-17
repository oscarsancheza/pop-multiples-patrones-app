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
import com.oscarsancz.biblioapp.models.TipoPantalla;
import com.oscarsancz.biblioapp.models.listas.InicializarBD;
import com.oscarsancz.biblioapp.presenters.ListadoPrestamosPresenter;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

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
  InicializarBD inicializarBD;
  public static final String TITULO_PANTALLA_EXTRA = "tipopantalla";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    inicializarBD = new InicializarBD();
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
        new ListadoPrestamosPresenter(UsuarioRepository.getInstance(), vista, this);
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

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_ini_sistema) {
      inicializarBD.inicializar();
    } else if (id == R.id.nav_prestar_libro) {
      ActivityUtils.createIntent(
          this, GeneralActivity.class, TITULO_PANTALLA_EXTRA, TipoPantalla.PRESTAMOS.toString());
    } else if (id == R.id.nav_devolver_libro) {
      ActivityUtils.createIntent(
          this, GeneralActivity.class, TITULO_PANTALLA_EXTRA, TipoPantalla.DEVOLUCIONES.toString());
    } else if (id == R.id.nav_cambiar_tipo_usuario) {
      ActivityUtils.createIntent(
          this,
          GeneralActivity.class,
          TITULO_PANTALLA_EXTRA,
          TipoPantalla.CAMBIAR_USUARIO.toString());
    } else if (id == R.id.nav_libro_resurtir) {
      ActivityUtils.createIntent(
          this,
          GeneralActivity.class,
          TITULO_PANTALLA_EXTRA,
          TipoPantalla.MOSTRAR_TITULO.toString());
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
