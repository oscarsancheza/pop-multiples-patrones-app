package com.oscarsancz.biblioapp;

import android.support.v4.app.Fragment;

import com.oscarsancz.biblioapp.fragments.PrestamoLibrosFragment;
import com.oscarsancz.biblioapp.models.TipoPantalla;

public class fragmentFactory {

  public static Fragment crearFragment(TipoPantalla tipo) {
    Fragment fragment = null;
    if (tipo.equals(TipoPantalla.PRESTAMOS)) {
      fragment = new PrestamoLibrosFragment();
    }
    return fragment;
  }
}
