package com.oscarsancz.biblioapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oscarsancz.biblioapp.CreadorPresenters;
import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.SimpleFragmentFactory;
import com.oscarsancz.biblioapp.fragments.CambioTipoUsuarioFragment;
import com.oscarsancz.biblioapp.fragments.DevolverLibroFragment;
import com.oscarsancz.biblioapp.fragments.MostrarResurtirFragment;
import com.oscarsancz.biblioapp.fragments.PrestamoLibrosFragment;
import com.oscarsancz.biblioapp.helpers.ActivityUtils;
import com.oscarsancz.biblioapp.models.TipoPantalla;
import com.oscarsancz.biblioapp.presenters.CambioTipoUsuarioPresenter;
import com.oscarsancz.biblioapp.presenters.DevolverLibroPresenter;
import com.oscarsancz.biblioapp.presenters.MostrarResurtirPresenter;
import com.oscarsancz.biblioapp.presenters.PrestamoLibroPresenter;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.SolicitudRepository;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralActivity extends AppCompatActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private TipoPantalla tipoPantalla;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_general);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_close_black_24dp);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    tipoPantalla =
        TipoPantalla.valueOf(getIntent().getStringExtra(MainActivity.TITULO_PANTALLA_EXTRA));

    SimpleFragmentFactory simpleFragmentFactory = new SimpleFragmentFactory();
    CreadorPresenters creadorPresenters = new CreadorPresenters(simpleFragmentFactory);
    creadorPresenters.MostrarFragment(tipoPantalla, getSupportFragmentManager(), this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        this.finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
