package com.saltamontes.vistas;

import com.saltamontes.modelos.Trabajador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton BOTONINGRESAR;
    private JButton BOTONOLVIDECONTRASEÑA;
    private JTextField USUARIO;
    private JPasswordField CONTRASEÑA;
    private JButton CERRAR;
    private Trabajador tra;
    private String cargo;

    public Login(String cargo) {
        this.cargo=cargo;
        getRootPane().setDefaultButton(BOTONINGRESAR);
        iniciarComponentes();
        BOTONINGRESAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(cargo.equals("administrador")){
                   if(!hayespaciosenblanco()){
                       cargarloginAdmi();
                   }else
                       {
                       JOptionPane.showMessageDialog(null,"Ingrese usuario y contraseña");
                   }
               }else if(cargo.equals("contador")){
                   if(!hayespaciosenblanco()){
                       cargarloginConta();
                   }else
                           {
                       JOptionPane.showMessageDialog(null,"Ingrese usuario y contraseña");
                   }}
               else{
                   if(!hayespaciosenblanco()){
                       cargarloginAlmacenero();
                   }else
                   {
                       JOptionPane.showMessageDialog(null,"Ingrese usuario y contraseña");
                   }
               }
            }
        });

        BOTONOLVIDECONTRASEÑA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Contacte al administrador del sistema");
            }
        });


        CERRAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VPrincipalPrograma VP=new VPrincipalPrograma();
                VP.setVisible(true);
            }
        });
    }

    private void cargarloginAdmi(){
        tra=Trabajador.TraerUsuario(USUARIO.getText(),CONTRASEÑA.getText());
        if(tra!=null){
        if(tra.getCargo().equals(cargo)){
            VAdministrador v= new VAdministrador();
            v.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            limpiarbotones();
        }}else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            limpiarbotones();
        }
    }
    private void cargarloginAlmacenero(){
        tra=Trabajador.TraerUsuario(USUARIO.getText(),CONTRASEÑA.getText());
        if(tra!=null){
            if(tra.getCargo().equals(cargo)){
                VAlmacenero v= new VAlmacenero();
                v.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
                limpiarbotones();
            }}else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            limpiarbotones();
        }
    }

    private void cargarloginConta(){
        tra=Trabajador.TraerUsuario(USUARIO.getText(),CONTRASEÑA.getText());
        if(tra!=null){
        if(tra.getCargo().equals(cargo)){
            VContador v= new VContador();
            v.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            limpiarbotones();
        }}else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            limpiarbotones();
        }
    }
    private void limpiarbotones(){
        USUARIO.setText(null);
        CONTRASEÑA.setText(null);
    }
    private Boolean hayespaciosenblanco(){
        if(USUARIO.getText().length()>0&&CONTRASEÑA.getText().length()>0){
            return false;
        }
        else{
            return true;
        }
    }

    private void iniciarComponentes(){
        setSize(350, 150);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(2);
        setUndecorated(true);

    }

}
