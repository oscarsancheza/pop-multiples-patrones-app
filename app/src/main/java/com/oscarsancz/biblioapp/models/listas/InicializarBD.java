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


        Libro libro1 = new Libro(1, 9780132350884L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro1);
        Libro libro2 = new Libro(2, 9780132350884L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro2);
        Libro libro3 = new Libro(3, 9780132350884L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro3);
        Libro libro4 = new Libro(4, 9780132350884L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro4);
        Libro libro5 = new Libro(5, 9780132350884L, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro5);
        Libro libro6 = new Libro(6, 9781491914250L, "Deep Learning A practitioner's approach", "Josh Patterson & Adam Gibson ", "O'Reilly", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro6);
        Libro libro7 = new Libro(7, 9781491914250L, "Deep Learning A practitioner's approach", "Josh Patterson & Adam Gibson ", "O'Reilly", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro7);
        Libro libro8 = new Libro(8, 9781491914250L, "Deep Learning A practitioner's approach", "Josh Patterson & Adam Gibson ", "O'Reilly", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro8);
        Libro libro9 = new Libro(9, 9786070724145L, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro9);
        Libro libro10 = new Libro(10, 9786070724145L, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro10);
        Libro libro11 = new Libro(11, 9786070724145L, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro11);
        Libro libro12 = new Libro(12, 9786070724145L, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro12);
        Libro libro13 = new Libro(13, 9789706438714L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro13);
        Libro libro14 = new Libro(14, 9789706438714L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro14);
        Libro libro15 = new Libro(15, 9789706438714L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro15);
        Libro libro16 = new Libro(16, 9789706438714L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro16);
        Libro libro17 = new Libro(17,9788445000663L, "EL SEÑOR DE LOS ANILLOS I: LA COMUNIDAD DEL ANILLO", "J.R.R Tolkien", "Booket", EstadoLibro.NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro17);
        Libro libro18 = new Libro(18,9788445000663L, "EL SEÑOR DE LOS ANILLOS I: LA COMUNIDAD DEL ANILLO", "J.R.R Tolkien", "Booket", EstadoLibro.VIEJO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro18);
        Libro libro19 = new Libro(19,9788445000663L, "EL SEÑOR DE LOS ANILLOS I: LA COMUNIDAD DEL ANILLO", "J.R.R Tolkien", "Booket", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro19);
        Libro libro20 = new Libro(20,9788445000663L, "EL SEÑOR DE LOS ANILLOS I: LA COMUNIDAD DEL ANILLO", "J.R.R Tolkien", "Booket", EstadoLibro.SEMI_NUEVO, DisponibilidadLibro.DISPONIBLE);
        libros.add(libro20);

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
