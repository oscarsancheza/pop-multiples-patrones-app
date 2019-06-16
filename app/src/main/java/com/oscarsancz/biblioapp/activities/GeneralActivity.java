package com.oscarsancz.biblioapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oscarsancz.biblioapp.R;
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

        crearFragment(tipoPantalla);
    }

    private void crearFragment(TipoPantalla tipoPantalla) {
        switch (tipoPantalla) {
            case PRESTAMOS:
                PrestamoLibrosFragment prestamoLibrosFragment = new PrestamoLibrosFragment();
                PrestamoLibroPresenter presenter =
                        new PrestamoLibroPresenter(
                                UsuarioRepository.getInstance(),
                                LibrosRepository.getInstance(),
                                prestamoLibrosFragment,
                                this);
                ActivityUtils.replaceFragment(
                        getSupportFragmentManager(),
                        prestamoLibrosFragment,
                        R.id.content_main,
                        tipoPantalla.toString());
                break;
            case DEVOLUCIONES:
                DevolverLibroFragment devolverLibroFragment = new DevolverLibroFragment();
                DevolverLibroPresenter presenterDevolver =
                        new DevolverLibroPresenter(UsuarioRepository.getInstance(),
                                LibrosRepository.getInstance(),
                                devolverLibroFragment,
                                this);
                ActivityUtils.replaceFragment(
                        getSupportFragmentManager(),
                        devolverLibroFragment,
                        R.id.content_main,
                        tipoPantalla.toString());
                break;
            case CAMBIAR_USUARIO:
                CambioTipoUsuarioFragment cambioTipoUsuarioFragment = new CambioTipoUsuarioFragment();
                CambioTipoUsuarioPresenter presenter1 =
                        new CambioTipoUsuarioPresenter(UsuarioRepository.getInstance(), cambioTipoUsuarioFragment, this);
                ActivityUtils.replaceFragment(
                        getSupportFragmentManager(),
                        cambioTipoUsuarioFragment,
                        R.id.content_main,
                        tipoPantalla.toString());
                break;
            case MOSTRAR_TITULO:
                MostrarResurtirFragment mostrarResurtirFragment = new MostrarResurtirFragment();
                MostrarResurtirPresenter mostrarResurtirPresenter =
                        new MostrarResurtirPresenter(SolicitudRepository.getInstance(), mostrarResurtirFragment, this);
                ActivityUtils.replaceFragment(
                        getSupportFragmentManager(),
                        mostrarResurtirFragment,
                        R.id.content_main,
                        tipoPantalla.toString());
                break;
        }
    }
}
