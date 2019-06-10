package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.ListadoPrestamoContract;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ListadoPrestamoFragment extends Fragment implements ListadoPrestamoContract.View {

  @BindView(R.id.contenedor_prestamos)
  RecyclerView recyclerView;

  @BindView(R.id.empty_view)
  TextView textoEnVacio;

  private ListadoPrestamoContract.Presenter presenter;

  @SuppressLint("ValidFragment")
  public ListadoPrestamoFragment() {}

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.listado_prestamo_view, container, false);
    getActivity().setTitle(R.string.libros_prestados);
    ButterKnife.bind(this, view);

    if (true) {
      recyclerView.setVisibility(View.GONE);
      textoEnVacio.setVisibility(View.VISIBLE);
    } else {
      recyclerView.setVisibility(View.VISIBLE);
      textoEnVacio.setVisibility(View.GONE);
    }

    return view;
  }

  @Override
  public void setPresenter(ListadoPrestamoContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
