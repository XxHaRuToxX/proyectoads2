package com.saltamontes.vistas;

import com.saltamontes.modelos.Proveedor;
import com.saltamontes.modelos.Trabajador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class VAdministrador extends JFrame {
    private JTextField TELEFONO_USUARIO;
    private JTextField DIRECCION_USUARIO;
    private JButton registrarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTextField ID_USUARIO;
    private JTextField GENERO_USUARIO;
    private JPanel Vgestionar_usuario;
    private JPasswordField CONTRASEÑA_USUARIO;
    private JTextField USUARIO;
    private JTextField NOMBRES;
    private JTextField APELLIDOS;
    private JTextField DNI;
    private JButton CERRARSESION;
    private Trabajador tra;
    private JComboBox CARGO_USUARIO;

    public VAdministrador() {
        iniciarComponentes();
        ID_USUARIO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(ID_USUARIO.getText().trim().length()>0&&ID_USUARIO.getText().trim().matches("\\d*")){
                    tra= Trabajador.traer(Integer.parseInt(ID_USUARIO.getText().trim()));
                    if(tra==null){
                    JOptionPane.showMessageDialog(null,"no existe trabajador");
                        limpiarcontroles();
                    }else{
                            mostrarDatos();
                    }}else{
                        JOptionPane.showMessageDialog(null,"Ingrese un ID VALIDO");
                    }
                }
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID_USUARIO.setText("1");
                if(!hayespaciosenblanco()){
                    registrar();
                }else{
                    JOptionPane.showMessageDialog(null,"rellene los espacios");
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!hayespaciosenblanco())){
                    actualizar();
                }else{
                    JOptionPane.showMessageDialog(null,"rellene los espacios");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ID_USUARIO.getText().trim().length()>0&&ID_USUARIO.getText().trim().matches("\\d*")){
                    tra= Trabajador.traer(Integer.parseInt(ID_USUARIO.getText().trim()));
                    if(tra!=null){
                        eliminar();
                    }else{
                        JOptionPane.showMessageDialog(null,"No existe Trabajador");
                    }}else{
                    JOptionPane.showMessageDialog(null,"Ingrese un ID VALIDO");
                }
            }
        });

        CERRARSESION.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VPrincipalPrograma VP=new VPrincipalPrograma();
                VP.setVisible(true);
            }
        });

    }

    private Boolean hayespaciosenblanco(){
        if(ID_USUARIO.getText().trim().length()>0&&ID_USUARIO.getText().trim().matches("\\d*")&&NOMBRES.getText().length()>0&&APELLIDOS.getText().length()>0&&DNI.getText().length()>0&&GENERO_USUARIO.getText().length()>0&&TELEFONO_USUARIO.getText().trim().matches("\\d*")&&!CARGO_USUARIO.getSelectedItem().equals("SELECCIONE-CARGO")&&DIRECCION_USUARIO.getText().length()>0&&USUARIO.getText().length()>0&&CONTRASEÑA_USUARIO.getText().length()>0){
            return false;
        }
        else{
            return true;
        }
    }

    private void registrar(){
        tra= new Trabajador();
        tra.setContraseña(CONTRASEÑA_USUARIO.getText());
        tra.setNombres_persona(NOMBRES.getText());
        tra.setApellidos_persona(APELLIDOS.getText());
        tra.setDni_persona(DNI.getText());
        tra.setGenero(GENERO_USUARIO.getText());
        tra.setTelefono(TELEFONO_USUARIO.getText());
        tra.setCargo(String.valueOf(CARGO_USUARIO.getSelectedItem()));
        tra.setDireccion_trabajador(DIRECCION_USUARIO.getText());
        tra.setUsuario(USUARIO.getText());
        tra.setOp('i');
        try {
            tra.gestionar();
            JOptionPane.showMessageDialog(null,"registrado");
            tra=null;
            limpiarcontroles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void actualizar(){
        tra=new Trabajador();
            tra.setId_trabajador(Integer.parseInt(ID_USUARIO.getText().trim()));
            tra.setOp('a');
            if(tra.getId_persona()!=1){ tra.setCargo(String.valueOf(CARGO_USUARIO.getSelectedItem()));}
                tra.setContraseña(CONTRASEÑA_USUARIO.getText());
                tra.setNombres_persona(NOMBRES.getText());
                tra.setApellidos_persona(APELLIDOS.getText());
                tra.setDni_persona(DNI.getText());
                tra.setGenero(GENERO_USUARIO.getText());
                tra.setTelefono(TELEFONO_USUARIO.getText());
                tra.setDireccion_trabajador(DIRECCION_USUARIO.getText());
                tra.setUsuario(USUARIO.getText());
            try {
                tra.gestionar();
                JOptionPane.showMessageDialog(null,"cambios guardados");
                tra=null;
                limpiarcontroles();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void eliminar(){
        tra=new Trabajador();
            if(Integer.parseInt(ID_USUARIO.getText().trim())!=1){
                tra.setId_trabajador(Integer.parseInt(ID_USUARIO.getText().trim()));
                tra.setOp('e');
                try {
                    tra.gestionar();
                    JOptionPane.showMessageDialog(null,"Usuario eliminado");
                    tra=null;
                    limpiarcontroles();
                } catch (Exception e) {
                e.printStackTrace();
                }
                } else {
                JOptionPane.showMessageDialog(null, "no puede eliminar al administrador");
                 }
    }

    private void  mostrarDatos(){
        ID_USUARIO.setText(String.valueOf(tra.getId_trabajador()));
        CONTRASEÑA_USUARIO.setText(tra.getContraseña());
        NOMBRES.setText(tra.getNombres_persona());
        APELLIDOS.setText(tra.getApellidos_persona());
        DNI.setText(tra.getDni_persona());
        GENERO_USUARIO.setText(tra.getGenero());
        TELEFONO_USUARIO.setText(tra.getTelefono());
        CARGO_USUARIO.setSelectedItem(tra.getCargo());
        DIRECCION_USUARIO.setText(tra.getDireccion_trabajador());
        USUARIO.setText(tra.getUsuario());

    }

    private void iniciarComponentes(){
        setSize(800, 480);
        setContentPane(Vgestionar_usuario);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setUndecorated(true);
    }


    private void limpiarcontroles(){
        ID_USUARIO.setText(null);
        CONTRASEÑA_USUARIO.setText(null);
        GENERO_USUARIO.setText(null);
        TELEFONO_USUARIO.setText(null);
        CARGO_USUARIO.setSelectedItem("SELECCIONE-CARGO");
        DIRECCION_USUARIO.setText(null);
        NOMBRES.setText(null);
        APELLIDOS.setText(null);
        DNI.setText(null);
        USUARIO.setText(null);
    }

}
