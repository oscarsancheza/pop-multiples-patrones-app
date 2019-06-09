package com.oscarsancz.biblioapp.CreadorFactoryMethod;

import com.oscarsancz.biblioapp.Usuarios.Estudiante;
import com.oscarsancz.biblioapp.Usuarios.Profesor;
import com.oscarsancz.biblioapp.Usuarios.PublicoGeneral;
import com.oscarsancz.biblioapp.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.Usuarios.Usuario;

public class CreadorUsuario {

    public Usuario crearUsuario(int clave, String nombre, String apellidoM, String apellidoP, TipoUsuario tipo){
        Usuario usuario = null;

        if(tipo == TipoUsuario.PROFESOR){
            usuario = new Profesor(clave, nombre, apellidoM, apellidoP, tipo);
        }else if(tipo == TipoUsuario.ESTUDIANTE){
            usuario = new Estudiante(clave, nombre, apellidoM, apellidoP, tipo);
        }else if(tipo == TipoUsuario.PUBLICO_GENERAL){
            usuario = new PublicoGeneral(clave, nombre, apellidoM, apellidoP, tipo);
        }
        return usuario;
    }
}
