package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import io.realm.RealmList;

public abstract class Prestar {

    protected ComportamientoPrestamo comportamientoPrestamo;

    public RealmList<Libro> crearPrestamo(RealmList<Libro> libros) {
        return comportamientoPrestamo.prestar(libros);
    }
}
