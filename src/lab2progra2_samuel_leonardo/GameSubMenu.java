/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author unwir
 */
public class GameSubMenu extends JFrame {

    private Game curGame;

    private JButton actFecha = new JButton();
    private JButton agrSpecs = new JButton();
    private JButton verSpecs = new JButton();
    private JButton regresar = new JButton();

    private JPanel orden;
    
    private JDateChooser fecha;

    public GameSubMenu() {
        initComps();
    }

    public void initComps() {
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel tit = new JLabel("Sub Menu");
        tit.setBounds(200, 20, 100, 50);
        tit.setFont(new Font("Arial Black", Font.BOLD, 24));
        tit.setHorizontalAlignment(JLabel.CENTER);

        orden = new JPanel();
        orden.setLayout(new BoxLayout(orden, BoxLayout.Y_AXIS));

        orden.add(Box.createVerticalGlue()); 

        JButton actFecha = new JButton("Actualizar Fecha");
        actFecha.setAlignmentX(Component.CENTER_ALIGNMENT); 
        actFecha.setSize(100, 30);
        orden.add(actFecha);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton agrSpecs = new JButton("Agregar Especificaciones");
        agrSpecs.setAlignmentX(Component.CENTER_ALIGNMENT);
          agrSpecs.setSize(100, 30);
        orden.add(agrSpecs);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton verSpecs = new JButton("Mirar Especificaciones");
        verSpecs.setAlignmentX(Component.CENTER_ALIGNMENT);
         verSpecs.setSize(100, 30);
        orden.add(verSpecs);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton regresar = new JButton("Salir");
        regresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        regresar.setSize(100, 30);
        orden.add(regresar);

        orden.add(Box.createVerticalGlue()); 

        add(tit, BorderLayout.NORTH);
        add(orden, BorderLayout.CENTER);

    }

    public void setGame(Game curGame) {
        this.curGame = curGame;
    }
    
    public void setearFechaPub(){
        int opcion = JOptionPane.showConfirmDialog(null, 
                fecha,               
                "Seleccione la fecha",           
                JOptionPane.OK_CANCEL_OPTION
        );
        
        if(opcion == JOptionPane.OK_OPTION){
       
            Date fechaSelect = fecha.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaSelect);
            
            
            
        }
                
        
    
    
    }

    public static void main(String[] args) {
        new GameSubMenu().setVisible(true);

    }

}
