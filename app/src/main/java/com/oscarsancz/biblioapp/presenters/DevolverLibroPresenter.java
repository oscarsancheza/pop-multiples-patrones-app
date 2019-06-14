package com.oscarsancz.biblioapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscarsancz.biblioapp.contracts.DevolverLibroContract;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;
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

    public DevolverLibroPresenter(
            @NonNull UsuarioRepository usuarioRepository,
            @NonNull LibrosRepository librosRepository,
            @NonNull DevolverLibroContract.View view,
            Context context
    ) {
        this.usuarioRepository = usuarioRepository;
        this.librosRepository = librosRepository;
        view.setPresenter(this);
    }

    @Override
    public void devolver(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarEstatusLibro(List<Libro> libros) {
        for (Libro item : libros) {
            item.setStatus(DisponibilidadLibro.DISPONIBLE);
            librosRepository.save(item);
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.all(Usuario.class);
    }
}
