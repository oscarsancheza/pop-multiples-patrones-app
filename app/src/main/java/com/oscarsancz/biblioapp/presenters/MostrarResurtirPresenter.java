package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.MostrarResurtirContract;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.repositories.SolicitudRepository;

import java.util.List;

public class MostrarResurtirPresenter implements MostrarResurtirContract.Presenter {
    private SolicitudRepository solicitudRepository;

    public MostrarResurtirPresenter(@NonNull SolicitudRepository solicitudRepository,
                                    @NonNull MostrarResurtirContract.View view,
                                    Context context) {
        this.solicitudRepository = solicitudRepository;
        view.setPresenter(this);
    }

    @Override
    public List<Solicitud> getData() {
        return solicitudRepository.all(Solicitud.class);
    }
}
