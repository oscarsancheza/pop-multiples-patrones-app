package com.oscarsancz.biblioapp.presenters;

import com.oscarsancz.biblioapp.Prestamo.Prestar;
import com.oscarsancz.biblioapp.Prestamo.PrestarEstudiante;
import com.oscarsancz.biblioapp.Prestamo.PrestarProfesor;
import com.oscarsancz.biblioapp.Prestamo.PrestarPublico;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.Observable;

import io.realm.RealmList;

public class PrestamoLibroPresenter extends Observable implements PrestamoLibroContract.Presenter {
    private UsuarioRepository usuarioRepository;

    public PrestamoLibroPresenter(){
        usuarioRepository = UsuarioRepository.getInstance();
    }

    @Override
    public boolean prestar(Usuario usuario, RealmList<Libro> librosPrestar) {
        Prestar prestar;
        RealmList<Libro> libros;
        if(usuario.getTipo() == TipoUsuario.PROFESOR){
            prestar = new PrestarProfesor();
            libros = prestar.crearPrestamo(librosPrestar);
        } else if(usuario.getTipo() == TipoUsuario.ESTUDIANTE){
            prestar = new PrestarEstudiante();
            libros = prestar.crearPrestamo(librosPrestar);
        } else{
            prestar = new PrestarPublico();
            libros = prestar.crearPrestamo(librosPrestar);
        }
        usuario.setLibros(libros);
        this.notifyObservers(libros);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }
}
