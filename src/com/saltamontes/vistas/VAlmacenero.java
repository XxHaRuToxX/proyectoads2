package com.saltamontes.vistas;

import com.saltamontes.modelos.Producto;
import com.saltamontes.modelos.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VAlmacenero extends JFrame{
    private JPanel VAlmacenero;
    private JButton CERRARSESION;
    private JTextField IDPRODUCTO;
    private JTextField NOMBREPRODUCTO;
    private JTextField PRECIOPRODUCTO;
    private JTextArea DESCRIPCIONPRODUCTO;
    private JTextField STOCK;
    private JButton REGISTRAR;
    private JButton ACTUALIZAR;
    private JButton ELIMINAR;
    private Producto p;


    public VAlmacenero() {
        iniciarComponentes();
        IDPRODUCTO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(IDPRODUCTO.getText().trim().length()>0&&IDPRODUCTO.getText().trim().matches("\\d*")){
                        p= Producto.traer(Integer.parseInt(IDPRODUCTO.getText().trim()));
                        if(p==null){
                            JOptionPane.showMessageDialog(null,"no existe Producto");
                            limpiarcontroles();
                        }else{
                            mostrarDatos();
                        }}else{
                        JOptionPane.showMessageDialog(null,"Ingrese un ID VALIDO");
                    }
                }
            }
        });
        REGISTRAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDPRODUCTO.setText("1");
                if(!hayespaciosenblanco()){
                    registrar();
                }else{
                    JOptionPane.showMessageDialog(null,"rellene los espacios");
                }
            }
        });
        ACTUALIZAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!hayespaciosenblanco()){
                    actualizar();
                }else{
                    JOptionPane.showMessageDialog(null,"rellene los espacios");
                }
            }
        });

        ELIMINAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(IDPRODUCTO.getText().trim().length()>0&&IDPRODUCTO.getText().trim().matches("\\d*")){
                    p= Producto.traer(Integer.parseInt(IDPRODUCTO.getText().trim()));
                    if(p!=null){
                        eliminar();
                    }else{
                        JOptionPane.showMessageDialog(null,"No existe Producto");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Ingrese un ID VALIDO");
                }
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
        setSize(600, 450);
        setContentPane(VAlmacenero);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setUndecorated(true);
        DESCRIPCIONPRODUCTO.setLineWrap(true);
        DESCRIPCIONPRODUCTO.setWrapStyleWord(true);
    }

    private void registrar(){

        p=new Producto();
        p.setNombre_producto(NOMBREPRODUCTO.getText());
        p.setPrecio_producto(Double.valueOf(PRECIOPRODUCTO.getText()));
        p.setDescripcion_producto(DESCRIPCIONPRODUCTO.getText());
        p.setStock_producto(Integer.parseInt(STOCK.getText()));
        p.setOp('i');
        try {
            p.gestionar();
            JOptionPane.showMessageDialog(null,"registrado");
            limpiarcontroles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizar(){
            p=new Producto();
            p.setOp('a');
            p.setId_producto(Integer.parseInt(IDPRODUCTO.getText().trim()));
            p.setNombre_producto(NOMBREPRODUCTO.getText());
            p.setPrecio_producto(Double.valueOf(PRECIOPRODUCTO.getText()));
            p.setDescripcion_producto(DESCRIPCIONPRODUCTO.getText());
            p.setStock_producto(Integer.parseInt(STOCK.getText()));
            try {
                p.gestionar();
                JOptionPane.showMessageDialog(null,"Cambios Guardados");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void eliminar(){
            p=new Producto();
            if(Integer.parseInt(IDPRODUCTO.getText().trim())!=1){
            p.setId_producto(Integer.parseInt(IDPRODUCTO.getText().trim()));
            p.setOp('e');
            try {
                p.gestionar();
                JOptionPane.showMessageDialog(null,"Producto eliminado");
                limpiarcontroles();
                p=null;
            } catch (Exception e) {
                e.printStackTrace();
            }}else{
            JOptionPane.showMessageDialog(null,"No puede eliminar a este producto");
        }

    }
    private Boolean hayespaciosenblanco(){
        if(IDPRODUCTO.getText().trim().length()>0&&IDPRODUCTO.getText().trim().matches("\\d*")&&NOMBREPRODUCTO.getText().length()>0&&obtenerprecio(PRECIOPRODUCTO.getText().trim())&&DESCRIPCIONPRODUCTO.getText().length()>0&&STOCK.getText().trim().matches("\\d*")){
            return false;
        }
        else{
            return true;
        }
    }
    private boolean obtenerprecio(String cadena){
        Float num;
        try {
            num=Float.parseFloat(cadena);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    private void mostrarDatos(){
        IDPRODUCTO.setText(String.valueOf(p.getId_producto()));
        NOMBREPRODUCTO.setText(p.getNombre_producto());
        PRECIOPRODUCTO.setText(String.valueOf(p.getPrecio_producto()));
        DESCRIPCIONPRODUCTO.setText(p.getDescripcion_producto());
        STOCK.setText(String.valueOf(p.getStock_producto()));
    }
    private void limpiarcontroles(){
        IDPRODUCTO.setText(null);
        NOMBREPRODUCTO.setText(null);
        PRECIOPRODUCTO.setText(null);
        DESCRIPCIONPRODUCTO.setText(null);
        STOCK.setText(null);
    }
}
