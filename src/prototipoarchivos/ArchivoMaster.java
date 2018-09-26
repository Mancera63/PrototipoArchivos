/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Angel Balderas
 */
public class ArchivoMaster {

    Terminal terminal = new Terminal();
    Traslado traslado = new Traslado();

    /*char llave, llaveDes, sig;
    String ciudad, ciudadDes;
    int ady, enl;
    double distancia;*/
    int posicion;

    Arbol arbol = new Arbol();

    int n, m;
    StringBuffer buffer = null;
    RandomAccessFile archM;
    RandomAccessFile archAristas;
    RandomAccessFile archIndice;
    Scanner entrada = new Scanner(System.in);

    void escribirArchivoMaestro() throws IOException {
        archM = new RandomAccessFile("terminales", "rw");
        archAristas = new RandomAccessFile("aristas", "rw");
        archIndice = new RandomAccessFile("indice", "rw");

        System.out.println("Insersión de terminales");
        do {
            //System.out.println(archM.length());
            archM.seek(archM.length());
            archIndice.seek(archIndice.length());
            System.out.println("Ingresa la llave de la ciudad");
            terminal.llave = entrada.next().charAt(0);
            archM.writeChar(terminal.llave);//ingreso llave en maestro
            archIndice.writeChar(terminal.llave);//llena indice con la llave
            archIndice.writeLong(((archM.getFilePointer() - 2) / 40) + 1);//pone la posicion de maestro en indice
            System.out.println("Nombre de la ciudad");
            terminal.ciudad = entrada.next();
            buffer = new StringBuffer(terminal.ciudad);
            buffer.setLength(20);
            archM.writeChars(buffer.toString());//ingreso destino en maestro
            //terminal.sig = ' ';
            //archM.writeChar(terminal.sig);//ingreso sig en maestro (vacío hasta tener el siguiente nodo
            System.out.println(archM.getFilePointer());
            if (archM.getFilePointer() == 42) {
                terminal.ady = 1;
            } else {
                terminal.ady = 0;
            }
            System.out.println(terminal.ady);
            archM.writeLong(terminal.ady);
            System.out.println("¿OTRA CIUDAD? :SI=1,NO=0 ");
            n = entrada.nextInt();
        } while (n == 1);

        /*do {
            archM.seek(archM.length());
            archIndice.seek(archIndice.length());
            
            System.out.println("Ingresa la llave de la ciudad origen");
            llave = entrada.next().charAt(0);
            archM.writeChar(llave);//ingreso llave en maestro
            archIndice.writeChar(llave);//llena indice con la llave
            archIndice.writeLong(((archM.getFilePointer() - 2) / 40) + 1);//pone la posicion de maestro en indice checalo xD 
            posicion=(int)((archM.getFilePointer() - 2) / 40) + 1;
            System.out.println("posicionArbol: "+posicion);
            arbol.insertar(llave,posicion);
            System.out.println("Arbol");
            arbol.imprimirEntreConNivel();
            
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
                archAristas.seek(archAristas.length());

                archAristas.writeChar(llave);//ingreso origen en aristas

                System.out.println("Llave de la ciudad destino");
                llaveDes = entrada.next().charAt(0);
                //archM.writeChar(llave);//ingreso destino en maestro
                archAristas.writeChar(llaveDes);//ingreso destino en aristas
                archIndice.writeChar(llaveDes);
                archIndice.writeLong(((archM.getFilePointer() - 2) / 40) + 1);
                posicion=(int)((archM.getFilePointer() - 2) / 40) + 1;
                //System.out.println("posicionArbol: "+posicion);
                //arbol.insertar(llaveDes,posicion);
                //System.out.println("Arbol");
                //arbol.imprimirEntreConNivel();

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
                    //nose si es asi checalo creo no xD
                    //if (z == 0) {
                    //archAristas.writeLong((archAristas.getFilePointer()-12)+1);

                    //archM.writeLong(archAristas.getFilePointer());
                    // z = 1;
                    //}else{
                    archAristas.writeLong(((archAristas.getFilePointer() - 12) / 20) + 2);
                    //}
                } else {
                    archAristas.writeLong(0);
                    //archM.writeLong(0);
                }
                archM.writeChar(llaveDes);
                archM.writeChars(buffer.toString());

            } while (y == 1);
            //}
            System.out.println("¿OTRA CIUDAD? :SI=1,NO=0 ");
            n = entrada.nextInt();
        } while (n == 1);*/
        archM.close();
        archAristas.close();
        archIndice.close();
    }

