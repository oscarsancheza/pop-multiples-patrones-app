package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import io.realm.RealmList;

public interface PrestamoLibroContract {
    interface View extends BaseView<PrestamoLibroContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
        boolean prestar(Usuario usuario, RealmList<Libro> librosPrestar);
    }
}
