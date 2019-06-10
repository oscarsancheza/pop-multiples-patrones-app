package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class LibrosRepository extends GenericRepository<Libro> {

  private static LibrosRepository instance;
  private final String TAG = this.getClass().getSimpleName();

  private LibrosRepository() {
    super();
  }

  public static LibrosRepository getInstance() {
    if (instance == null) {
      instance = new LibrosRepository();
    }
    return instance;
  }

  private List<Libro> all() {
    List<Libro> libros = new ArrayList<>();
    try {
      RealmResults<Libro> results =
          mRealm.where(Libro.class).greaterThan("existencia", 0).findAll();
      libros = mRealm.copyFromRealm(results);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }

    return libros;
  }
}
