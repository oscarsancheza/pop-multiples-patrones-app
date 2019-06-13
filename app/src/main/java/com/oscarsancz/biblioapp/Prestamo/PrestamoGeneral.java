package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.EstadoLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;

import io.realm.RealmList;

public class PrestamoGeneral implements ComportamientoPrestamo {
    private LibrosRepository librosRepository;

    public PrestamoGeneral(){
        librosRepository  = LibrosRepository.getInstance();
    }

    @Override
    public RealmList<Libro> prestar(RealmList<Libro> libros) {
        Libro libroPrestar;
        RealmList<Libro> librosPrestar = new RealmList<>();
        for (Libro libro : libros){
            libroPrestar = librosRepository.find(libro.getIsbn(), EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
            if(libroPrestar == null){
                libroPrestar = librosRepository.find(libro.getIsbn(), EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
                if(libroPrestar == null){
                    libroPrestar = librosRepository.find(libro.getIsbn(), EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
                    librosPrestar.add(libroPrestar);
                }else {
                    librosPrestar.add(libroPrestar);
                }
            }else{
                librosPrestar.add(libroPrestar);
            }
        }
        return librosPrestar;
    }
}
