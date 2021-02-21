package com.saltamontes.vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VContador extends JFrame{
    private JPanel VContadorPrincipal;
    private JButton COMPRASVENTAS;
    private JButton PROVEEDORES;
    private JButton CERRARSESION;
    private JPanel CONTENIDO;
    private VContadorComprasVentas pv;
    private VContadorProveedores p;


    public VContador() {
        iniciarComponentes();
        COMPRASVENTAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CONTENIDO.removeAll();
                    cargarProductosVentas();
            }
        });

        PROVEEDORES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CONTENIDO.removeAll();
                cargarProveedores();


            }
        });

        CERRARSESION.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VPrincipalPrograma v=new VPrincipalPrograma();
                v.setVisible(true);
            }
        });

    }

    private void iniciarComponentes(){
        setSize(600, 650);
        setContentPane(VContadorPrincipal);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setUndecorated(true);
    }
    private void cargarProductosVentas(){
        pv = new VContadorComprasVentas();
        CONTENIDO.add(pv.getpanel());
        setVisible(true);

    }
    private void cargarProveedores(){
        p = new VContadorProveedores();
        CONTENIDO.add(p.getpanel());
        setVisible(true);
    }
}
