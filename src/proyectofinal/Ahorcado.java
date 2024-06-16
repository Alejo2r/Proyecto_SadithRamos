/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Admin
 */
public class Ahorcado extends JFrame{
   public JPanel panel;
   public JTextField txt;
   public JLabel mostrarp;
   public JLabel mostrarv = new JLabel();
   public JButton verificar;
   public String[] listapal = {"defensor","proyecto","programacion","estanteria","volar","estereo","agua","anona","aeropuerto","medicina"};
   public String palabras;
   public char[] palabradesco;
   public int vidas = 6;
   public ArrayList<Character> errores = new ArrayList();
    public Ahorcado(){  
        Random xd = new Random();
        setSize(500,500);
        setLocationRelativeTo(null);//posicion inicial de la ventana en el centro 
        setTitle("Ahorcado");
        palabras = listapal[xd.nextInt(listapal.length)];
        palabradesco = new char[palabras.length()];
        for(int i = 0; i<palabras.length();i++){
            palabradesco[i] = '_';
        }
        mostrarp = new JLabel(new String(palabradesco), SwingConstants.CENTER);
        mostrarp.setBounds(50,50,300,50);
        mostrarp.setFont(new Font("Monospaced", Font.BOLD,20));
        add(mostrarp);
        
        
    }
 
}
