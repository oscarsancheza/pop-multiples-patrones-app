package com.oscarsancz.biblioapp.models.Solicitud;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Solicitud extends RealmObject {
    @PrimaryKey private Long isbn;
    private Date fecha;

    public Solicitud(){}

    public Solicitud(Long isbn, Date fecha){
        this.isbn = isbn;
        this.fecha = fecha;
    }

    public Long getIsbn() {
        return isbn;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
