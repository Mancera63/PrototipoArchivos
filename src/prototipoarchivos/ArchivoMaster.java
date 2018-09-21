/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Angel Balderas
 */
public class ArchivoMaster {

    char llave, llaveDes, sig;
    String ciudad, ciudadDes;
    int ady, enl;
    double distancia;

    void escribirArchivoMaestro() throws IOException {
        int n;
        StringBuffer buffer = null;
        System.out.println("Archivo Maestro");
        RandomAccessFile archM = new RandomAccessFile("terminales", "rw");
        RandomAccessFile archAristas = new RandomAccessFile("aristas", "rw");
        RandomAccessFile archIndice = new RandomAccessFile("indice", "rw");
        Scanner entrada = new Scanner(System.in);

        do {
            //archM.seek(archM.length());
            archIndice.seek(archIndice.length());

            System.out.println("Ingresa la llave de la ciudad origen");
            llave = entrada.next().charAt(0);
            archM.writeChar(llave);//ingreso llave en maestro
            archIndice.writeChar(llave);//llena indice con la llave
            archIndice.writeLong(archM.getFilePointer());//pone la posicion de maestro en indice checalo xD 

            System.out.println("Nombre de la ciudad");
            ciudad = entrada.next();
            buffer = new StringBuffer(ciudad);
            buffer.setLength(20);
            archM.writeChars(buffer.toString());//ingreso destino en maestro
            int x = 0, y = 0, z = 0;
            //System.out.println("¿DESTINO? :SI=1,NO=0 ");
            //int x=entrada.nextInt();
            //if(x==1){
            do {
                archM.seek(archM.length());
                archAristas.seek(archM.length());

                archAristas.writeChar(llave);//ingreso origen en aristas

                System.out.println("Llave de la ciudad destino");
                llaveDes = entrada.next().charAt(0);
                //archM.writeChar(llave);//ingreso destino en maestro
                archAristas.writeChar(llaveDes);//ingreso destino en aristas

                System.out.println("Nombre de la ciudad");
                ciudadDes = entrada.next();
                buffer = new StringBuffer(ciudadDes);
                buffer.setLength(20);
                //archM.writeChars(buffer.toString());

                System.out.println("Ingrese la distancia");
                distancia = entrada.nextDouble();
                archAristas.writeDouble(distancia);//ingresando distancia en aristas

                System.out.println("¿La misma ciudad tendrá otro destino? :SI=1,NO=0 ");
                y = entrada.nextInt();
                //aqui llenas ENL checalo xD
                if (y == 1) {
                    archAristas.writeLong(archAristas.getFilePointer() + 1);//nose si es asi checalo creo no xD
                    //if (z == 0) {
                        //archM.writeChar(llaveDes);
                        archM.writeLong(archAristas.getFilePointer());
                        //z = 1;
                    //}
                } else {
                    archAristas.writeLong(0);
                }

            } while (y == 1);
            //}
            System.out.println("¿OTRA CIUDAD? :SI=1,NO=0 ");
            n = entrada.nextInt();
        } while (n == 1);
        archM.close();
    }
    
    void leer_secuencual_maestro() throws IOException {
        long ap_actual, ap_final;
        //int i=1;

        RandomAccessFile leer_archi = new RandomAccessFile("terminales", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            llave = leer_archi.readChar();
            System.out.println(llave);
            char nombre[] = new char[20], temp;
            for (int c = 0; c < nombre.length; c++) {
                temp = leer_archi.readChar();
                nombre[c] = temp;
            }
            new String(nombre).replace('\0', ' ');
            System.out.println(nombre);
            //System.out.println(leer_archi.readUTF());
            //clasifica = leer_archi.readDouble();
            System.out.println(leer_archi.readLong());
            //ar.insertar(llave, i);
            //i++;
        }
        leer_archi.close();
    }
    
    void leer_secuencual_aristas() throws IOException {
        long ap_actual, ap_final;
        int i=1;

        RandomAccessFile leer_archi = new RandomAccessFile("aristas", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            //llave = leer_archi.readChar();
            System.out.println(leer_archi.readChar());
            System.out.println(leer_archi.readChar());
            System.out.println(leer_archi.readDouble());
            System.out.println(leer_archi.readLong());
            /*char nombre[] = new char[20], temp;
            for (int c = 0; c < nombre.length; c++) {
                temp = leer_archi.readChar();
                nombre[c] = temp;
            }
            new String(nombre).replace('\0', ' ');
            System.out.println(nombre);
            //System.out.println(leer_archi.readUTF());
            //clasifica = leer_archi.readDouble();
            System.out.println(leer_archi.readLong());
            //ar.insertar(llave, i);
            i++;*/
        }
        leer_archi.close();
    }
}
