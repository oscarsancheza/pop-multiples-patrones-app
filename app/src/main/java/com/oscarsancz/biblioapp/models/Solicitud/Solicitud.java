package com.oscarsancz.biblioapp.models.Solicitud;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Solicitud extends RealmObject {
    @PrimaryKey private Long isbn;
    private String titulo;
    private Date fecha;

    public Solicitud(){}

    public Solicitud(Long isbn, String titulo, Date fecha){
        this.titulo = titulo;
        this.isbn = isbn;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getIsbn() {
        return isbn;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
