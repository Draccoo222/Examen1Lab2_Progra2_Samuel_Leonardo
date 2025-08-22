/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author unwir
 */
public class Game extends RentItem implements MenuActions {
    private Calendar fecha;
    private ArrayList<String> especs;

    public Game(int codigo, String nombre) {
        super(codigo, nombre, 20);
        fecha = Calendar.getInstance();
        especs = new ArrayList<>();
    }
    
    public void setFechaPublicacion(int dia, int mes, int year){
        fecha.set(year, mes, dia);
    }
  
    public double pagoRenta(int dias){
        return 20*dias;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setEspecs(ArrayList<String> especs) {
        this.especs = especs;
    }
    
    
   
    public void ejecutarOpcion(int opcion){
        switch(opcion){
            case 0 -> setearFechaPub();
            case 1 -> cambiarSpecs();
            case 2 -> mostrarSpecs();
        }
    
    }
    
    private void addSpecs(String spec){
        especs.add(spec);
    }
    
    public void subMenu(){
        GameSubMenu gsm = new GameSubMenu();
        gsm.setGame(this);
        gsm.setVisible(true);
    }
    
    private void setearFechaPub(){
            JDateChooser fechasel = new JDateChooser();
        int opcion = JOptionPane.showConfirmDialog(null, 
                fechasel,               
                "Seleccione la fecha",           
                JOptionPane.OK_CANCEL_OPTION
        );
        
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                Date fechaSelect = fechasel.getDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaSelect);

                this.setFechaPublicacion(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
                System.out.println("Fecha nova: " + this.getFecha().getTime());
            } catch (NullPointerException en) {
                JOptionPane.showMessageDialog(null, "ERROR, porfavor no dejar la fecha en blanco");
            }
        }
    }
    
    private void cambiarSpecs(){
        JTextField espec = new JTextField(); 
        
      int opcion = JOptionPane.showConfirmDialog(null, 
                espec,               
                "Escriba la especificacion que desea",           
                JOptionPane.OK_CANCEL_OPTION
        );
        
        if (opcion == JOptionPane.OK_OPTION) {
            if (!espec.getText().isBlank() && !espec.getText().isEmpty()) {
                String selecEspec = espec.getText().trim();
                addSpecs(selecEspec);
                JOptionPane.showMessageDialog(null, "Especificacion agregada con exito!");
            } else {
                JOptionPane.showMessageDialog(null, "No puede dejar el campo en blanco");
            }
        }
    }
    
    private void mostrarSpecs(){
        JOptionPane.showMessageDialog(null, "Especificaciones: " + imprimirSpecs(0));
    }

    
    
    public String imprimirSpecs(int integer){
        if(integer >= especs.size()){
            return " ";
        }
        return "\nDato: " + (integer + 1) + ": " + especs.get(integer) + imprimirSpecs(integer + 1);
    }

    @Override
    public String toString() {
        return super.toString() + "Fecha de Publicacion: " + fecha + "-PS3 Game";
    }

    
    
   
    
    
    
    
}
