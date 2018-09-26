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
public class Arbol {
    Nodo raiz=null;
    
    void insertar(char llave,int posicion){
        Nodo nuevo;
        Nodo ant=null;
        Nodo rec;
        
        if(raiz==null){
            raiz=new Nodo(llave,posicion);
            System.out.println("valor insertado "+llave);
        }else{
            nuevo=new Nodo(llave,posicion);
            System.out.println("valor insertado "+llave);
            rec=raiz;
            while(rec!=null){
                ant=rec;
                if(rec.llave>nuevo.llave)
                    rec=rec.izq;
                else
                    rec=rec.der;
            }
            if(ant.llave>nuevo.llave)
                ant.izq=nuevo;
            else
                ant.der=nuevo;
        }
    }//FIN INSERTAR
    
    int buscar(int llave){
        int p=0;
        Nodo rec;
        Nodo ant;
        if(raiz==null)
            System.out.println("Arbol vacio");
        else{
            rec=raiz;
            ant=raiz;
            while((ant.llave!=llave) && (rec!=null)){
                ant=rec;
                if(rec.llave>llave)
                    rec=rec.izq;
                else
                    rec=rec.der;
            }
            if(ant.llave==llave){
                System.out.println("El valor esta en el arbol: "+ant.llave);
                p=ant.dl;
            }else{
                System.out.println("El valor "+llave+"no se encuentra en el arbol");
            }
        }
        return(p);
    }//FIN BUSCAR
    
    
    public void imprimirEntreConNivel(){
        imprimirEntreConNivel(raiz,1);
        System.out.println();
    }
    private void imprimirEntreConNivel(Nodo tmp, int nivel){
        if(tmp !=null){
            imprimirEntreConNivel(tmp.izq,nivel+1);
            System.out.println(tmp.llave + "("+nivel+")  ");
            imprimirEntreConNivel(tmp.der,nivel+1);
        }
    }
}