    void escribirArchivoAristas() throws IOException {
        archM = new RandomAccessFile("terminales", "rw");
        archAristas = new RandomAccessFile("aristas", "rw");
        long pos;

        System.out.println("Insersión de translados");

        do {
            archM.seek(archM.length());
            archAristas.seek(archAristas.length());

            System.out.println("Ingresar llave de ciudad origen");
            traslado.origen = entrada.next().charAt(0);

            do {
                archM.writeChar(traslado.origen);
                System.out.println("Ingresar llave de ciudad destino");
                traslado.destino = entrada.next().charAt(0);
                archM.writeChar(traslado.destino);
                System.out.println("Ingresar la distancia");
                traslado.distancia = entrada.nextDouble();
                archM.writeDouble(traslado.distancia);
                System.out.println("¿La misma ciudad tendrá otro destino? :SI=1,NO=0 ");
                m = entrada.nextInt();
                if (m == 1) {
                    traslado.enl = ((archAristas.getFilePointer() - 12) / 20) + 2;
                } else {
                    traslado.enl = 0;
                    pos = leer_aleatorio_maestro(traslado.origen);
                    archM.seek(pos + 42);
                    archM.writeLong((archAristas.length() / 14) + 1);
                }
                archAristas.writeLong(traslado.enl);
            } while (m == 1);
            System.out.println("¿OTRA CIUDAD CON TRASLADO? :SI=1,NO=0 ");
            n = entrada.nextInt();

        } while (n == 1);
    }

    void leer_secuencual_maestro() throws IOException {
        long ap_actual, ap_final;

        RandomAccessFile leer_archi = new RandomAccessFile("terminales", "r");
        System.out.println("Llave\tNombre\t\tAdy\t");
        System.out.println("-------------------------------------");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {

            //System.out.println(leer_archi.getFilePointer()); 0
            System.out.print(leer_archi.readChar() + "\t");
            //System.out.println(leer_archi.getFilePointer()); 2
            char nombre[] = new char[20], temp;
            for (int c = 0; c < nombre.length; c++) {
                temp = leer_archi.readChar();
                nombre[c] = temp;
            }
            new String(nombre).replace('\0', ' ');
            System.out.print(nombre);
            //System.out.print("\t");
            //System.out.println(leer_archi.getFilePointer()); -> 40
            //System.out.print(leer_archi.readChar() + "\t");
            //System.out.println(leer_archi.getFilePointer()); -> 2
            System.out.println(leer_archi.readLong());
            //System.out.println(leer_archi.getFilePointer()); -> 8
            System.out.println("");
        }
        leer_archi.close();
    }

