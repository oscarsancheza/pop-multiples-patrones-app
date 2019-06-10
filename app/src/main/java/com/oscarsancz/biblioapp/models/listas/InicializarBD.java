package com.oscarsancz.biblioapp.models.listas;

import com.oscarsancz.biblioapp.models.Libro.EstadoLibro;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;


import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class InicializarBD {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private RealmList<Libro> lirosUsuario;

    public InicializarBD(){
        Libro libro1 = new Libro(136083238, "Clean Code", "Robert C. Martin", "Prentice Hall", EstadoLibro.NUEVO, 5);
        libros.add(libro1);
        Libro libro2 = new Libro(1491914250, "Deep Learning A practitioner's approach", "Josh Patterson & Adam Gibson ", "O'Reilly", EstadoLibro.NUEVO, 8);
        libros.add(libro2);
        Libro libro3 = new Libro(470724145, "El Hobbit", "J.R.R Tolkien", "Minotauro", EstadoLibro.NUEVO, 4);
        libros.add(libro3);
        Libro libro4 = new Libro(978970643, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Penguin Clasicos", EstadoLibro.NUEVO, 10);
        libros.add(libro4);

        Usuario usuario1 = new Usuario("M18170808", "Jose", "Beltran", "Valezuela", TipoUsuario.ESTUDIANTE, lirosUsuario);
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario("M18130401", "Oscar", "Alvarado", "Sanchez", TipoUsuario.ESTUDIANTE, lirosUsuario);
        usuarios.add(usuario2);
        Usuario usuario3 = new Usuario("M18190201", "Efrain", "Gonzalez", "Cuadras", TipoUsuario.ESTUDIANTE, lirosUsuario);
        usuarios.add(usuario3);
        Usuario usuario4 = new Usuario("D18910205", "Ricardo", "Qintero", "Quintero", TipoUsuario.PROFESOR, lirosUsuario);
        usuarios.add(usuario4);
        Usuario usuario5 = new Usuario("D19181716", "Ramon", "Cabada", "Zatarain", TipoUsuario.PROFESOR, lirosUsuario);
        usuarios.add(usuario5);
        Usuario usuario6 = new Usuario("D17161514", "Lucia", "Es", "Barron", TipoUsuario.PROFESOR, lirosUsuario);
        usuarios.add(usuario6);
        Usuario usuarioG = new Usuario("G19181716", "Luis", "Lopez", "Leyva", TipoUsuario.PUBLICO_GENERAL, lirosUsuario);
        usuarios.add(usuarioG);
        Usuario usuarioG1 = new Usuario("G18171615", "Oralando", "Luna", "Alvarado", TipoUsuario.PUBLICO_GENERAL, lirosUsuario);
        usuarios.add(usuarioG1);
        Usuario usuarioG2 = new Usuario("G17161514", "Maria", "Beltran", "Quintero", TipoUsuario.PUBLICO_GENERAL, lirosUsuario);
        usuarios.add(usuarioG2);
    }

    public List<Libro> getLibros(){
        return libros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
