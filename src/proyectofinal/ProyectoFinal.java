/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class ProyectoFinal {
static Scanner lea = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Jugar Jugador = new Jugar("Invitado",300);
        System.out.println("-----GameStation-----");
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = lea.nextLine();
        Jugador.setNombre(nombre);
        int o = 0;
        do{    
        System.out.println("Bienvenido(a) "+nombre+" que desea jugar?");
        System.out.println("1) Buscaminas");
        System.out.println("2) Ahorcado");
        System.out.println("3) Trivia");
        o = lea.nextInt();
        lea.nextLine();
        }while(o>3);
        switch(o){
            case 1:
                char[][] matrizOculta = crearMatrizOculta();
                mostrarMatrizOculta(matrizOculta); 
                int[][] matrizSolucion = generarMatriz();
                int vidas = 3;
                break;
            case 2:
                System.out.println("Cargando....");
               break;
            case 3:
                System.out.println("Cargando....");
                break;
        }
    }
     public static void mostrarMatrizOculta(char[][] matriz) {
        System.out.println("Matriz oculta: ");
        for (char[] fila : matriz) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
    public static char[][] crearMatrizOculta() {
        char[][] matrizOculta = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizOculta[i][j] = 'X';
            }
        }
        return matrizOculta;
    }
      public static int[][] generarMatriz() {
        int[][] matriz = new int[4][4];
        Random random = new Random();

        for (int num = 1; num <= 7; num++) {
            for (int par = 0; par < 2; par++) {
                int fila, col;
                do {
                    fila = random.nextInt(1);
                    col = random.nextInt(1);
                } while (matriz[fila][col] != 0);

                matriz[fila][col] = num;
            }
        }
        return matriz;
    }
}
