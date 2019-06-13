package com.oscarsancz.biblioapp.presenters;

import com.oscarsancz.biblioapp.contracts.DevolverLibroContract;
import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.List;

import io.realm.RealmList;

public class DevolverLibroPresenter implements DevolverLibroContract.Presenter {
    UsuarioRepository usuarioRepository;
    LibrosRepository librosRepository;

    public DevolverLibroPresenter() {
        usuarioRepository = UsuarioRepository.getInstance();
        librosRepository = LibrosRepository.getInstance();
    }

    @Override
    public RealmList<Libro> getLibrosUsuario(int id) {
        Usuario usuario = usuarioRepository.find(id, "id", Usuario.class);
        return usuario.getLibros();
    }

    @Override
    public boolean devolverLibros(List<Libro> librosDevolver) {
        Libro libro;
        boolean respuesta = false;
        for (Libro libroDevolver : librosDevolver) {
            libro = librosRepository.find(libroDevolver.getIsbn(), "isbn", Libro.class);
            libro.setStatus(DisponibilidadLibro.DISPONIBLE);
            respuesta = librosRepository.save(libro);
        }
        return respuesta;
    }

    @Override
    public boolean updateLibrosUsuario(int id, RealmList<Libro> librosUsuario) {
        boolean respuesta;
        Usuario usuario = usuarioRepository.find(id, "id", Usuario.class);
        usuario.setLibros(librosUsuario);
        respuesta = usuarioRepository.save(usuario);
        return respuesta;
    }
}
