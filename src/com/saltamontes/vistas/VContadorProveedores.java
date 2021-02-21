package com.saltamontes.vistas;

import com.saltamontes.modelos.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VContadorProveedores extends JFrame{
    private JPanel VContadorProveedor;
    private JTextField ID;
    private JTextField RAZONSOCIAL;
    private JTextField DIRECCION;
    private JTextField TELEFONO;
    private JTextField RUC;
    private JButton REGISTRAR;
    private JButton ACTUALIZAR;
    private JButton ELIMINAR;
    private Proveedor p;

    public VContadorProveedores() {
        iniciarcomponentes();

        ID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (ID.getText().trim().length()>0&&ID.getText().trim().matches("\\d*")){
                        p= Proveedor.traer(Integer.parseInt(ID.getText().trim()));
                         if(p!=null){
                             mostrarDatos();

                         }else{
                             JOptionPane.showMessageDialog(null,"no existe Proveedor");
                             limpiarcontroles();
                         }
                        } else{
                        JOptionPane.showMessageDialog(null,"ingrese un id valido");
                    }
                }
            }
        });

        REGISTRAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID.setText("1");
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
                if(ID.getText().trim().length()>0&&ID.getText().trim().matches("\\d*")){
                    p= Proveedor.traer(Integer.parseInt(ID.getText().trim()));
                    if(p!=null){
                        eliminar();
                    }else{
                        JOptionPane.showMessageDialog(null,"No existe Proveedor");
                    }}else{
                         JOptionPane.showMessageDialog(null,"Ingrese un ID VALIDO");
                }
            }
        });
    }

    private void iniciarcomponentes(){
        setSize(550, 350);
        setContentPane(VContadorProveedor);
    }

    private void registrar(){

        p=new Proveedor();
        p.setOp('i');
        p.setDireccion_roveedor(DIRECCION.getText());
        p.setRazon_social(RAZONSOCIAL.getText());
        p.setRuc_proveedor(RUC.getText());
        p.setTelefono(TELEFONO.getText());
        try {
            p.gestionar();
            JOptionPane.showMessageDialog(null,"Proveedor registrado");
            limpiarcontroles();
            p=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizar(){
            p = new Proveedor();
            p.setOp('a');
            p.setId_proveedor(Integer.parseInt(ID.getText().trim()));
            p.setDireccion_roveedor(DIRECCION.getText());
            p.setRazon_social(RAZONSOCIAL.getText());
            p.setRuc_proveedor(RUC.getText());
            p.setTelefono(TELEFONO.getText());
            try {
                p.gestionar();
                JOptionPane.showMessageDialog(null,"Cambios guardados");
                limpiarcontroles();
                p=null;
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    private void mostrarDatos(){
            ID.setText(String.valueOf(p.getId_proveedor()));
            DIRECCION.setText(p.getDireccion_roveedor());
            RAZONSOCIAL.setText(p.getRazon_social());
            RUC.setText(p.getRuc_proveedor());
            TELEFONO.setText(p.getTelefono());
    }
    private void limpiarcontroles(){
        ID.setText(null);
        DIRECCION.setText(null);
        RAZONSOCIAL.setText(null);
        RUC.setText(null);
        TELEFONO.setText(null);
    }

    private Boolean hayespaciosenblanco(){
        if(ID.getText().trim().length()>0&&ID.getText().trim().matches("\\d*")&&DIRECCION.getText().length()>0&&RAZONSOCIAL.getText().length()>0&&RUC.getText().trim().matches("\\d*")&&TELEFONO.getText().trim().matches("\\d*")){
            return false;
        }
        else{
            return true;
        }
    }
    private void eliminar(){
            p=new Proveedor();
            if(Integer.parseInt(ID.getText().trim())!=1){
                p.setId_proveedor(Integer.parseInt(ID.getText().trim()));
                p.setOp('e');
            try {
                p.gestionar();
                JOptionPane.showMessageDialog(null,"Proveedor eliminado");
                limpiarcontroles();
                p=null;
            } catch (Exception e) {
                e.printStackTrace();
            }}else{
                JOptionPane.showMessageDialog(null,"No puede eliminar a este proveedor");
            }

    }
    public JPanel getpanel(){
        return VContadorProveedor;
    }
}
