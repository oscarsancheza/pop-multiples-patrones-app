package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;

public interface ListadoPrestamoContract {

  interface View extends BaseView<Presenter> {}

  interface Presenter extends BasePresenter {}
}
