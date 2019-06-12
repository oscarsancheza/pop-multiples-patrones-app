package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

public interface CambioTipoUsuarioContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        boolean cambioUsuario(Usuario usuario, TipoUsuario nuevoTipo);
    }
}
