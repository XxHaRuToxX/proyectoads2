package com.saltamontes.modelos;

import com.saltamontes.conexion.Conexion;
import com.saltamontes.interfaz.Imodels;

public class Persona extends Conexion implements Imodels {
    private int id_persona;
    private String nombres_persona;
    private String apellidos_persona;
    private String dni_persona;

    public Persona(){
        id_persona=0;
        nombres_persona="";
        apellidos_persona="";
        dni_persona="";

    }
    public Persona(int id_persona, String nombres_persona, String apellidos_persona, String dni_persona) {
        this.id_persona = id_persona;
        this.nombres_persona = nombres_persona;
        this.apellidos_persona = apellidos_persona;
        this.dni_persona = dni_persona;
    }

    public int getId_persona() { return id_persona; }

    public void setId_persona(int id_persona) { this.id_persona = id_persona; }

    public String getNombres_persona() { return nombres_persona; }

    public void setNombres_persona(String nombres_persona) { this.nombres_persona = nombres_persona; }

    public String getApellidos_persona() { return apellidos_persona; }

    public void setApellidos_persona(String apellidos_persona) { this.apellidos_persona = apellidos_persona; }

    public String getDni_persona() { return dni_persona; }

    public void setDni_persona(String dni_persona) { this.dni_persona = dni_persona; }


    @Override
    public boolean gestionar() throws Exception {
        return false;
    }
}
