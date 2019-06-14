package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import io.realm.RealmList;

public interface PrestamoLibroContract {
  interface View extends BaseView<PrestamoLibroContract.Presenter> {}

  interface Presenter extends BasePresenter {
    List<Libro> librosPrestar(Usuario usuario, RealmList<Libro> librosPrestar);

    List<Libro> getLibros();

    List<Usuario> getUsuarios();

    void prestar(Usuario usuario);

    void cambiarEstatusLibro(List<Libro> libros);
  }
}
