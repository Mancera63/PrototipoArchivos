/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Aux Sistemas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArchivoMaster am = new ArchivoMaster();
        Scanner sc = new Scanner(System.in);
        int menu;

        do {
            System.out.println("Menú de opciones");
            System.out.println("1.- Ingresar Terminal");
            System.out.println("2.- Ingresar Translado");
            System.out.println("3.- Listar Terminales");
            System.out.println("4.- Listar Translados");
            System.out.println("5.- Buscar Translado");
            System.out.println("6.- Salir");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    am.escribirArchivoMaestro();
                    break;
                case 2:
                    am.escribirArchivoAristas();
                    break;
                case 3:
                    System.out.println("\nSecuencial maestro\n------------------");
                    am.leer_secuencual_maestro();
                    break;
                case 4:
                    System.out.println("\nSecuencial aristas\n------------------");
                    am.leer_secuencual_aristas();
                    break;
                case 5:
                    am.leerAleatorioBusqueda();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (menu != 6);
        System.out.println("\nSecuencial Indice\n------------------");
        //am.leer_secuencual_indice();

        //prueba de fuego///xD ABC
    }

}
