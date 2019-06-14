package com.oscarsancz.biblioapp.contracts;

import com.oscarsancz.biblioapp.helpers.BasePresenter;
import com.oscarsancz.biblioapp.helpers.BaseView;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;

import java.util.List;

public interface MostrarResurtirContract {
    interface View extends BaseView<MostrarResurtirContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
        List<Solicitud> getData();
    }
}
