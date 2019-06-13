package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.EstadoLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;

import io.realm.RealmList;

public class PrestamoProfesor implements ComportamientoPrestamo {
    private LibrosRepository librosRepository;

    public PrestamoProfesor() {
        librosRepository = LibrosRepository.getInstance();
    }

    @Override
    public RealmList<Libro> prestar(RealmList<Libro> libros) {
        Libro libroP;
        RealmList<Libro> librosPrestar = new RealmList<>();
        for (Libro libro : libros) {
            libroP = librosRepository.find(libro.getIsbn(), EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
            if (libroP == null) {
                libroP = librosRepository.find(libro.getIsbn(), EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
                if (libroP == null) {
                    libroP = librosRepository.find(libro.getIsbn(), EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
                }
            }

            if (libroP != null) {
                libroP.setStatus(DisponibilidadLibro.PRESTADO);
                librosPrestar.add(libroP);
            }
        }
        return librosPrestar;
    }
}
