package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.adapters.PrestamosAdapter;
import com.oscarsancz.biblioapp.contracts.ListadoPrestamoContract;
import com.oscarsancz.biblioapp.helpers.SimpleDividerItemDecoration;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ListadoPrestamoFragment extends Fragment implements ListadoPrestamoContract.View {

  @BindView(R.id.contenedor_prestamos)
  RecyclerView recyclerView;

  @BindView(R.id.empty_view)
  TextView textoEnVacio;

  private ListadoPrestamoContract.Presenter presenter;
  private RecyclerView.LayoutManager layoutManager;
  private PrestamosAdapter adapter;

  @SuppressLint("ValidFragment")
  public ListadoPrestamoFragment() {}

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.listado_prestamo_view, container, false);
    getActivity().setTitle(R.string.libros_prestados);
    ButterKnife.bind(this, view);

    initRecyclerView();
    mostrarDatos();

    return view;
  }

  private void mostrarDatos() {
    List<Usuario> usuarios = presenter.getData();
    if (usuarios == null || usuarios.isEmpty()) {
      recyclerView.setVisibility(View.GONE);
      textoEnVacio.setVisibility(View.VISIBLE);
    } else {
      recyclerView.setVisibility(View.VISIBLE);
      textoEnVacio.setVisibility(View.GONE);
      adapter.addItems(usuarios);
    }
  }

  private void initRecyclerView() {
    layoutManager = new LinearLayoutManager(getContext());
    adapter = new PrestamosAdapter(getContext(), layoutManager, recyclerView);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

    adapter.addOnItemClickListener(
        new DefaultAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(int position, Object model) {}

          @Override
          public boolean onItemLongClick(int position, Object model) {
            return false;
          }
        });
  }

  @Override
  public void setPresenter(ListadoPrestamoContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
