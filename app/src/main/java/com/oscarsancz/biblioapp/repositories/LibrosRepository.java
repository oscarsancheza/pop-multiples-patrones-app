package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class LibrosRepository {

  private static LibrosRepository instance;
  private final String TAG = this.getClass().getSimpleName();
  private Realm mRealm;

  private LibrosRepository() {
    try {
      this.mRealm = Realm.getDefaultInstance();
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
  }

  public static LibrosRepository getInstance() {
    if (instance == null) {
      instance = new LibrosRepository();
    }
    return instance;
  }

  public boolean save(RealmList<Libro> libros) {
    try {
      mRealm.executeTransaction((realm) -> realm.insertOrUpdate(libros));
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return false;
    }
    return true;
  }

  public boolean save(Libro libro) {
    try {
      mRealm.executeTransaction((realm) -> realm.insertOrUpdate(libro));
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return false;
    }
    return true;
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
