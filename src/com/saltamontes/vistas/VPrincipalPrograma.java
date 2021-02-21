package com.saltamontes.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VPrincipalPrograma extends JFrame{
    private JPanel PANELADMI;
    private JPanel PANELCONTADOR;
    private JPanel PANELALMACENERO;
    private JButton contadorButton;
    private JButton administradorDelSistemaButton;
    private JButton almaceneroButton;
    private JPanel PanelPrincipalPrograma;
    Login log;
//    imagenfondo ima=new imagenfondo("/com/saltamontes/recursos/cacao.jpg");
    public VPrincipalPrograma() {
        setResizable(false);
        iniciarComponentes();
        administradorDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargarLoginAdministrador();
                dispose();

            }
        });
        contadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargarLoginContador();
                dispose();
            }
        });

        almaceneroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargarLoginAlmacenero();
                dispose();
            }
        });
    }
    private void iniciarComponentes(){
        setSize(1000, 600);
        setContentPane(PanelPrincipalPrograma);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
//        setUndecorated(true);
//        ima.add(COOPAIDI,0,0);
//        ima.add(PANELADMI);
//        ima.add(PANELCONTADOR);
//        ima.add(PANELALMACENERO);
//        ima.add(PANELCLIENTE);
//        COOPAIDI.show();
//        PANELADMI.show();
//        PANELCONTADOR.show();
//        PANELALMACENERO.show();
//        PANELCLIENTE.show();

    }

    private void CargarLoginAdministrador(){
        log=new Login("administrador");
        log.setVisible(true);


    }
    private void CargarLoginContador(){
        log=new Login("contador");
        log.setVisible(true);

    }
    private void CargarLoginAlmacenero(){
        log=new Login("almacenero");
        log.setVisible(true);

    }
//    public class imagenfondo extends JPanel{
//        ImageIcon imagen;
//        String nombre;
//        public imagenfondo(String nombre){
//            this.nombre=nombre;
//        }
//        public void paint(Graphics g){
//            Dimension tamanio=getSize();
//            imagen=new ImageIcon(getClass().getResource(nombre));
//            g.drawImage(imagen.getImage(),0,0,tamanio.width,tamanio.height,null);
//            setOpaque(false);
//            super.paint(g);
//        }
//    }
}



