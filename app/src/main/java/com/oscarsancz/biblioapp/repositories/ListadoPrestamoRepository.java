package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import io.realm.Realm;

public class ListadoPrestamoRepository {

  private static ListadoPrestamoRepository instance;
  private final String TAG = this.getClass().getSimpleName();
  private Realm mRealm;

  private ListadoPrestamoRepository() {
    try {
      this.mRealm = Realm.getDefaultInstance();
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
  }

  public static ListadoPrestamoRepository getInstance() {
    if (instance == null) {
      instance = new ListadoPrestamoRepository();
    }
    return instance;
  }
}
