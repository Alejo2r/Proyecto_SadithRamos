/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
   public JLabel mostrarv;
   public JButton verificar;
   public JButton salir;
   public String[] listapal = {"defensor","proyecto","programacion","estanteria","volar","estereo","agua","anona","aeropuerto","medicina","ventilador","atardecer"};
   public String palabras;
   public char[] palabradesco;
   public int vidas = 6;
   public ArrayList<Character> errores = new ArrayList();
    public Ahorcado(){
        iniciarpanel();
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
        mostrarp.setBounds(50, 50, 300, 50);
        mostrarp.setFont(new Font("Monospaced", Font.BOLD, 20));
        panel.add(mostrarp);
        mostrarv = new JLabel("Vidas restantes: " + vidas);
        mostrarv.setBounds(50, 100, 300, 30);
        panel.add(mostrarv);
        txt = new JTextField(1);
        txt.setBounds(150, 150, 50, 30);
        panel.add(txt);
        verificar = new JButton ("Verificar");
        verificar.setBounds(200,150, 100, 20);
        panel.add(verificar);
        salir = new JButton("Salir");
        salir.setBounds(200,300,100,20);
        panel.add(salir);
         ActionListener cerrar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           dispose();
        }
    };
        ActionListener accion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           verificar();
        }
    };
        salir.addActionListener(cerrar);
        verificar.addActionListener(accion);
    }
      private void iniciarpanel (){
         panel = new JPanel();
         panel.setLayout(null);
        this.getContentPane().add(panel); //agrego panel      
    }
     private void verificar (){
         String letraingresada= txt.getText().toLowerCase();
         if(letraingresada.length() != 1){
             JOptionPane.showMessageDialog(this, "Solo ingrese una letra por favor");
             return; //
         }
         char letra = letraingresada.charAt(0);
         boolean correcto = false;
         for(int i  = 0; i<palabras.length();i++){
             if(palabras.charAt(i) == letra){
                 palabradesco[i] = letra;
                 correcto = true;
             }
         }
         if(!correcto){
             if (!errores.contains(letra)) {
                errores.add(letra);
                vidas--;
            } else {
                JOptionPane.showMessageDialog(this, "Ya usaste esa letra");
            }
             
         }
         txt.setText("");
         estadovidas();
         if(vidas == 0){
             JOptionPane.showMessageDialog(this, "Te han ahorcado! La palabra era: "+palabras);
             jugardenuevo();
         }else if(new String(palabradesco).equals(palabras)){
             JOptionPane.showMessageDialog(this, "Que pro, Ganaste");
             jugardenuevo();
         }
     }
     private void estadovidas (){
         mostrarp.setText(new String(palabradesco));
         mostrarv.setText("Vidas restantes: "+ vidas);
     }
     private void jugardenuevo(){
         Random xd = new Random();
         palabras = listapal[xd.nextInt(listapal.length)];
        palabradesco = new char[palabras.length()];
        for(int i = 0; i<palabras.length();i++){
            palabradesco[i] = '_';
        }
        vidas = 6;
        errores.clear();
        estadovidas();
     }
}
