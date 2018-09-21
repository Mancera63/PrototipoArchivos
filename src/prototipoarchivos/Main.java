/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

import java.io.IOException;

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
        am.escribirArchivoMaestro();
        am.leer_secuencual_maestro();
        //am.leer_secuencual_aristas();
        //prueba de fuego///xD ABC
        
    }
    
}
