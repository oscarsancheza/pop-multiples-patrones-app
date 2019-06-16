package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.EstadoLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.ArrayList;
import java.util.List;

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

  public List<Libro> all() {
    List<Libro> libros = new ArrayList<>();
    try {
      RealmResults<Libro> results =
          mRealm
              .where(Libro.class)
              .equalTo("status", DisponibilidadLibro.DISPONIBLE.toString())
              .distinct("isbn")
              .findAll();
      libros = mRealm.copyFromRealm(results);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }

    return libros;
  }

  public List<Libro> getAllByIsbn(Long isbn){
    List<Libro> libros = new ArrayList<>();
    try{
      RealmResults<Libro> results =
              mRealm.where(Libro.class).equalTo("isbn", isbn).equalTo("status", DisponibilidadLibro.DISPONIBLE.toString()).
                      findAll();
      libros = mRealm.copyFromRealm(results);

    }catch (Exception e){
      Log.e(TAG, e.toString());
    }
    return libros;
  }

  public Libro find(Long isbn, EstadoLibro estadoLibro, DisponibilidadLibro disponibilidadLibro) {
    Libro libro = null;
    try {
      libro =
          mRealm
              .where(Libro.class)
              .equalTo("isbn", isbn)
              .equalTo("estado", estadoLibro.toString())
              .equalTo("status", disponibilidadLibro.toString())
              .findFirst();
      libro = mRealm.copyFromRealm(libro);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
    return libro;
  }
}
