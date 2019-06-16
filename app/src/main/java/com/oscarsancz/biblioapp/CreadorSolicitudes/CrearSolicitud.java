package com.oscarsancz.biblioapp.CreadorSolicitudes;

import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.SolicitudRepository;

import java.util.Date;
import java.util.List;

public class CrearSolicitud {
    private Libro libro;
    private LibrosRepository librosRepository;
    private SolicitudRepository solicitudRepository;

    public CrearSolicitud(LibrosRepository librosRepository, SolicitudRepository solicitudRepository){
        this.librosRepository = librosRepository;
        this.solicitudRepository = solicitudRepository;
    }

    public void crear(Libro libro){
        List<Libro> librosPrestados;

        librosPrestados = librosRepository.getAllByIsbn(libro.getIsbn());
        if(librosPrestados.size() <= 2){
            Solicitud solicitudResurtir = new Solicitud(libro.getIsbn(), libro.getTitulo(), new Date());
            solicitudRepository.save(solicitudResurtir);
        }
    }
}
