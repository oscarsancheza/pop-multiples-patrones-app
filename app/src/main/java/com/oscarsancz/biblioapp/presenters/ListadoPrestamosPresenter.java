package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.ListadoPrestamoContract;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.List;

public class ListadoPrestamosPresenter implements ListadoPrestamoContract.Presenter {

  private final ListadoPrestamoContract.View view;
  private final UsuarioRepository repository;
  private final Context mContext;

  public ListadoPrestamosPresenter(
      @NonNull UsuarioRepository repository,
      @NonNull ListadoPrestamoContract.View view,
      Context context) {
    this.view = view;
    mContext = context;
    this.repository = repository;
    this.view.setPresenter(this);
  }


  @Override
  public List<Usuario> getData() {
    return repository.getAllWithBooks();
  }
}
