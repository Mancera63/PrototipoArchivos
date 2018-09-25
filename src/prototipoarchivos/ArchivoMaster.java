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
    int posicion;
    
    Arbol arbol=new Arbol();

    void escribirArchivoMaestro() throws IOException {
        int n;
        StringBuffer buffer = null;
        System.out.println("Archivo Maestro");
        RandomAccessFile archM = new RandomAccessFile("terminales", "rw");
        RandomAccessFile archAristas = new RandomAccessFile("aristas", "rw");
        RandomAccessFile archIndice = new RandomAccessFile("indice", "rw");
        Scanner entrada = new Scanner(System.in);

        do {
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
        } while (n == 1);
        archM.close();
        archAristas.close();
        archIndice.close();
    }

    void leer_secuencual_maestro() throws IOException {
        long ap_actual, ap_final;
        //int i=1;

        RandomAccessFile leer_archi = new RandomAccessFile("terminales", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            //llave = leer_archi.readChar();
            System.out.println(leer_archi.readChar());
            char nombre[] = new char[20], temp;
            for (int c = 0; c < nombre.length; c++) {
                temp = leer_archi.readChar();
                nombre[c] = temp;
            }
            new String(nombre).replace('\0', ' ');
            System.out.println(nombre);
            //System.out.println(leer_archi.readLong());
            System.out.println("");
            //System.out.println(leer_archi.readUTF());
            //clasifica = leer_archi.readDouble();
            //System.out.println(leer_archi.readLong());
            //ar.insertar(llave, i);
            //i++;
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

        RandomAccessFile leer_archi = new RandomAccessFile("indice", "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {

            llave=leer_archi.readChar();
            posicion=(int)leer_archi.readLong();
            arbol.insertar(llave,posicion);
            System.out.println(llave);
            System.out.println(posicion);
            System.out.println("");

        }
        leer_archi.close();
        System.out.println("Arbol");
        arbol.imprimirEntreConNivel();
    }
    
    void leerAleatorioBusqueda()throws IOException{
        int n,n2,pos;
        long dl;
        long lreg,desplazamiento;
        char llave,llaveM;
        long posicionI;
        
        long lregM,dlM,desplazamientoM,ady;
        
        char origen,destino,destin;
        
        long lregA,desplazamientoA,ENL;
        
        
        
        RandomAccessFile archindice=new RandomAccessFile("indice","r");
        RandomAccessFile archM=new RandomAccessFile("terminales","r");
        RandomAccessFile archAristas=new RandomAccessFile("aristas","r");
        
        Scanner entrada=new Scanner(System.in);
        archindice.readChar();
        archindice.readLong();
        lreg=archindice.getFilePointer();
        do{
            System.out.println("Ingresa el origen");
            origen=entrada.next().charAt(0);
            System.out.println("Ingresa el destino");
            destino=entrada.next().charAt(0);
            
            pos=arbol.buscar(origen);
            if(pos!=0)
                System.out.println("La posicion es: "+pos);
            
            dl=pos;
            desplazamiento=(dl-1)*lreg;
            archindice.seek(desplazamiento);
            llave=archindice.readChar();
            System.out.println("\nLos datos del registro son: ");
            System.err.println("i: "+llave);
            posicionI=archindice.readLong();
            System.out.println(posicionI);
            
            ////////////mide la longitud de registro maestro//////////////////////////
            archM.readChar();
            char nombre[]=new char[20],temp;
            for(int c=0;c<nombre.length;c++){
                temp=archM.readChar();
                nombre[c]=temp;
            }
            new String(nombre).replace('\0',' ');
            System.out.println(nombre);
            archM.readChar();
            archM.readLong();
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
                
            
            System.out.println("¿OTRA BUSQUEDA? :SI=1,NO=0 ");
            n=entrada.nextInt();
        }while(n==1);
        archindice.close();
        archAristas.close();
        archM.close();
    }
}
