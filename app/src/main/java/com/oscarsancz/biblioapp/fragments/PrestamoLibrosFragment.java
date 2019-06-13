package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;

import butterknife.ButterKnife;

public class PrestamoLibrosFragment extends Fragment {

  @SuppressLint("ValidFragment")
  public PrestamoLibrosFragment() {}

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.prestamo_libros_view, container, false);
    getActivity().setTitle(R.string.pantalla_prestamos);
    ButterKnife.bind(this, view);

    return view;
  }
}
