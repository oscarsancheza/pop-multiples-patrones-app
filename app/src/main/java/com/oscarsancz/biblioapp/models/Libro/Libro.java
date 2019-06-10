package com.oscarsancz.biblioapp.models.Libro;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Libro extends RealmObject {

  @PrimaryKey private int isbn;
  private String titulo;
  private String autor;
  private String editorial;
  private String estadoLibro;

  public Libro() {}

  public Libro(int isbn, String titulo, String autor, String editorial, EstadoLibro estadoLibro) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.estadoLibro = estadoLibro.toString();
  }

  public int getIsbn() {
    return isbn;
  }

  public void setIsbn(int isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getEditorial() {
    return editorial;
  }

  public void setEditorial(String editorial) {
    this.editorial = editorial;
  }

  public EstadoLibro getEstadoLibro() {
    return EstadoLibro.valueOf(estadoLibro);
  }

  public void setEstadoLibro(EstadoLibro estadoLibro) {
    this.estadoLibro = estadoLibro.toString();
  }
}
