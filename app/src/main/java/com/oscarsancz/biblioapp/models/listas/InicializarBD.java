package com.oscarsancz.biblioapp.models.listas;

import com.oscarsancz.biblioapp.models.Libro.DisponibilidadLibro;
import com.oscarsancz.biblioapp.models.Libro.EstadoLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;
import com.oscarsancz.biblioapp.repositories.LibrosRepository;
import com.oscarsancz.biblioapp.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class InicializarBD {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioRepository usuarioRepository;
    private LibrosRepository librosRepository;

    public InicializarBD() {
        usuarioRepository = UsuarioRepository.getInstance();
        librosRepository = LibrosRepository.getInstance();


        Libro libro1 = new Libro(1, 12321136083238L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro1);
        Libro libro2 = new Libro(2, 1491914250, "Deep Learning A practitioner's approach", "Josh Patterson & Adam Gibson ", "O'Reilly", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro2);
        Libro libro3 = new Libro(3, 470724145, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro3);
        Libro libro4 = new Libro(4, 9789706438714L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro4);

        Usuario usuario1 = new Usuario(1, "M18170808", "Jose", "Beltran", "Valezuela", TipoUsuario.ESTUDIANTE, new RealmList<>());
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario(2, "M18130401", "Oscar", "Alvarado", "Sanchez", TipoUsuario.ESTUDIANTE, new RealmList<>());
        usuarios.add(usuario2);
        Usuario usuario3 = new Usuario(3, "M18190201", "Efrain", "Gonzalez", "Cuadras", TipoUsuario.ESTUDIANTE, new RealmList<>());
        usuarios.add(usuario3);
        Usuario usuario4 = new Usuario(4, "D18910205", "Ricardo", "Qintero", "Quintero", TipoUsuario.PROFESOR, new RealmList<>());
        usuarios.add(usuario4);
        Usuario usuario5 = new Usuario(5, "D19181716", "Ramon", "Cabada", "Zatarain", TipoUsuario.PROFESOR, new RealmList<>());
        usuarios.add(usuario5);
        Usuario usuario6 = new Usuario(6, "D17161514", "Lucia", "Es", "Barron", TipoUsuario.PROFESOR, new RealmList<>());
        usuarios.add(usuario6);
        Usuario usuarioG = new Usuario(7, "G19181716", "Luis", "Lopez", "Leyva", TipoUsuario.PUBLICO_GENERAL, new RealmList<>());
        usuarios.add(usuarioG);
        Usuario usuarioG1 = new Usuario(8, "G18171615", "Oralando", "Luna", "Alvarado", TipoUsuario.PUBLICO_GENERAL, new RealmList<>());
        usuarios.add(usuarioG1);
        Usuario usuarioG2 = new Usuario(9, "G17161514", "Maria", "Beltran", "Quintero", TipoUsuario.PUBLICO_GENERAL, new RealmList<>());
        usuarios.add(usuarioG2);


    }

    public void inicializar() {
        usuarioRepository.save(usuarios);
        librosRepository.save(libros);
    }
}