    void leer_secuencual_aristas() throws IOException {
        long ap_actual, ap_final;
        //int i=1;

        RandomAccessFile leer_archi = new RandomAccessFile("aristas", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {

            System.out.println(leer_archi.readChar());
            System.out.println(leer_archi.readChar());
            System.out.println(leer_archi.readDouble());
            //System.out.println(leer_archi.getFilePointer());
            System.out.println(leer_archi.readLong());
            //System.out.println(leer_archi.getFilePointer());
            System.out.println("");

        }
        leer_archi.close();
    }

    void leer_secuencual_indice() throws IOException {
        long ap_actual, ap_final;
        //int i=1;
        char llave;
        long pos;

        RandomAccessFile leer_archi = new RandomAccessFile("indice", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
<<<<<<< HEAD
            llave = leer_archi.readChar();
            //System.out.println(llave);
            pos = leer_archi.readLong();
            //System.out.println(pos);
            //System.out.println("");
            arbol.insertar(llave, (int) pos);
=======

            llave=leer_archi.readChar();
            posicion=(int)leer_archi.readLong();
            arbol.insertar(llave,posicion);
            System.out.println(llave);
            System.out.println(posicion);
            System.out.println("");
>>>>>>> d4af9d59e2b65b3aee5bb95043304faed700746e

        }
        leer_archi.close();
        System.out.println("Arbol");
        arbol.imprimirEntreConNivel();
    }

    void leerAleatorioBusqueda() throws IOException {
        int n, n2, pos;
        long dl;
        long lreg, desplazamiento;
        char llave, llaveM;
        long posicionI;

        long lregM, dlM, desplazamientoM, ady;

        char origen, destino, destin;

        long lregA, desplazamientoA, ENL;

        RandomAccessFile archindice = new RandomAccessFile("indice", "r");
        RandomAccessFile archM = new RandomAccessFile("terminales", "r");
        RandomAccessFile archAristas = new RandomAccessFile("aristas", "r");

        Scanner entrada = new Scanner(System.in);
        archindice.readChar();
        archindice.readLong();
        lreg = archindice.getFilePointer();
        do {
            System.out.println("Ingresa el origen");
            origen = entrada.next().charAt(0);
            System.out.println("Ingresa el destino");
            destino = entrada.next().charAt(0);

            pos = arbol.buscar(origen);
            if (pos != 0) {
                System.out.println("La posicion es: " + pos);
            }

            dl = pos;
            desplazamiento = (dl - 1) * lreg;
            archindice.seek(desplazamiento);
            llave = archindice.readChar();
            System.out.println("\nLos datos del registro son: ");
            System.err.println("i: " + llave);
            posicionI = archindice.readLong();
            System.out.println(posicionI);

            ////////////mide la longitud de registro maestro//////////////////////////
            archM.readChar();
            char nombre[] = new char[20], temp;
            for (int c = 0; c < nombre.length; c++) {
                temp = archM.readChar();
                nombre[c] = temp;
            }
            new String(nombre).replace('\0', ' ');
            System.out.println(nombre);
            archM.readChar();
            archM.readLong();
<<<<<<< HEAD
            lregM = archM.getFilePointer();

            ////////////////////////busqueda en maestro//////////////////////////////////////////////////
            dlM = posicionI;
            desplazamientoM = (dlM - 1) * lregM;
            archM.seek(desplazamientoM);
            llaveM = archM.readChar();
            System.out.println("\nLos datos del registro Maestro son: ");
            System.err.println("M: " + llaveM);
            archM.readChar();
            System.out.println(nombre);
            System.err.println("M: " + archM.readChar()); //aqui muestra el SIG
            ady = archM.readLong();
            System.err.println("M: " + ady); // aqui muestra el ADY

            ///////////////////mide la longitud de archi aristas///////////////////////////////
            archAristas.readChar();
            archAristas.readChar();
            archAristas.readDouble();
            archAristas.readLong();
            lregA = archM.getFilePointer();

            int a = 1;
            do {
                desplazamientoA = (ady - 1) * lregM;
                System.out.println("\nLos datos del registro Aristas son: ");
                System.out.println("A: " + archAristas.readChar());
                destin = archAristas.readChar();
                System.out.println("A: " + destin);
                System.out.println("A: " + archAristas.readDouble());
                ENL = archAristas.readLong();
                System.out.println("A: " + ENL);
                if (destin == destino) {
                    a = 0;
                } else {
                    ady = ENL;
                }
            } while (a == 1);

=======
            lregM=archM.getFilePointer();
            
                ////////////////////////busqueda en maestro//////////////////////////////////////////////////
                dlM=posicionI;
                desplazamientoM=(dlM-1)*lregM;
                archM.seek(desplazamientoM);
                llaveM=archM.readChar();
                System.out.println("\nLos datos del registro Maestro son: ");
                System.err.println("M: "+llaveM);
                archM.readChar();
                System.out.println(nombre);
                System.err.println("M: "+archM.readChar()); //aqui muestra el SIG
                ady=1;
                System.err.println("M: "+ady); // aqui muestra el ADY
            
                ///////////////////mide la longitud de archi aristas///////////////////////////////
                archAristas.readChar();
                archAristas.readChar();
                archAristas.readDouble();
                archAristas.readLong();
                lregA=archM.getFilePointer();
                
                int a=1;
                do{
                    desplazamientoA=(ady-1)*lregM;
                    System.out.println("ady: "+ady);
                    archAristas.seek(desplazamientoA);
                    System.out.println("\nLos datos del registro Aristas son: ");
                    System.out.println("A: "+archAristas.readChar());
                    destin=archAristas.readChar();
                    System.out.println("A: "+destin);
                    System.out.println("A: "+archAristas.readDouble());
                    ENL=archAristas.readLong();
                    System.out.println("A: "+ENL);
                    if(destin==destino){
                        a=0;
                    }else{
                        ady=ENL;
                    }
                }while(a==1);
                
            
>>>>>>> d4af9d59e2b65b3aee5bb95043304faed700746e
            System.out.println("¿OTRA BUSQUEDA? :SI=1,NO=0 ");
            n = entrada.nextInt();
        } while (n == 1);
        archindice.close();
        archAristas.close();
        archM.close();
    }

    long leer_aleatorio_maestro(int llave) throws IOException {
        int n, dl;
        long lreg, pos;

        //archAristas = new RandomAccessFile("terminales", "r");
        archAristas.readChar();
        char nomb[] = new char[20];
        for (int c = 0; c < nomb.length; c++) {
            nomb[c] = archAristas.readChar();
        }
        archAristas.readChar();
        archAristas.readLong();
        lreg = archAristas.getFilePointer();
        leer_secuencual_indice();
        dl = arbol.buscar(llave);
        pos = (dl - 1) * lreg;
        //archAristas.close();
        return pos;
    }
}
