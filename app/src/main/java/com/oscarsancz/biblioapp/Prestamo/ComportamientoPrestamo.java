package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import io.realm.RealmList;

public interface ComportamientoPrestamo {
    public RealmList<Libro> prestar(RealmList<Libro> libros);
}
