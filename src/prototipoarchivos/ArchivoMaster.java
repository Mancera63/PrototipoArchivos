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
    char llave,sig;
    String ciudad;
    int ady,enl;
    double distancia;
    
    void escribirArchivoMaestro() throws IOException{
        int n;
        StringBuffer buffer=null;
        System.out.println("Archivo Maestro");
        RandomAccessFile archM=new RandomAccessFile("terminales","rw");
        RandomAccessFile archAristas=new RandomAccessFile("aristas","rw");
        RandomAccessFile archIndice=new RandomAccessFile("indice","rw");
        Scanner entrada=new Scanner(System.in);
        
        do{
            archM.seek(archM.length());
            archIndice.seek(archIndice.length());
            
            System.out.println("llave ciudad");
            llave=entrada.next().charAt(0);
            archM.writeChar(llave);
            archIndice.writeChar(llave);//llena indice con la llave
            archIndice.writeLong();//pone la posicion de maestro en indice checalo xD 
            
            System.out.println("Nombre de la ciudad");
            ciudad=entrada.next();
            buffer=new StringBuffer(ciudad);
            buffer.setLength(20);
            archM.writeChars(buffer.toString());
            
            System.out.println("¿DESTINO? :SI=1,NO=0 ");
            int x=entrada.nextInt();
            if(x==1){
                do{
                    archM.seek(archM.length());
                    archAristas.seek(archM.length());
                    
                    archAristas.writeChar(llave);//ingreso origen en aristas
                    
                    System.out.println("llave ciudad");
                    llave=entrada.next().charAt(0);
                    archM.writeChar(llave);
                    archAristas.writeChar(llave);//ingreso destino en aristas
                    
                    System.out.println("Nombre de la ciudad");
                    ciudad=entrada.next();
                    buffer=new StringBuffer(ciudad);
                    buffer.setLength(20);
                    archM.writeChars(buffer.toString());
                    
                    System.out.println("ingrese distancia");
                    distancia=entrada.nextDouble();
                    archAristas.writeDouble(distancia);//ingresando distancia en aristas
                    
                    System.out.println("¿DESTINO? :SI=1,NO=0 ");
                    int y=entrada.nextInt();
                    //aqui llenas ENL checalo xD
                    if(y==1){
                        archAristas.writeLong(archAristas.length()+1);//nose si es asi checalo creo no xD
                    }else
                        archAristas.writeLong(0);
                    
                }while(x==1);
            }
            System.out.println("¿OTRA CIUDAD? :SI=1,NO=0 ");
            n=entrada.nextInt();
        }while(n==1);
        archM.close();
    }
}
