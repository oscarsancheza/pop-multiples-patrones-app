package com.oscarsancz.biblioapp.Prestamo;

public class PrestarPublico extends Prestar {
    public PrestarPublico() {
        comportamientoPrestamo = new PrestamoGeneral();
    }
}
