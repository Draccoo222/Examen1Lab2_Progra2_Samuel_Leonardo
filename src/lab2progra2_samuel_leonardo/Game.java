/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import java.util.ArrayList;
import java.util.Calendar;
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
    
    public void ejecutarOpcion(int opcion){
    
    }
    
    private void addSpecs(String spec){
        especs.add(spec);
    }
    
    public void subMenu(){
        GameSubMenu gsm = new GameSubMenu();
        gsm.setGame(this);
        gsm.setVisible(true);
    }
    
    
    public String imprimirSpecs(int integer){
        if(integer >= especs.size()){
            return " ";
        }
        return "Dato: " + integer + ": " + especs.get(integer) + imprimirSpecs(integer + 1);
    }

    @Override
    public String toString() {
        return super.toString() + "Fecha de Publicacion: " + fecha + "-PS3 Game";
    }

    
    
   
    
    
    
    
}
