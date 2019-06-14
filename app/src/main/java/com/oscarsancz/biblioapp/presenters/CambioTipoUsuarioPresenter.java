package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.CambioTipoUsuarioContract;
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
    usuario.setTipo(nuevoTipo);
    return usuarioRepository.save(usuario);
  }

  @Override
  public List<Usuario> getUsuarios() {
    return usuarioRepository.all(Usuario.class);
  }
}
