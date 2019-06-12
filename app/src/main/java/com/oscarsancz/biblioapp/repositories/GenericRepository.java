package com.oscarsancz.biblioapp.repositories;

import android.util.Log;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class GenericRepository<T extends RealmObject> {

  private final String TAG = this.getClass().getSimpleName();
  protected Realm mRealm;

  public GenericRepository() {
    try {
      this.mRealm = Realm.getDefaultInstance();
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
  }

  public boolean save(List<T> objects) {
    try {
      mRealm.executeTransaction((realm) -> realm.insertOrUpdate(objects));
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return false;
    }
    return true;
  }

  public boolean save(T object) {
    try {
      mRealm.executeTransaction((realm) -> realm.insertOrUpdate(object));
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return false;
    }
    return true;
  }

  public List<T> getAll(Class<T> tClass) {
    List<T> objects = new ArrayList<>();
    try {
      RealmResults<T> results = mRealm.where(tClass).findAll();
      objects = mRealm.copyFromRealm(results);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }

    return objects;
  }

  public T find(int id, String searchKey,Class<T> tClass){
    T object = null;
    try {
      object =
              mRealm
                      .where(tClass)
                      .equalTo(searchKey, id)
                      .findFirst();
      object = mRealm.copyFromRealm(object);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
    return object;
  }

}
