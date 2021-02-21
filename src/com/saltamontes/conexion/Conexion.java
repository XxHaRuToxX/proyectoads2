package com.saltamontes.conexion;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public abstract class Conexion {
    protected static Connection conexion;
    protected static PreparedStatement consulta;
    protected static ResultSet resultado;
    protected static final String Driver = "org.mariadb.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mariadb://localhost:3306/proyectoads2";
    protected static final String usuario = "xxharutoxx";
    protected static final String contraseña = "dell190";

    protected static boolean abrirConexion()
    {
        try {
            Class.forName(Driver);
            conexion = DriverManager.getConnection(DB_URL,usuario,contraseña);
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
    protected static boolean cerrarConexion() {
        try {
            if (!conexion.isClosed()) {


                consulta.close();
                conexion.close();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}



