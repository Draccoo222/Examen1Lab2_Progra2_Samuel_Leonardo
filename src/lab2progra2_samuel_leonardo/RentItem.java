/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import javax.swing.ImageIcon;

/**
 *
 * @author unwir
 */
public abstract class RentItem {
    
    protected int codigo;
    protected String nombre;
    protected double baseRenta;
    protected int cantCopias;
    protected ImageIcon imagen;
    

    public RentItem(int codigo, String nombre, double baseRenta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.baseRenta = baseRenta;
        cantCopias = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getBaseRenta() {
        return baseRenta;
    }

    public void setBaseRenta(double baseRenta) {
        this.baseRenta = baseRenta;
    }

    public int getCantCopias() {
        return cantCopias;
    }

    public void setCantCopias(int cantCopias) {
        this.cantCopias = cantCopias;
    }

    abstract double pagoRenta(int dias);

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    
    
    @Override
    public String toString() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + 
                "\nBase Renta: " + baseRenta + ".Lps" + "\nCantidad de Copias: " + cantCopias;
    }
    
   
    
    
    
    
    
            
    
    
    
    
    
}
