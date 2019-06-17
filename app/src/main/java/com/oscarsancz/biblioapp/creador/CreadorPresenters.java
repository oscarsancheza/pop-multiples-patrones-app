package com.oscarsancz.biblioapp.creador;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

public class CreadorPresenters {

  SimpleFragmentFactory simpleFragmentFactory;

  public CreadorPresenters(SimpleFragmentFactory simpleFragmentFactory) {
    this.simpleFragmentFactory = simpleFragmentFactory;
  }

  public void MostrarFragment(
      TipoPantalla tipoPantalla, FragmentManager fragmentManager, Context context) {

    Fragment fragment = simpleFragmentFactory.createFragment(tipoPantalla);

    switch (tipoPantalla) {
      case PRESTAMOS:
        PrestamoLibroPresenter presenter =
            new PrestamoLibroPresenter(
                UsuarioRepository.getInstance(),
                LibrosRepository.getInstance(),
                (PrestamoLibrosFragment) fragment,
                context);
        break;
      case DEVOLUCIONES:
        DevolverLibroPresenter presenterDevolver =
            new DevolverLibroPresenter(
                UsuarioRepository.getInstance(),
                LibrosRepository.getInstance(),
                (DevolverLibroFragment) fragment,
                context);
        break;
      case CAMBIAR_USUARIO:
        CambioTipoUsuarioPresenter cambioTipoUsuarioPresenter =
            new CambioTipoUsuarioPresenter(
                UsuarioRepository.getInstance(), (CambioTipoUsuarioFragment) fragment, context);
        break;
      case MOSTRAR_TITULO:
        MostrarResurtirPresenter mostrarResurtirPresenter =
            new MostrarResurtirPresenter(
                SolicitudRepository.getInstance(), (MostrarResurtirFragment) fragment, context);
        break;
    }

    if (fragment != null) {
      ActivityUtils.replaceFragment(
          fragmentManager, fragment, R.id.content_main, tipoPantalla.toString());
    }
  }
}
