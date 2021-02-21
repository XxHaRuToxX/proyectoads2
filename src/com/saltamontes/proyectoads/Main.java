package com.saltamontes.proyectoads;
import com.saltamontes.conexion.Conexion;
import com.saltamontes.modelos.Producto;
import com.saltamontes.vistas.VPrincipalPrograma;

import java.util.List;

public class Main extends Conexion {

    public static void main(String[] args){
        VPrincipalPrograma pri = new VPrincipalPrograma();
        pri.setVisible(true);

    }
}
