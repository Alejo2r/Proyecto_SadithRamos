/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Admin
 */
public class Pantalla extends JFrame{
    public JPanel panel;
    public JTextField txt;
    JRadioButton b;
    JRadioButton b2;
    JRadioButton b3;
    public Pantalla(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//posicion inicial de la ventana en el centro 
        setTitle("GameStation");
        iniciarpanel();
        iniciarlabel();
        botones();
        texto();
    }
    private void iniciarpanel (){
         panel = new JPanel();
         panel.setLayout(null);
        this.getContentPane().add(panel); //agrego panel
            
    }
    private void iniciarlabel(){
         JLabel t = new JLabel();
        t.setText("------GameStation------");
        t.setHorizontalAlignment(SwingConstants.CENTER);
        t.setBounds(10,10,500,40);
        t.setFont(new Font("copper black",0,40));
        JLabel p = new JLabel("Ingresa tu nombre", SwingConstants.LEFT);
        p.setBounds(50,60,150,40);
        panel.add(t);
        panel.add(p);
    }
    private void texto (){
        txt = new JTextField();
        txt.setBounds(50,100, 200, 20);
        panel.add(txt);
    }
    private void botones(){
    JButton boton1 = new JButton("Aceptar");
    boton1.setBounds(50,125, 100, 20);
    boton1.setEnabled(true);
    boton1.setForeground(Color.BLUE);
    panel.add(boton1);
    JLabel p = new JLabel();
    p.setBounds(50,175,200,40);
    panel.add(p);
    
    ActionListener accion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           p.setText("Que deseas jugar "+txt.getText()+"?");
           radiobotones();
        }
    };
    boton1.addActionListener(accion);
    
}
    private void radiobotones(){
        b = new JRadioButton("Buscaminas", false);
        b.setBounds(50,210,200,40);
        panel.add(b);
        b2 = new JRadioButton("Ahorcado", false);
        b2.setBounds(50,260,200,40);
        panel.add(b2);
        ButtonGroup rBotones = new ButtonGroup();
        rBotones.add(b);
        rBotones.add(b2);
    JButton boton1 = new JButton("Aceptar");
    boton1.setBounds(50,350, 100, 20);
    boton1.setEnabled(true);
    boton1.setForeground(Color.BLUE);
    panel.add(boton1);
    
    
    ActionListener accion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if(b.isSelected()){
            SecondFrame secondFrame = new SecondFrame();
            secondFrame.setVisible(true);
        } 
        }
    };
    boton1.addActionListener(accion);
    }
    class SecondFrame extends JFrame {
    private int fil = 5;
    private int col = 5;
    private int mina = 5;
    private int tamano = 45; // Tamaño de cada celda del juego
    private JButton[][] botones;
    private boolean[][] minas;
    private boolean[][] descubiertos;

    public SecondFrame() {
        setLocationRelativeTo(null);
        setTitle("Buscaminas");
        setSize(250,250);
        //creamos la matriz en base a botones 
        botones = new JButton[fil][col];
        minas = new boolean[fil][col];
        descubiertos = new boolean[fil][col];

        colocarMinasAleatorias();
        inicializarInterfaz();

        setVisible(true);
    }
    //mettodo para colocar 5 minas en la matriz (5)
    private void colocarMinasAleatorias() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < mina) {
            int fila = rand.nextInt(fil);
            int columna = rand.nextInt(col);
            if (!minas[fila][columna]) {
                minas[fila][columna] = true;
                minasColocadas++;
            }
        }
    }

    private void inicializarInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Establece el layout como null para disposición libre
        

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                JButton boton = new JButton();
                boton.setBounds(j * tamano, i * tamano, tamano, tamano);//establece los tamaños y la posicion de los botones
                final int fila = i;
                final int columna = j;
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        descubrirCelda(fila, columna);
                    }
                });
                panel.add(boton);
                botones[i][j] = boton;
            }
        }

        add(panel);
    }
    //determina si en la celda habia una mina o no
 private void descubrirCelda(int fila, int columna) {
    if (fila < 0 || fila >= fil|| columna < 0 || columna >= col || descubiertos[fila][columna]) {
        return; // Verifica los límites y si la celda ya fue descubierta
    }

    descubiertos[fila][columna] = true;

    if (minas[fila][columna]) {
        botones[fila][columna].setText("X");
        JOptionPane.showMessageDialog(this, "Sos un pete, perdiste (mis roomies pudieron)");
        mostrarMinas();
        bloquearBotones();
    } else {
        int minasCercanas = contarMinasCercanas(fila, columna);
        botones[fila][columna].setText(String.valueOf(minasCercanas));

        if (minasCercanas == 0) {
            // Descubrir celdas  válidas
            for (int i = Math.max(0, fila - 1); i <= Math.min(fila + 1, fil - 1); i++) {
                for (int j = Math.max(0, columna - 1); j <= Math.min(columna + 1, col - 1); j++) {
                    descubrirCelda(i, j);
                }
            }
        }

        if (haGanado()) {
            JOptionPane.showMessageDialog(this, "Ni vos sabes como ganaste xd");
            bloquearBotones();
        }
    }
}
   //ayuda al jugador notificando cuantas minas hay alrededor
 private int contarMinasCercanas(int fila, int columna) {
        int count = 0;
        for (int i = Math.max(0, fila - 1); i <= Math.min(fila + 1, fil - 1); i++) {//evita que se salga de los bounds de la matriz
            for (int j = Math.max(0, columna - 1); j <= Math.min(columna + 1, col - 1); j++) {
                if (minas[i][j]) {
                    count++;
                }
            }
        }
        return count;
    } 
     //comprueba si el jugador no toco ninguna mina y limpio el tablero
    private boolean haGanado() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                if (!minas[i][j] && !descubiertos[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    //muestra todas las minas al comerse una
    private void mostrarMinas() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                if (minas[i][j]) {
                    botones[i][j].setText("X");
                }
            }
        }
    }
    //evita que el jugador siga presionando botones al perder o ganar
    private void bloquearBotones() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

}
}