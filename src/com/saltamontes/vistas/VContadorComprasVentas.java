package com.saltamontes.vistas;

import com.saltamontes.modelos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.util.Calendar;

public class VContadorComprasVentas extends JFrame {
    private JPanel VContadorCompradorVentas;
    private JButton eliminarProductoButton;
    private JButton AÑADIRPRODUCTO;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField1;
    private JTable tablaproductos;
    private JPanel BOLETA;
    private JTextField TOTAL;
    private JButton imprimirButton;
    private Producto p;
    private DefaultTableModel productos;
    private int cantidad;
    private Float calculartotal;
    private JLabel FECHA;


    public VContadorComprasVentas() {
        iniciarcomponentes();
        calculartotal= Float.valueOf(0);
        String[] arraysproductos={"PRODUCTO","CANTIDAD","PRECIO","TOTAL"};

        tablaproductos.setModel(new DefaultTableModel(null,arraysproductos));

        productos =(DefaultTableModel) tablaproductos.getModel();

        AÑADIRPRODUCTO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                añadirproductos();
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarproducto();

            }
        });
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printComponenet(BOLETA);
            }
        });
    }
    private void añadirproductos(){
                if(pedirproducto()){
                Float total=Float.parseFloat(String.valueOf(cantidad*p.getPrecio_producto()));
                productos.addRow(new Object[]{p.getNombre_producto(), cantidad, String.valueOf(p.getPrecio_producto()),String.valueOf(total)});
                calculartotal=calculartotal+Float.parseFloat(String.valueOf(cantidad*p.getPrecio_producto()));
                TOTAL.setText(String.valueOf(calculartotal));
                }else{
                    JOptionPane.showMessageDialog(null,"no se agregaron productos");
                }

    }
    private boolean pedirproducto(){
        String[] prod=Producto.traernombres();
        String produ= String.valueOf(JOptionPane.showInputDialog(null,"Seleccione Producto","Productos",JOptionPane.PLAIN_MESSAGE,null,prod,prod[0]));
        if(!produ.equals("selceccione-producto")){
        p= Producto.traerpornombre(produ);
        if(p!=null){
            return pedircantidad();
        }else{
            JOptionPane.showMessageDialog(null,"operación cancelada");
            return false;
        }}else{
            JOptionPane.showMessageDialog(null,"no seleccionó producto");
            return pedirproducto();
        }
    }
    private boolean pedircantidad(){
        String canti=JOptionPane.showInputDialog(null,"ingrese la cantidad");
        if(canti!=null){
        if(canti.trim().length()>0&&canti.trim().matches("\\d*")){
            cantidad= Integer.parseInt(canti);
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"ingrese una cantidad valida");
            return pedircantidad();
        }}else{
            JOptionPane.showMessageDialog(null,"operación cancelada");
            return false;
        }
    }

    private void iniciarcomponentes(){
        setSize(550, 350);
        setContentPane(VContadorCompradorVentas);
        FECHA.setText(fecha());
    }
    public JPanel getpanel(){
        return VContadorCompradorVentas;
    }

    private void eliminarproducto(){
        int fse;
        int conf;
        fse=tablaproductos.getSelectedRow();
        if(fse!=-1){
            conf=JOptionPane.showOptionDialog(null,"¿está seguro que desea eliminar este producto?","eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "si", "no"},"si");
            if(conf==JOptionPane.YES_OPTION){
                productos =(DefaultTableModel)tablaproductos.getModel();
                productos.removeRow(fse);
            }
        }else{
            JOptionPane.showMessageDialog(null,"seleccione el producto que desee eliminar");
        }
    }
    private String fecha(){
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));
        String fecha=dia+"/"+mes+"/"+annio;
        return fecha;
    }
    public void printComponenet(Component component){
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable (new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum){
                if (pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Dimension dim = component.getSize();
                double cHeight = dim.getHeight();
                double cWidth = dim.getWidth();

                // get the bounds of the printable area
                double pHeight = pf.getImageableHeight();
                double pWidth = pf.getImageableWidth();

                double pXStart = pf.getImageableX();
                double pYStart = pf.getImageableY();

                double xRatio = pWidth / cWidth;
                double yRatio = pHeight / cHeight;

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.translate(pXStart, pYStart);
                g2.scale(xRatio, yRatio);
                component.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false)
            return;
        try {
            pj.print();
        } catch (Exception ex) {
            // handle exception
        }
    }
}
