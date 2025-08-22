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
    
    private JDateChooser fecha = new JDateChooser();

    public GameSubMenu(Game curGame) {
        this.curGame = curGame;
        System.out.println(curGame.getFecha().getTime());
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
        actFecha.addActionListener(e -> {
            curGame.ejecutarOpcion(0);
        });
        
        orden.add(actFecha);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton agrSpecs = new JButton("Agregar Especificaciones");
        agrSpecs.setAlignmentX(Component.CENTER_ALIGNMENT);
        agrSpecs.setSize(100, 30);
        agrSpecs.addActionListener(e -> {
            curGame.ejecutarOpcion(1);
        });
        orden.add(agrSpecs);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton verSpecs = new JButton("Mirar Especificaciones");
        verSpecs.setAlignmentX(Component.CENTER_ALIGNMENT);
       verSpecs.setSize(100, 30);
        verSpecs.addActionListener(e -> {
            curGame.ejecutarOpcion(2);
        });
        orden.add(verSpecs);
        orden.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton regresar = new JButton("Salir");
        regresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        regresar.setSize(100, 30);
        regresar.addActionListener(e -> {
            this.dispose();
        });
        orden.add(regresar);

        orden.add(Box.createVerticalGlue()); 

        add(tit, BorderLayout.NORTH);
        add(orden, BorderLayout.CENTER);

    }

    public void setGame(Game curGame) {
        this.curGame = curGame;
    }
    
  
  

}
