package com.oscarsancz.biblioapp.Prestamo;

import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import io.realm.RealmList;

public class Prestar {
    private Usuario usuario;
    private RealmList<Libro> libros;

    public Prestar(Usuario usuario, RealmList<Libro> libros){
        this.libros = libros;
        this.usuario = usuario;
    }

    public void crearPrestamo(){
        ComportamientoPrestamo comportamientoPrestamo;
        if(usuario.getTipo() == TipoUsuario.PROFESOR){
            comportamientoPrestamo = new PrestamoProfesor();
            comportamientoPrestamo.prestar(usuario, libros);
        }else if(usuario.getTipo() == TipoUsuario.ESTUDIANTE){
            comportamientoPrestamo = new PrestamoEstudiante();
            comportamientoPrestamo.prestar(usuario, libros);
        }else if(usuario.getTipo() == TipoUsuario.PUBLICO_GENERAL){
            comportamientoPrestamo = new PrestamoGeneral();
            comportamientoPrestamo.prestar(usuario, libros);
        }
    }
}
