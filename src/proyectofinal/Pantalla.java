/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        JRadioButton b = new JRadioButton("Buscaminas");
        b.setBounds(50,210,200,40);
        panel.add(b);
        JRadioButton b2 = new JRadioButton("Ahorcado");
        b2.setBounds(50,260,200,40);
        panel.add(b2);
        JRadioButton b3 = new JRadioButton("Trivia");
        b3.setBounds(50,310,200,40);
        panel.add(b3);
        
    }
    
}