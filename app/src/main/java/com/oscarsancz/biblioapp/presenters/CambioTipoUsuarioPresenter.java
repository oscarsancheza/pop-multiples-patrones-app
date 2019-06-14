package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.CambioTipoUsuarioContract;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.List;

public class CambioTipoUsuarioPresenter implements CambioTipoUsuarioContract.Presenter {
    private UsuarioRepository usuarioRepository;

    public CambioTipoUsuarioPresenter(
            @NonNull UsuarioRepository repository,
            @NonNull CambioTipoUsuarioContract.View view,
            Context context) {
        usuarioRepository = repository;
        view.setPresenter(this);
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

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.all(Usuario.class);
    }
}
