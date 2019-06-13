package com.oscarsancz.biblioapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.fragmentFactory;
import com.oscarsancz.biblioapp.helpers.ActivityUtils;
import com.oscarsancz.biblioapp.models.TipoPantalla;

import butterknife.BindView;

public class GeneralActivity extends AppCompatActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private TipoPantalla tipoPantalla;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_general);
    setSupportActionBar(toolbar);

    tipoPantalla =
        TipoPantalla.valueOf(getIntent().getStringExtra(MainActivity.TITULO_PANTALLA_EXTRA));

    Fragment fragment = fragmentFactory.crearFragment(tipoPantalla);
    ActivityUtils.replaceFragment(
        getSupportFragmentManager(), fragment, R.id.content_main, tipoPantalla.toString());
  }
}
