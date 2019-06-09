package com.oscarsancz.biblioapp.Usuarios;

public class Usuario {
    private int clave;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private TipoUsuario tipo;

    public Usuario(int clave, String nombre, String apellidoM, String apellidoP, TipoUsuario tipo){
        this.clave = clave;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.tipo = tipo;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClave() {
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
        return tipo;
    }
}
