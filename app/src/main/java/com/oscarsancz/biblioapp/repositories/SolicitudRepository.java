package com.oscarsancz.biblioapp.repositories;

import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;

public class SolicitudRepository extends GenericRepository<Solicitud>{
    private static SolicitudRepository instance;
    private final String TAG = this.getClass().getSimpleName();

    private SolicitudRepository(){
        super();
    }

    public static SolicitudRepository getInstance(){
        if(instance == null) {
            instance = new SolicitudRepository();
        }
        return instance;
    }



}
