package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.List;

import io.realm.RealmList;

public interface DevolverLibroContract {
    interface View extends BaseView<DevolverLibroContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
        RealmList<Libro> getLibrosUsuario(int id);

        boolean devolverLibros(List<Libro> librosDevolver);

        boolean updateLibrosUsuario(int id, RealmList<Libro> librosUsuario);
    }
}
