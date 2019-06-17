package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public interface ListadoPrestamoContract {

  interface View extends BaseView<Presenter> {}

  interface Presenter extends BasePresenter {
    RealmResults<Usuario> getData();
  }
}
