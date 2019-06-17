package com.oscarsancz.biblioapp;

import android.support.v4.app.Fragment;

import com.oscarsancz.biblioapp.fragments.CambioTipoUsuarioFragment;
import com.oscarsancz.biblioapp.fragments.DevolverLibroFragment;
import com.oscarsancz.biblioapp.fragments.MostrarResurtirFragment;
import com.oscarsancz.biblioapp.fragments.PrestamoLibrosFragment;
import com.oscarsancz.biblioapp.models.TipoPantalla;

public class SimpleFragmentFactory {

  public Fragment createFragment(TipoPantalla tipoPantalla) {
    Fragment fragment = null;

    switch (tipoPantalla) {
      case PRESTAMOS:
        fragment = new PrestamoLibrosFragment();
        break;
      case DEVOLUCIONES:
        fragment = new DevolverLibroFragment();
        break;
      case MOSTRAR_TITULO:
        fragment = new MostrarResurtirFragment();
        break;
      case CAMBIAR_USUARIO:
        fragment = new CambioTipoUsuarioFragment();
        break;
    }

    return fragment;
  }
}
