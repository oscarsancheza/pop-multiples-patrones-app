package com.oscarsancz.biblioapp.models.Usuarios;

import com.oscarsancz.biblioapp.models.Libro.Libro;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Usuario extends RealmObject {

  @PrimaryKey private int id;
  private String clave;
  private String nombre;
  private String apellidoP;
  private String apellidoM;
  private String tipo;
  private RealmList<Libro> libros;

  public Usuario() {}

  public Usuario(int id,
      String clave,
      String nombre,
      String apellidoM,
      String apellidoP,
      TipoUsuario tipo,
      RealmList<Libro> libros) {
    this.id = id;
    this.clave = clave;
    this.nombre = nombre;
    this.apellidoP = apellidoP;
    this.apellidoM = apellidoM;
    this.tipo = tipo.toString();
    this.libros = libros;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public void setApellidoP(String apellidoP) {
    this.apellidoP = apellidoP;
  }

  public void setApellidoM(String apellidoM) {
    this.apellidoM = apellidoM;
  }

  public void setTipo(TipoUsuario tipo) {
    this.tipo = tipo.toString();
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getClave() {
    return clave;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellidoP() {
    return apellidoP;
  }

  public String getApellidoM() {
    return apellidoM;
  }

  public TipoUsuario getTipo() {
    return TipoUsuario.valueOf(tipo);
  }

  public RealmList<Libro> getLibros() {
    return libros;
  }

  public void setLibros(RealmList<Libro> libros) {
    this.libros = libros;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombreCompleto() {
    return ((getNombre() == null || getNombre().isEmpty()) ? "Sin nombre" : getNombre())
        + " "
        + ((getApellidoP() == null || getApellidoP().isEmpty()) ? "" : getApellidoP())
        + " "
        + ((getApellidoM() == null || getApellidoM().isEmpty()) ? "" : getApellidoM());
  }
}
