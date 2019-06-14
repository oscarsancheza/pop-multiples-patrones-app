package com.oscarsancz.biblioapp;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.oscarsancz.biblioapp.fragments.PrestamoLibroFragment;
import com.oscarsancz.biblioapp.fragments.PrestamoLibrosFragment;
import com.oscarsancz.biblioapp.models.TipoPantalla;
import com.oscarsancz.biblioapp.presenters.PrestamoLibroPresenter;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

public class fragmentFactory {

  public static Fragment crearFragment(TipoPantalla tipo, Context context) {
    Fragment fragment = null;
    if (tipo.equals(TipoPantalla.PRESTAMOS)) {
      fragment = new PrestamoLibrosFragment();
      PrestamoLibroPresenter presenter =
          new PrestamoLibroPresenter(
              UsuarioRepository.getInstance(), LibrosRepository.getInstance(), new PrestamoLibroFragment(), context);
    }
    return fragment;
  }
}
