package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.adapters.LibroAdapter;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
import com.oscarsancz.biblioapp.helpers.SimpleDividerItemDecoration;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.SelectionKey;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

public class PrestamoLibrosFragment extends Fragment implements PrestamoLibroContract.View {

  @BindView(R.id.prestar_btn)
  Button prestarBtn;

  @BindView(R.id.contenedor_libros)
  RecyclerView recyclerView;

  @BindView(R.id.scroll_view)
  NestedScrollView view;

  private PrestamoLibroContract.Presenter presenter;
  private List<SelectionKey> selectionKeys;
  private Usuario usuario;
  private RecyclerView.LayoutManager layoutManager;
  private LibroAdapter adapter;
  private List<Libro> libros = new ArrayList<>();

  @BindView(R.id.usuario_component)
  EditText usuarioComponent;

  @SuppressLint("ValidFragment")
  public PrestamoLibrosFragment() {}

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.prestamo_libros_view, container, false);
    getActivity().setTitle(R.string.pantalla_prestamos);
    ButterKnife.bind(this, view);

    usuarioComponent.setFocusable(false);
    usuarioComponent.setOnFocusChangeListener(
        (v, hasFocus) -> {
          if (v.isFocused()) {
            usuarioComponent();
          }
        });

    initRecyclerView();

    return view;
  }

  private void initRecyclerView() {
    layoutManager = new LinearLayoutManager(getContext());
    adapter = new LibroAdapter(getContext(), layoutManager, recyclerView);

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

  @OnClick(R.id.prestar_btn)
  public void prestarAccion() {
    if (usuario != null && adapter != null && !adapter.getItems().isEmpty()) {
      RealmList<Libro> librosPrestar = new RealmList<>();
      librosPrestar.addAll(adapter.getItems());
      usuario.setLibros(librosPrestar);
      presenter.cambiarEstatusLibro(librosPrestar);
      presenter.prestar(usuario);
      new AlertDialog.Builder(getContext())
          .setTitle("Atención")
          .setPositiveButton(
              "Aceptar",
              (dialogInterface, i) -> {
                Intent intentResult = getActivity().getIntent();
                getActivity().setResult(Activity.RESULT_OK, intentResult);
                getActivity().finish();
              })
          .setMessage("Se realizó el prestamo con éxito.")
          .setCancelable(false)
          .show();

    } else {
      mostrarMensaje("los campos usaurio y libros son obligatorios");
    }
  }

  @OnClick(R.id.usuario_component)
  public void usuarioComponent() {
    List<Usuario> usuarios = presenter.getUsuarios();
    UsuarioDialogFragment dialog = new UsuarioDialogFragment();
    dialog.setItems(
        getContext(),
        usuarios,
        usuario -> {
          this.usuario = usuario;
          usuarioComponent.setText(usuario.getNombreCompleto());
        });
    dialog.show(getActivity().getFragmentManager(), "usuarioDialog");
  }

  @OnClick(R.id.libro_component)
  public void libroComponent() {
    if (usuario != null) {

      if (selectionKeys == null || selectionKeys.isEmpty()) {
        libros = presenter.getLibros();
        selectionKeys = crearList(libros);
      }

      DialogFragmentMultipleChoice dialog = new DialogFragmentMultipleChoice();
      dialog.setItems(
          selectionKeys,
          items -> {
            if (adapter.getItems().size() > 0) {
              adapter.removeAll();
            }
            RealmList<Libro> librosList = getLibrosFromSelectedKey(selectionKeys, this.libros);
            List<Libro> librosPrestar = presenter.librosPrestar(usuario, librosList);
            adapter.addItems(librosPrestar);
          });

      dialog.show(getActivity().getFragmentManager(), "librosDialog");
    } else {
      mostrarMensaje("Es necesario seleccionar primero un usuario.");
    }
  }

  private void mostrarMensaje(String mensaje) {
    new AlertDialog.Builder(getContext())
        .setTitle("Atención")
        .setPositiveButton("Aceptar", (dialogInterface, i) -> dialogInterface.dismiss())
        .setMessage(mensaje)
        .show();
  }

  private RealmList<Libro> getLibrosFromSelectedKey(
      List<SelectionKey> selectionKeys, List<Libro> librosP) {
    RealmList<Libro> resultado = new RealmList<>();

    if (selectionKeys != null && librosP != null) {
      for (SelectionKey item : selectionKeys) {
        if (item.isSelected()) {
          resultado.add(librosP.get(item.getId()));
        }
      }
    }

    return resultado;
  }

  private List<SelectionKey> crearList(List<Libro> libros) {
    List<SelectionKey> selectionKeys = new ArrayList<>();
    if (libros != null && !libros.isEmpty()) {
      SelectionKey selectionKey;
      int i = 0;
      for (Libro item : libros) {
        selectionKey = new SelectionKey();
        selectionKey.setId(i);
        selectionKey.setDescripcion(item.getTitulo());
        selectionKey.setSelected(false);
        selectionKeys.add(selectionKey);
        i++;
      }
    }
    return selectionKeys;
  }

  @Override
  public void setPresenter(PrestamoLibroContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
