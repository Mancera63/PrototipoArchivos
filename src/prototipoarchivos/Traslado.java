/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoarchivos;

/**
 *
 * @author EliasMancera
 */
public class Traslado {
    char origen, destino;
    double distancia;
    long enl;

    public Traslado() {
    }

    public Traslado(char origen, char destino, double distancia, long enl) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.enl = enl;
    }

    public char getOrigen() {
        return origen;
    }

    public void setOrigen(char origen) {
        this.origen = origen;
    }

    public char getDestino() {
        return destino;
    }

    public void setDestino(char destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public long getEnl() {
        return enl;
    }

    public void setEnl(long enl) {
        this.enl = enl;
    }
    
}
