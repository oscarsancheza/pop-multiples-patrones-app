package com.oscarsancz.biblioapp.presenters;

import com.oscarsancz.biblioapp.contracts.MostrarResurtirContract;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.repositories.SolicitudRepository;

import java.util.List;

public class MostrarResurtirPresenter implements MostrarResurtirContract.Presenter {
    private SolicitudRepository solicitudRepository;

    public MostrarResurtirPresenter() {
        solicitudRepository = SolicitudRepository.getInstance();
    }

    @Override
    public List<Solicitud> getData() {
        return solicitudRepository.getAll(Solicitud.class);
    }
}
