package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.ListadoPrestamoContract;
import com.oscarsancz.biblioapp.repositories.ListadoPrestamoRepository;

public class ListadoPrestamosPresenter implements ListadoPrestamoContract.Presenter {

  private final ListadoPrestamoContract.View view;
  private final ListadoPrestamoRepository repository;
  private final Context mContext;

  public ListadoPrestamosPresenter(
      @NonNull ListadoPrestamoRepository repository,
      @NonNull ListadoPrestamoContract.View view,
      Context context) {
    this.view = view;
    mContext = context;
    this.repository = repository;
    this.view.setPresenter(this);
  }
}
