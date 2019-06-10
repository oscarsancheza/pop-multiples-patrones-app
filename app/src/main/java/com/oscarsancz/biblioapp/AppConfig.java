package com.oscarsancz.biblioapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppConfig extends Application {

  private final String DBNAME = "biblioapp.realm";
  private final String TAG = getClass().getSimpleName();
  public static final int SCHEMA_VERSION_NEW = 1;

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(this);
    RealmConfiguration realmConfiguration =
        new RealmConfiguration.Builder().name(DBNAME).schemaVersion(SCHEMA_VERSION_NEW).build();
    Realm.setDefaultConfiguration(realmConfiguration);
  }
}
