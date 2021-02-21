package com.saltamontes.modelos;

import com.saltamontes.conexion.Conexion;
import com.saltamontes.interfaz.Imodels;

public class Proveedor extends Conexion implements Imodels {
    private int id_proveedor;
    private String direccion_roveedor;
    private String razon_social;
    private String ruc_proveedor;
    private String telefono;
    private char op;


    public Proveedor() {
        id_proveedor=0;
        direccion_roveedor="";
        razon_social="";
        ruc_proveedor="";
        telefono="";
        op='i';
    }

    public Proveedor(int id_proveedor,String direccion_roveedor,String razon_social, String ruc_proveedor, String telefono){
        this.id_proveedor=id_proveedor;
        this.direccion_roveedor=direccion_roveedor;
        this.razon_social=razon_social;
        this.ruc_proveedor=ruc_proveedor;
        this.telefono=telefono;
        op='i';
    }

    public int getId_proveedor() { return id_proveedor; }

    public void setId_proveedor(int id_proveedor) { this.id_proveedor = id_proveedor; }

    public String getDireccion_roveedor() { return direccion_roveedor; }

    public void setDireccion_roveedor(String direccion_roveedor) { this.direccion_roveedor = direccion_roveedor; }

    public String getRazon_social() { return razon_social; }

    public void setRazon_social(String razon_social) { this.razon_social = razon_social; }

    public String getRuc_proveedor() { return ruc_proveedor; }

    public void setRuc_proveedor(String ruc_proveedor) { this.ruc_proveedor = ruc_proveedor; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public char getOp() { return op; }

    public void setOp(char op) { this.op = op; }

    @Override
    public boolean gestionar() throws Exception {
        try {
            abrirConexion();
            consulta=conexion.prepareStatement("call gestionarProveedor(?,?,?,?,?,?)");
            consulta.setString(1, String.valueOf(getOp()));
            consulta.setInt(2,getId_proveedor());
            consulta.setString(3,getDireccion_roveedor());
            consulta.setString(4,getRazon_social());
            consulta.setString(5,getRuc_proveedor());
            consulta.setString(6,getTelefono());
            return consulta.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return false;
    }

    public static Proveedor traer(int id){
        Proveedor p=null;
        try {
            abrirConexion();
            consulta=conexion.prepareStatement("select*from proveedor where id_proveedor=?");
            consulta.setInt(1,id);
            resultado = consulta.executeQuery();
            if(resultado.next()){
                p= new Proveedor();
                p.setId_proveedor(resultado.getInt(1));
                p.setDireccion_roveedor(resultado.getString(2));
                p.setRazon_social(resultado.getString(3));
                p.setRuc_proveedor(resultado.getString(4));
                p.setTelefono(resultado.getString(5));
                return p;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return p;
    }
}
