/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2progra2_samuel_leonardo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hnleo
 */
public class ImprimirTodo extends JFrame {

    private JPanel panelContenido;
    private JScrollPane scrollPane;
    private JButton btnVolver;
    private JLabel lblTitulo;

    private ArrayList<RentItem> items = MainPantalla.getInventario();

    public ImprimirTodo() {
        pantalla();
        ventana();
        mostrarItems();
    }

    private void pantalla() {
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(25, 25, 35));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("JAVABUSTER - ITEMS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        panelTitulo.add(lblTitulo);

        panelContenido = new JPanel();
        panelContenido.setLayout(new GridBagLayout());
        panelContenido.setBackground(new Color(35, 35, 45));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(35, 35, 45));

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(25, 25, 35));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        btnVolver = crearBoton("Volver", new Color(70, 130, 180));
        btnVolver.addActionListener(e -> {
            dispose();
        });

        panelInferior.add(btnVolver);

        add(panelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void mostrarItems() {
        panelContenido.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int columna = 0;
        int fila = 0;
        int itemsPorFila = 2;

        for (RentItem item : items) {
            JPanel tarjeta = crearTarjeta(item);

            gbc.gridx = columna;
            gbc.gridy = fila;
            panelContenido.add(tarjeta, gbc);

            columna++;
            if (columna >= itemsPorFila) {
                columna = 0;
                fila++;
            }
        }

        gbc.gridx = 0;
        gbc.gridy = fila + 1;
        gbc.gridwidth = itemsPorFila;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelContenido.add(Box.createVerticalGlue(), gbc);

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private JPanel crearTarjeta(RentItem item) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(280, 350));
        tarjeta.setBackground(new Color(45, 45, 55));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 80), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBackground(new Color(60, 60, 70));
        panelImagen.setPreferredSize(new Dimension(250, 180));
        panelImagen.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 90), 1));

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);

        if (item.getImagen() != null) {
            lblImagen.setIcon(item.getImagen());

        } else {
            String tipoIcono = item instanceof Movie ? "Movie" : "Game";
            lblImagen.setText(tipoIcono);
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 48));
        }
        lblImagen.setForeground(new Color(200, 200, 200));

        panelImagen.add(lblImagen, BorderLayout.CENTER);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(45, 45, 55));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel lblNombre = new JLabel(item.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTipo = new JLabel(item instanceof Movie ? "Movie" : "Game");
        lblTipo.setFont(new Font("Arial", Font.ITALIC, 12));
        lblTipo.setForeground(new Color(180, 180, 180));
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblEstado = null;
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            lblEstado = new JLabel("Estado: " + movie.getEstado());
            lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
            lblEstado.setForeground(Color.WHITE);
            lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        JLabel lblPrecio = new JLabel("Precio: " + String.format("%.2f", item.getBaseRenta()) + " Lps.");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(new Color(144, 238, 144));
        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblTipo);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));

        if (lblEstado != null) {
            panelInfo.add(lblEstado);
            panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        panelInfo.add(lblPrecio);

        tarjeta.add(panelImagen, BorderLayout.NORTH);
        tarjeta.add(panelInfo, BorderLayout.CENTER);

        return tarjeta;
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

    private void ventana() {
        setTitle("JAVABUSTER - LISTA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 700);
        setLocationRelativeTo(null);
        setResizable(true);
    }

}
