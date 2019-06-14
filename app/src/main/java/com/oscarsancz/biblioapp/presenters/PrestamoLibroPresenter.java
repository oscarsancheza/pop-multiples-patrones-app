package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.Prestamo.Prestar;
import com.oscarsancz.biblioapp.Prestamo.PrestarEstudiante;
import com.oscarsancz.biblioapp.Prestamo.PrestarProfesor;
import com.oscarsancz.biblioapp.Prestamo.PrestarPublico;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.List;

import io.realm.RealmList;

public class PrestamoLibroPresenter implements PrestamoLibroContract.Presenter {
  private UsuarioRepository usuarioRepository;
  private LibrosRepository librosRepository;

  public PrestamoLibroPresenter(
      @NonNull UsuarioRepository repository,
      @NonNull LibrosRepository librosRepository,
      @NonNull PrestamoLibroContract.View view,
      Context context) {
    usuarioRepository = repository;
    this.librosRepository = librosRepository;
    view.setPresenter(this);
  }

  @Override
  public List<Libro> librosPrestar(Usuario usuario, RealmList<Libro> librosPrestar) {
    Prestar prestar;
    RealmList<Libro> libros;

    if (usuario.getTipo() == TipoUsuario.PROFESOR) {
      prestar = new PrestarProfesor();
      libros = prestar.crearPrestamo(librosPrestar);
    } else if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
      prestar = new PrestarEstudiante();
      libros = prestar.crearPrestamo(librosPrestar);
    } else {
      prestar = new PrestarPublico();
      libros = prestar.crearPrestamo(librosPrestar);
    }

    return libros;
  }

  @Override
  public List<Libro> getLibros() {
    return librosRepository.all();
  }

  @Override
  public List<Usuario> getUsuarios() {
    return usuarioRepository.all(Usuario.class);
  }

  @Override
  public void prestar(Usuario usuario) {
    usuarioRepository.save(usuario);
  }

  @Override
  public void cambiarEstatusLibro(List<Libro> libros) {
    for (Libro item : libros) {
      item.setStatus(DisponibilidadLibro.PRESTADO);
      librosRepository.save(item);
    }
  }
}
