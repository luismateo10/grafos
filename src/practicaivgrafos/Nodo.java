/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaivgrafos;

/**
 *
 * @author alejandroescobar
 */
public class Nodo {
    private int vertice;
    private Nodo liga;

    public Nodo(int v) {
        vertice = v;
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
}
