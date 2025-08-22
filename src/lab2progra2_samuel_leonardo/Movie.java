/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author hnleo
 */
public class Movie extends RentItem {

    private Calendar fecha;

    public Movie(int codigo, String nombre, double baseRenta) {
        super(codigo, nombre, baseRenta);
        fecha = Calendar.getInstance();
    }

    public String getEstado() {
        Calendar fechaComparativa = Calendar.getInstance();
        fechaComparativa.add(Calendar.MONTH, -3);

        if (fecha.getTime().after(fechaComparativa.getTime())) {
            return "ESTRENO";
        }
        return "NORMAL";
    }

    @Override
    public String toString() {
        return super.toString() + "\nEstado: " + getEstado() + "\n- Movie";
    }

    @Override
    double pagoRenta(int dias) {
     double totalPagar = baseRenta * dias;
     if(getEstado().equals("ESTRENO")){
         if(dias>2){
            totalPagar += (dias-2)*50;
         }
     } else {
         if(dias>5){
             totalPagar += (dias-5)*30;
         }
     }
     return totalPagar;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

  
    

}
