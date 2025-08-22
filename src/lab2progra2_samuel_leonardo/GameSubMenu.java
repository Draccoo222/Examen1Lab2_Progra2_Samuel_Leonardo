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
    private JButton actFecha;
    private JButton agrSpecs;
    private JButton verSpecs;
    private JButton regresar;
    private JPanel panelCentral;
    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
  
    
    private JDateChooser fecha = new JDateChooser();
    
    public GameSubMenu(Game curGame) {
        this.curGame = curGame;
        System.out.println(curGame.getFecha().getTime());
        pantalla();
        ventana();
    }
    
    private void pantalla() {
        setLayout(new BorderLayout());
       
        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setBackground(new Color(25, 25, 35));
        panelTitulos.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        
        lblTitulo = new JLabel("SUBMENÚ JAVABUSTER");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 215, 0)); 
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblSubtitulo = new JLabel("Configuraciones avanzadas");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        panelTitulos.add(lblTitulo);
        panelTitulos.add(Box.createRigidArea(new Dimension(0, 8)));
        panelTitulos.add(lblSubtitulo);
        panelTitulos.add(Box.createRigidArea(new Dimension(0, 10)));
       
        
        panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setBackground(new Color(35, 35, 45));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 12, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        actFecha = crearBoton("Actualizar Fecha", new Color(70, 130, 180));
        actFecha.addActionListener(e -> {
            curGame.ejecutarOpcion(0);
        });
        
        agrSpecs = crearBoton("Agregar Especificaciones", new Color(60, 179, 113));
        agrSpecs.addActionListener(e -> {
            curGame.ejecutarOpcion(1);
        });
        
        verSpecs = crearBoton("Mirar Especificaciones", new Color(147, 112, 219));
        verSpecs.addActionListener(e -> {
            curGame.ejecutarOpcion(2);
        });
        
        regresar = crearBoton("Salir", new Color(220, 20, 60));
        regresar.addActionListener(e -> {
            this.dispose();
        });
        
        panelCentral.add(actFecha, gbc);
        panelCentral.add(agrSpecs, gbc);
        panelCentral.add(verSpecs, gbc);
        
        gbc.insets = new Insets(25, 0, 12, 0);
        panelCentral.add(regresar, gbc);
        
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(25, 25, 35));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
    
        add(panelTitulos, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }
    
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(320, 55));
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(12, 25, 12, 25)
        ));
        
        
        return boton;
    }
    
    private void ventana() {
        setTitle("JAVABUSTER - Submenú de Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setResizable(false);
        setLocationRelativeTo(null); 
        
      
    }
    
    public void setGame(Game curGame) {
        this.curGame = curGame;
        
    }
    
    
}