package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.SelectionKey;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrestamoLibrosFragment extends Fragment implements PrestamoLibroContract.View {

  @BindView(R.id.prestar_btn)
  Button prestarBtn;

  private PrestamoLibroContract.Presenter presenter;
  private List<SelectionKey> selectionKeys;
  private Usuario usuario;

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

    return view;
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
    if (selectionKeys == null || selectionKeys.isEmpty()) {
      List<Libro> libros = presenter.getLibros();
      selectionKeys = crearList(libros);
    }

    DialogFragmentMultipleChoice dialog = new DialogFragmentMultipleChoice();
    dialog.setItems(selectionKeys, items -> {});

    dialog.show(getActivity().getFragmentManager(), "librosDialog");
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
