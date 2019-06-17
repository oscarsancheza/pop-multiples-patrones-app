package com.oscarsancz.biblioapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oscarsancz.biblioapp.creador.CreadorPresenters;
import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.creador.SimpleFragmentFactory;
import com.oscarsancz.biblioapp.models.TipoPantalla;

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
