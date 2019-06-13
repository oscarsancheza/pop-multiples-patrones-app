package com.oscarsancz.biblioapp.presenters;

import com.oscarsancz.biblioapp.contracts.CambioTipoUsuarioContract;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

public class CambioTipoUsuarioPresenter implements CambioTipoUsuarioContract.Presenter {
    UsuarioRepository usuarioRepository;

    public CambioTipoUsuarioPresenter() {
        usuarioRepository = UsuarioRepository.getInstance();

    }

    @Override
    public boolean cambioUsuario(Usuario usuario, TipoUsuario nuevoTipo) {
        boolean respuesta;
        if (usuario.getTipo() == TipoUsuario.PROFESOR && nuevoTipo != TipoUsuario.PUBLICO_GENERAL) {
            respuesta = false;
        } else {
            respuesta = usuarioRepository.cambiar(usuario.getId(), nuevoTipo);
        }
        return respuesta;
    }
}
