package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import io.realm.RealmList;

public interface DevolverLibroContract {
    interface View extends BaseView<DevolverLibroContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
        void devolver(Usuario usuario);

        void cambiarEstatusLibro(List<Libro> libros);

        List<Usuario> getUsuarios();
    }
}
