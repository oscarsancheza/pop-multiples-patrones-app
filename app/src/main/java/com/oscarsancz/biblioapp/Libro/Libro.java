package com.oscarsancz.biblioapp.Libro;

public class Libro {
    private int ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private EstadoLibro estadoLibro;

    public Libro(int isbn, String titulo, String autor, String editorial, EstadoLibro estadoLibro){
        this.ISBN = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estadoLibro = estadoLibro;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

}
