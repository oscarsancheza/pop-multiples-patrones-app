package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import io.realm.RealmList;
import io.realm.RealmResults;

public class UsuarioRepository extends GenericRepository<Usuario> {

  private static UsuarioRepository instance;
  private final String TAG = this.getClass().getSimpleName();

  private UsuarioRepository() {
    super();
  }

  public static UsuarioRepository getInstance() {
    if (instance == null) {
      instance = new UsuarioRepository();
    }
    return instance;
  }

  public RealmResults<Usuario> getAllWithBooks() {
    RealmResults<Usuario> usuarios = null;
    try {
      usuarios = mRealm.where(Usuario.class).isNotEmpty("libros").findAll();
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }

    return usuarios;
  }

  public RealmList<Libro> getLibros(int id) {
    RealmList<Libro> libros = new RealmList<>();

    Usuario usuario = this.find(id, "id", Usuario.class);

    if (usuario.getLibros() != null && !usuario.getLibros().isEmpty()) {
      libros = usuario.getLibros();
    }

    return libros;
  }

  public boolean cambiar(int id, TipoUsuario tipoUsuario) {
    Usuario usuario = this.find(id, "id", Usuario.class);
    usuario.setTipo(tipoUsuario);
    return this.save(usuario);
  }
}
