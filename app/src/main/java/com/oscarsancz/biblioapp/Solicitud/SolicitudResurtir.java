package com.oscarsancz.biblioapp.Solicitud;

import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.presenters.PrestamoLibroPresenter;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.SolicitudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.realm.RealmList;

public class SolicitudResurtir implements Observer {
    private SolicitudRepository solicitudRepository;
    private LibrosRepository librosRepository;
    private PrestamoLibroPresenter prestamoLibroPresenter;

    public SolicitudResurtir(PrestamoLibroPresenter prestamoLibroPresenter){
        this.prestamoLibroPresenter = prestamoLibroPresenter;
        this.solicitudRepository = SolicitudRepository.getInstance();
        this.librosRepository = LibrosRepository.getInstance();
        prestamoLibroPresenter.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        RealmList<Libro> libros = (RealmList<Libro>) arg;
        List<Libro> librosPrestados = new ArrayList<>();
        for (Libro libro : libros){
            librosPrestados = librosRepository.getAllByIsbn(libro.getIsbn());
            if(librosPrestados.size() <= 2){
                Solicitud solicitudResurtir = new Solicitud(libro.getIsbn(), new Date());
                solicitudRepository.save(solicitudResurtir);
            }
        }
    }
}
