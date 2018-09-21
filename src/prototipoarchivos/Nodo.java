/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

/**
 *
 * @author Angel Balderas
 */
public class Nodo {
    int llave;
    int dl;
    Nodo izq;
    Nodo der;

    public Nodo(int llave, int dl, Nodo izq, Nodo der) {
        this.llave = llave;
        this.dl = dl;
        this.izq = izq;
        this.der = der;
    }
    public Nodo(int ele,int dir){
        this(ele,dir,null,null);
    }
}
