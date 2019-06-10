package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

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

  public List<Usuario> getAllWithBooks() {
    List<Usuario> usuarios = new ArrayList<>();
    try {
      RealmResults<Usuario> results =
          mRealm.where(Usuario.class).isNotNull("libros").isNotEmpty("libros").findAll();
      usuarios = mRealm.copyFromRealm(results);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }

    return usuarios;
  }
}
