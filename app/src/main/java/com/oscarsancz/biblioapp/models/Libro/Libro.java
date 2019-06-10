package com.oscarsancz.biblioapp.models.Libro;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Libro extends RealmObject {

  @PrimaryKey private int isbn;
  private String titulo;
  private String autor;
  private String editorial;
  private int existencia;
  private String estado;

  public Libro() {}

  public Libro(
      int isbn, String titulo, String autor, String editorial, EstadoLibro estado, int existencia) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.estado = estado.toString();
    this.existencia = existencia;
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

  public EstadoLibro getEstado() {
    return EstadoLibro.valueOf(estado);
  }

  public void setEstado(EstadoLibro estado) {
    this.estado = estado.toString();
  }

  public int getExistencia() {
    return existencia;
  }

  public void setExistencia(int existencia) {
    this.existencia = existencia;
  }
}
