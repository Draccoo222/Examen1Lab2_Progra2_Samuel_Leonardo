/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author hnleo
 */
public class MainPantalla extends JFrame {

    private JLabel lblTitulo, lblSubtitulo;
    private JButton btnAgregarItem, btnRentar, btnEjecutarSubmenu, btnImprimirTodo, btnSalir;
    private ArrayList<RentItem> inventario = new ArrayList<>();

    public MainPantalla() {
        pantalla();
        ventana();
    }

    private void pantalla() {
        setLayout(new BorderLayout());

        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setBackground(new Color(25, 25, 35));
        panelTitulos.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        lblTitulo = new JLabel("JAVABUSTER");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(255, 215, 0)); // Dorado
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblSubtitulo = new JLabel("Donde tus películas y videojuegos tienen vida");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitulos.add(lblTitulo);
        panelTitulos.add(Box.createRigidArea(new Dimension(0, 10)));
        panelTitulos.add(lblSubtitulo);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setBackground(new Color(35, 35, 45));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 12, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        btnAgregarItem = crearBoton("Agregar Ítem", new Color(70, 130, 180));
        btnRentar = crearBoton("Rentar", new Color(60, 179, 113));
        btnEjecutarSubmenu = crearBoton("Ejecutar Submenú", new Color(255, 140, 0));
        btnImprimirTodo = crearBoton("Imprimir Todo", new Color(147, 112, 219));
        btnSalir = crearBoton("Salir", new Color(220, 20, 60));

        panelBotones.add(btnAgregarItem, gbc);
        panelBotones.add(btnRentar, gbc);
        panelBotones.add(btnEjecutarSubmenu, gbc);
        panelBotones.add(btnImprimirTodo, gbc);
        panelBotones.add(btnSalir, gbc);

        add(panelTitulos, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        agregarEventos();
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);

        boton.setPreferredSize(new Dimension(280, 50));
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);

        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            Color colorOriginal = colorFondo;

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorOriginal.brighter());
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorOriginal);
                boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return boton;
    }

    public RentItem buscarItem(int codigo, int x) { //Función recursiva 
        if (x >= inventario.size()) {
            return null;
        }
        if (inventario.get(x).getCodigo() == codigo) {
            return inventario.get(x);
        }

        return buscarItem(codigo, x + 1);

    }

    private void agregarEventos() {
        btnAgregarItem.addActionListener(e -> {

            String[] op = {"Movie", "Game"};
            String tipo = (String) JOptionPane.showInputDialog(null, "¿Que tipo de ítem deseas agregar?", "Agregar", JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
            if (tipo == null) {
                return;
            }

            try {
                int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese código: "));
                if (buscarItem(codigo, 0) != null) {
                    JOptionPane.showMessageDialog(this, "El código de Item ingresar ya existe.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                double baseRenta = 0;
                String nombreItem = JOptionPane.showInputDialog("Ingrese nombre: ");
                if (tipo.equals("Movie")) {
                    baseRenta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese base de renta: "));
                }
                   
                JDateChooser elegirFecha = new JDateChooser();
                elegirFecha.setDate(new Date());
                int opF = JOptionPane.showConfirmDialog(null, elegirFecha, "Fecha", JOptionPane.OK_CANCEL_OPTION);
                Calendar fechaEst = Calendar.getInstance();
                if(opF == JOptionPane.OK_OPTION){
                    fechaEst.setTime(elegirFecha.getDate());
                }
                
                JFileChooser imagenPortada = new JFileChooser();
                imagenPortada.setDialogTitle("Seleccione la portada.");
                String ruta = null;
                int resultado = imagenPortada.showOpenDialog(null);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    ruta = imagenPortada.getSelectedFile().getAbsolutePath();
                }
             
                
                RentItem tempInventario = null;

                if (tipo.equals("Movie")) {
                    Movie peliculaAgregada = new Movie(codigo, nombreItem, baseRenta);
                    
                    peliculaAgregada.setImagen(new ImageIcon(ruta));
                    peliculaAgregada.setFecha(fechaEst);
                    tempInventario = peliculaAgregada;
                } else if (tipo.equals("Game")) {
                    Game juegoAgregado = new Game(codigo, nombreItem);
                    juegoAgregado.setImagen(new ImageIcon(ruta));
                   juegoAgregado.setFechaPublicacion(fechaEst.get(Calendar.DAY_OF_MONTH), fechaEst.get(Calendar.MONTH), fechaEst.get(Calendar.YEAR));
                    tempInventario = juegoAgregado;

                }
                
                if(tempInventario!= null){
                    inventario.add(tempInventario);
                    JOptionPane.showMessageDialog(this, (tipo.equals("Movie") ? "Movie":"Game") + " Agregado Correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } catch (Exception i) {
                JOptionPane.showMessageDialog(this, "No se seleccionó ninguna opción.");
            }

        });

        btnRentar.addActionListener(e -> {
        });

        btnEjecutarSubmenu.addActionListener(e -> {
        });

        btnImprimirTodo.addActionListener(e -> {
          RentItem item= buscarItem(12, 0);
          if(item instanceof Movie){
              Movie m = (Movie)item;
              System.out.println(m.getEstado());
          }
        });

        btnSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea salir?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void ventana() {
        setTitle("JAVABUSTER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 600);
        setResizable(false);
        setLocationRelativeTo(null);

    }

}
