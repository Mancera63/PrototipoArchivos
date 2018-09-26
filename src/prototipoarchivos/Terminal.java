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
public class Terminal {
    char llave;
    String ciudad;
    long ady;

    public Terminal(char llave, String ciudad, long ady) {
        this.llave = llave;
        this.ciudad = ciudad;
        this.ady = ady;
    }

    public Terminal() {
    }
    

    public char getLlave() {
        return llave;
    }

    public void setLlave(char llave) {
        this.llave = llave;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public long getAdy() {
        return ady;
    }

    public void setAdy(long ady) {
        this.ady = ady;
    }
}
