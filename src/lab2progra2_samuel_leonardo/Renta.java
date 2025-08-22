/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author unwir
 */
public class Renta extends JFrame {
    private RentItem curItem;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JTextField txtDias;
    private JButton btnCalcular;
    private JButton btnVolver;
    private JLabel lblTotalPagar;
    
    public Renta(RentItem curItem) {
        this.curItem = curItem;
        initComponents();
        setupWindow();
        mostrarInfoItem();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
     
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.6);
        
      
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setBackground(new Color(35, 35, 45));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 80), 2),
            "INFORMACIÓN DEL ITEM",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(255, 215, 0)
        ));
        
        
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setBackground(new Color(45, 45, 55));
        panelDerecho.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 80), 2),
            "CALCULAR RENTA",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(255, 215, 0)
        ));
        
        setupPanelDerecho();
        
        splitPane.setLeftComponent(panelIzquierdo);
        splitPane.setRightComponent(panelDerecho);
        
        add(splitPane, BorderLayout.CENTER);
        
     
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(25, 25, 35));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        btnVolver = crearBoton("Volver", new Color(70, 130, 180));
        btnVolver.addActionListener(e -> dispose());
        
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void setupPanelDerecho() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
   
        JLabel lblDias = new JLabel("Días de renta:");
        lblDias.setFont(new Font("Arial", Font.BOLD, 14));
        lblDias.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDerecho.add(lblDias, gbc);
      
        txtDias = new JTextField(10);
        txtDias.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDias.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelDerecho.add(txtDias, gbc);
    
        btnCalcular = crearBoton("Calcular Renta", new Color(34, 139, 34));
        btnCalcular.addActionListener(this::calcularRenta);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDerecho.add(btnCalcular, gbc);
    
        lblTotalPagar = new JLabel("Total a pagar: 0.00 Lps");
        lblTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalPagar.setForeground(new Color(144, 238, 144));
        lblTotalPagar.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelDerecho.add(lblTotalPagar, gbc);
    }
    
    private void mostrarInfoItem() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new BorderLayout());
        contenido.setBackground(new Color(35, 35, 45));
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBackground(new Color(60, 60, 70));
        panelImagen.setPreferredSize(new Dimension(250, 200));
        panelImagen.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 90), 1));
        
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);
        
        if (curItem.getImagen() != null) {
            lblImagen.setIcon(curItem.getImagen());
        } else {
            String tipoIcono = curItem instanceof Movie ? "Movie" : "Game";
            lblImagen.setText(tipoIcono);
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 48));
        }
        lblImagen.setForeground(new Color(200, 200, 200));
        
        panelImagen.add(lblImagen, BorderLayout.CENTER);
        
      
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(35, 35, 45));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
       
        JLabel lblCodigo = new JLabel("Código: " + curItem.getCodigo());
        JLabel lblNombre = new JLabel("Nombre: " + curItem.getNombre());
        JLabel lblTipo = new JLabel("Tipo: " + (curItem instanceof Movie ? "Movie" : "Game"));
        JLabel lblBaseRenta = new JLabel("Precio base: " + String.format("%.2f", curItem.getBaseRenta()) + " Lps");
        JLabel lblCopias = new JLabel("Copias disponibles: " + curItem.getCantCopias());
        
        // Estilos para las etiquetas
        JLabel[] labels = {lblCodigo, lblNombre, lblTipo, lblBaseRenta, lblCopias};
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelInfo.add(label);
            panelInfo.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
     
        if (curItem instanceof Movie) {
            Movie movie = (Movie) curItem;
            JLabel lblEstado = new JLabel("Estado: " + movie.getEstado());
            lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
            lblEstado.setForeground(movie.getEstado().equals("ESTRENO") ? 
                new Color(255, 215, 0) : new Color(144, 238, 144));
            lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelInfo.add(lblEstado);
            
            // Información de fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            JLabel lblFecha = new JLabel("Fecha: " + sdf.format(movie.getFecha().getTime()));
            lblFecha.setFont(new Font("Arial", Font.PLAIN, 12));
            lblFecha.setForeground(new Color(180, 180, 180));
            lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
            panelInfo.add(lblFecha);
            
        } else if (curItem instanceof Game) {
            Game game = (Game) curItem;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            JLabel lblFechaPub = new JLabel("Fecha publicación: " + sdf.format(game.getFecha().getTime()));
            lblFechaPub.setFont(new Font("Arial", Font.PLAIN, 12));
            lblFechaPub.setForeground(new Color(180, 180, 180));
            lblFechaPub.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelInfo.add(lblFechaPub);
        }
        
        contenido.add(panelImagen, BorderLayout.NORTH);
        contenido.add(panelInfo, BorderLayout.CENTER);
        
        panelIzquierdo.add(contenido, BorderLayout.CENTER);
    }
    
    private void calcularRenta(ActionEvent e) {
        try {
            String diasTexto = txtDias.getText().trim();
            
            if (diasTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese el número de días", 
                    "Campo vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int dias = Integer.parseInt(diasTexto);
            
            if (dias <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de días debe ser mayor a 0", 
                    "Valor inválido", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (curItem.getCantCopias() <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "No hay copias disponibles de este item", 
                    "Sin stock", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Calcular el total
            double total = curItem.pagoRenta(dias);
            lblTotalPagar.setText("Total a pagar: " + String.format("%.2f", total) + " Lps");
            
            // Mostrar factura
            mostrarFactura(dias, total);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un número válido de días", 
                "Formato inválido", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarFactura(int dias, double total) {
        StringBuilder factura = new StringBuilder();
        factura.append("═══════════════════════════════════════\n");
        factura.append("              JAVABUSTER RENTAL\n");
        factura.append("═══════════════════════════════════════\n\n");
        
        // Fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        factura.append("Fecha: ").append(sdf.format(Calendar.getInstance().getTime())).append("\n\n");
      
        factura.append("ITEM RENTADO:\n");
        factura.append("Código: ").append(curItem.getCodigo()).append("\n");
        factura.append("Nombre: ").append(curItem.getNombre()).append("\n");
        factura.append("Tipo: ").append(curItem instanceof Movie ? "Movie" : "Game").append("\n");
     
        if (curItem instanceof Movie) {
            Movie movie = (Movie) curItem;
            factura.append("Estado: ").append(movie.getEstado()).append("\n");
        } else if (curItem instanceof Game) {
            factura.append("Tipo: PS3 Game").append("\n");
        }
        
        factura.append("\n");
        
     
        factura.append("DETALLES DE RENTA:\n");
        factura.append("Precio base por día: ").append(String.format("%.2f", curItem.getBaseRenta())).append(" Lps\n");
        factura.append("Días de renta: ").append(dias).append("\n");
        
      
        if (curItem instanceof Movie) {
            Movie movie = (Movie) curItem;
            double baseTotal = curItem.getBaseRenta() * dias;
            factura.append("Subtotal base: ").append(String.format("%.2f", baseTotal)).append(" Lps\n");
            
            if (movie.getEstado().equals("ESTRENO") && dias > 2) {
                double recargo = (dias - 2) * 50;
                factura.append("Recargo por estreno (días extras): ").append(String.format("%.2f", recargo)).append(" Lps\n");
            } else if (!movie.getEstado().equals("ESTRENO") && dias > 5) {
                double recargo = (dias - 5) * 30;
                factura.append("Recargo por días extras: ").append(String.format("%.2f", recargo)).append(" Lps\n");
            }
        } else {
            factura.append("Subtotal: ").append(String.format("%.2f", total)).append(" Lps\n");
        }
        
        factura.append("\n");
        factura.append("───────────────────────────────────────\n");
        factura.append("TOTAL A PAGAR: ").append(String.format("%.2f", total)).append(" Lps\n");
        factura.append("───────────────────────────────────────\n\n");
        factura.append("¡Gracias por elegir JAVABUSTER!\n");
        factura.append("═══════════════════════════════════════");
        
        JOptionPane.showMessageDialog(this, 
            factura.toString(), 
            "Factura de Renta", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
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
    
    private void setupWindow() {
        setTitle("JAVABUSTER - RENTA DE ITEM");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        
        getContentPane().setBackground(new Color(25, 25, 35));
    }
}