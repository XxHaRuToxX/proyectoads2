package com.saltamontes.modelos;

import com.saltamontes.conexion.Conexion;
import com.saltamontes.interfaz.Imodels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Producto extends Conexion implements Imodels {

    private int id_producto;
    private Double precio_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private int stock_producto;
    private char op;
    private int cantidad=0;

    public Producto(){
        id_producto=0;
        precio_producto=0.0;
        nombre_producto="";
        descripcion_producto="";
        stock_producto=0;
        op=' ';
    }
    public Producto(int id_producto,Double precio_producto,String nombre_producto,String descripcion_producto,int stock_producto){
        this.id_producto=id_producto;
        this.precio_producto=precio_producto;
        this.nombre_producto=nombre_producto;
        this.descripcion_producto=descripcion_producto;
        this.stock_producto=stock_producto;

    }

    public int getId_producto() { return id_producto; }

    public void setId_producto(int id_producto) { this.id_producto = id_producto; }

    public Double getPrecio_producto() { return precio_producto; }

    public void setPrecio_producto(Double precio_producto) { this.precio_producto = precio_producto; }

    public String getNombre_producto() { return nombre_producto; }

    public void setNombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }

    public String getDescripcion_producto() { return descripcion_producto; }

    public void setDescripcion_producto(String descripcion_producto) { this.descripcion_producto = descripcion_producto; }

    public int getStock_producto() { return stock_producto; }

    public void setStock_producto(int stock_producto) { this.stock_producto = stock_producto; }

    public char getOp() { return op; }

    public void setOp(char op) { this.op = op; }

    @Override
    public boolean gestionar() throws Exception {
        try{
            abrirConexion();
            consulta=conexion.prepareStatement("call gestionarProducto(?,?,?,?,?,?)");
            consulta.setString(1, String.valueOf(getOp()));
            consulta.setInt(2,getId_producto());
            consulta.setDouble(3,getPrecio_producto());
            consulta.setString(4,getNombre_producto());
            consulta.setString(5,getDescripcion_producto());
            consulta.setInt(6,getStock_producto());
            return consulta.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return false;
    }
    @Override
    public String toString(){
        return "ID : "+getId_producto()+"\n"+"NOMBRE: "+getNombre_producto()+"\n"+"DESCRIPCION: "+getDescripcion_producto()+"\n"+"PRECIO: "+getPrecio_producto()+"\n"+"STOCK: "+getStock_producto()+"\n";
    }

    public static Producto traer(int id){
        Producto p=null;
        try{
            abrirConexion();
            consulta=conexion.prepareStatement("select*from producto where id_producto=?");
            consulta.setInt(1,id);
            resultado=consulta.executeQuery();
            if(resultado.next()){
                p=new Producto();
                p.setId_producto(resultado.getInt(1));
                p.setPrecio_producto(resultado.getDouble(2));
                p.setNombre_producto(resultado.getString(3));
                p.setDescripcion_producto(resultado.getString(4));
                p.setStock_producto(resultado.getInt(5));
                return p;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return null;
    }

    public static Producto traerpornombre(String nombre){
        Producto p=null;
        try{
            abrirConexion();
            consulta=conexion.prepareStatement("select*from producto where nombre_producto=?");
            consulta.setString(1,nombre);
            resultado=consulta.executeQuery();
            if(resultado.next()){
                p=new Producto();
                p.setId_producto(resultado.getInt(1));
                p.setPrecio_producto(resultado.getDouble(2));
                p.setNombre_producto(resultado.getString(3));
                p.setDescripcion_producto(resultado.getString(4));
                p.setStock_producto(resultado.getInt(5));
                return p;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion();
        }
        return null;
    }

    public static List<Producto> listarProductos(){
        List<Producto> Pros=new ArrayList<Producto>();
        try {
            Producto p=null;
            abrirConexion();
            consulta = conexion.prepareStatement("select*from producto");
            resultado = consulta.executeQuery();
            while(resultado.next()) {
                p=new Producto();
                p.setId_producto(resultado.getInt(1));
                p.setPrecio_producto(resultado.getDouble(2));
                p.setNombre_producto(resultado.getString(3));
                p.setDescripcion_producto(resultado.getString(4));
                p.setStock_producto(resultado.getInt(5));
                Pros.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return Pros;
    }

    public static String[] traernombres(){
        String[] nombres=new String[traercantidad()+1];
        try {
            abrirConexion();
            int i=0;
            nombres[i]=("seleccione-producto");
            i++;
            consulta = conexion.prepareStatement("select nombre_producto from producto");
            resultado = consulta.executeQuery();
            while(resultado.next()) {
                nombres[i]=resultado.getString(1);
                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return nombres;
    }

    public static int traercantidad(){
        try {
            abrirConexion();
            consulta = conexion.prepareStatement("select count(id_producto) from producto;");
            resultado=consulta.executeQuery();
            if(resultado.next()){
                return resultado.getInt(1);
            }

        }catch (Exception ex){

        }finally {
            cerrarConexion();
        }
        return 0;
    }



}
