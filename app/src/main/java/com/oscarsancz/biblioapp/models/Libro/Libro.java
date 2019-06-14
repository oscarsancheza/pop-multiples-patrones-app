package com.oscarsancz.biblioapp.models.Libro;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Libro extends RealmObject {

  @PrimaryKey private int id;
  private long isbn;
  private String titulo;
  private String autor;
  private String editorial;
  private String estado;
  private String status;

  public Libro() {}

  public Libro(
          int id,
      long isbn, String titulo, String autor, String editorial, EstadoLibro estado, DisponibilidadLibro disponibilidadLibro) {
    this.id = id;
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.estado = estado.toString();
    this.status = disponibilidadLibro.toString();
  }

  public long getIsbn() {
    return isbn;
  }

  public void setIsbn(long isbn) {
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

  public int getId() { return id; }

  public void setId(int id) { this.id = id;  }

  public DisponibilidadLibro getStatus() { return DisponibilidadLibro.valueOf(status); }

  public void setStatus(DisponibilidadLibro disponibilidadLibro) { this.status = disponibilidadLibro.toString();  }
}
