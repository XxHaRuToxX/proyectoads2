package com.saltamontes.modelos;
import com.saltamontes.conexion.Conexion;
import com.saltamontes.interfaz.Imodels;

import java.util.ArrayList;


public class Trabajador extends Persona implements Imodels {
    private int id_trabajador;
    private String cargo;
    private String direccion_trabajador;
    private String genero;
    private String contraseña;
    private String telefono;
    private int id_persona;
    private String usuario;
    private char op;



    public Trabajador(){
        id_trabajador=0;
        setNombres_persona("");
        setApellidos_persona("");
        setApellidos_persona("");
        cargo="";
        direccion_trabajador="";
        genero="";
        contraseña="";
        telefono="";
        id_persona=0;
        usuario="";
        op='i';
    }

    public Trabajador(int id_trabajador,String nombres,String apellidos,String dni,String cargo,String direccion_trabajador,String genero,String contraseña,String telefono,int id_persona,String usuario){
        this.id_trabajador=id_trabajador;
        setNombres_persona(nombres);
        setApellidos_persona(apellidos);
        setApellidos_persona(dni);
        this.cargo=cargo;
        this.direccion_trabajador=direccion_trabajador;
        this.genero=genero;
        this.contraseña=contraseña;
        this.telefono=telefono;
        this.id_persona=id_persona;
        this.usuario=usuario;
        this.op='i';
    }

    public int getId_trabajador() { return id_trabajador; }

    public void setId_trabajador(int id_trabajador) { this.id_trabajador = id_trabajador; }

    public String getCargo() { return cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getDireccion_trabajador() { return direccion_trabajador; }

    public void setDireccion_trabajador(String direccion_trabajador) { this.direccion_trabajador = direccion_trabajador; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public String getContraseña() { return contraseña; }

    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getId_persona() { return id_persona; }

    public void setId_persona(int id_persona) { this.id_persona = id_persona; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public char isOp() { return op; }

    public void setOp(char op) { this.op = op; }

    @Override
    public boolean gestionar() throws Exception {
        try{
            abrirConexion();
            consulta=conexion.prepareStatement("call gestionartrabajador(?,?,?,?,?,?,?,?,?,?,?)");
            consulta.setString(1, String.valueOf(isOp()));
            consulta.setInt(2,getId_trabajador());
            consulta.setString(3, getContraseña());
            consulta.setString(4,getNombres_persona());
            consulta.setString(5,getApellidos_persona());
            consulta.setString(6,getDni_persona());
            consulta.setString(7,getGenero());
            consulta.setString(8,getTelefono());
            consulta.setString(9,getCargo());
            consulta.setString(10, getDireccion_trabajador());
            consulta.setString(11,getUsuario());
            return consulta.execute();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return false;
    }

    @Override
    public String toString(){
        return "ID : "+getId_trabajador()+" CARGO: "+getCargo()+" DIRECCION: "+getDireccion_trabajador()+" GENERO: "+getGenero()+" CONTRASEÑA: "+getContraseña()+" TELEFONO: "+getTelefono()+" ID PERSONA: "+getId_persona()+"\n";
    }

    public static Trabajador traer(int id) {
        Trabajador T;
        try {
            abrirConexion();
            consulta = conexion.prepareStatement("select t.id_trabajador,t.contraseña,p.nombres,p.apellidos,p.dni,t.genero,t.telefono,t.cargo,t.direccion_trabajador,t.id_persona,t.usuario from trabajador t,persona p where t.id_persona=p.id_persona and t.id_persona=?");
            consulta.setInt(1, id);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                T = new Trabajador();
                T.setId_trabajador(resultado.getInt(1));
                T.setContraseña(resultado.getString(2));
                T.setNombres_persona(resultado.getString(3));
                T.setApellidos_persona(resultado.getString(4));
                T.setDni_persona(resultado.getString(5));
                T.setGenero(resultado.getString(6));
                T.setTelefono(resultado.getString(7));
                T.setCargo(resultado.getString(8));
                T.setDireccion_trabajador(resultado.getString(9));
                T.setId_persona(resultado.getInt(10));
                T.setUsuario(resultado.getString(11));
                return T;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();

        }
        return null;
    }

    public static Trabajador TraerUsuario(String Usuario,String Contraseña) {
        Trabajador T=null;
        try {
            abrirConexion();
            consulta = conexion.prepareStatement("select*from trabajador where usuario=? and contraseña=? ");
            consulta.setString(1, Usuario);
            consulta.setString(2,Contraseña);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                T = new Trabajador();
                T.setId_trabajador(resultado.getInt(1));
                T.setCargo(resultado.getString(2));
                T.setDireccion_trabajador(resultado.getString(3));
                T.setGenero(resultado.getString(4));
                T.setContraseña(resultado.getString(5));
                T.setTelefono(resultado.getString(6));
                T.setId_persona(resultado.getInt(7));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();

        }
        return T;
    }
}
