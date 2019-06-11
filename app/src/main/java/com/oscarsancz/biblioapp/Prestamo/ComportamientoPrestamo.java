package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import io.realm.RealmList;

public interface ComportamientoPrestamo {
    public void prestar(Usuario usuario, RealmList<Libro> libros);
}
