/*
 * Copyright (C) 2018 Luis Mateo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package practicaivgrafos;

import java.util.Iterator;

/**
 *
 * @author Luis Mateo
 */
public class matrizAdyacencia {

    private int n;
    private int[][] matrizAdyac;
    private Nodo[] g;

    int[] escogido;
    int[][] costos;

    public Nodo[] getG() {
        return g;
    }

    public void setG(Nodo[] g) {
        this.g = g;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[][] getMatrizAdyac() {
        return matrizAdyac;
    }

    public void setMatrizAdyac(int[][] matrizAdyac) {
        this.matrizAdyac = matrizAdyac;
    }

    private String dfsMatrizAdyacencia(int v, int[] visitado,String a) {
        int i;
        System.out.println(v);
        visitado[v] = 1;
        for (i = 0; i < n; i++) {
           if (matrizAdyac[v][i] == 1) {
                if (visitado[i] == 0) {
                    dfsMatrizAdyacencia(i, visitado,a);
                }
            }

        }
        return a;
    }

    public void dfs() {
        
        String a="";
        int v = 0;
        int[] visitados = new int[n];
        dfsMatrizAdyacencia(v, visitados,a);
    }

    public void matrizAdyacToLLdaAdyac(int adya[][]) {
        int i, j;
        Nodo x;
        Nodo[] g = new Nodo[n];
        this.g = g;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (adya[i][j] == 1) {
                    x = new Nodo(j);
                    x.setLiga(g[i]);
                    g[i] = x;
                }
            }
        }
    }

    public Conjunto prim() {

        Conjunto st = new Conjunto();
        Conjunto lados = crearLados();
        Conjunto conectados = new Conjunto();
        Conjunto noConectados = crearNoConectados();
        int a = (int) noConectados.get();
        Nodo v = new Nodo(a);
        conectados.add(v.getVertice());
        noConectados.remove(v.getVertice());
        Lado lmc;
        while (!noConectados.empty()) {
            lmc = seleccioneLado(conectados, lados);
            st.add(lmc);
            conectados.add(lmc.getVi());
            conectados.add(lmc.getVj());
            noConectados.remove(lmc.getVi());
            noConectados.remove(lmc.getVj());
        }
        return st;
    }

    private Conjunto crearLados() {
        Conjunto lados = new Conjunto();
        for (Nodo n : g) {
            Nodo p = n.getLiga();
            while (p != null) {
                Lado l = new Lado(n.getVertice(), p.getVertice());
                p = p.getLiga();
                lados.add(l);
            }
        }
        return lados;
    }

    private Conjunto crearNoConectados() {
        Conjunto noConectados = new Conjunto();
        for (Nodo n : g) {
            noConectados.add(n.getVertice());
        }
        return noConectados;
    }

    private Lado seleccioneLado(Conjunto conectados, Conjunto lados) {
        Lado lMenor = null;
        for (Iterator iterator = conectados.iterator(); iterator.hasNext();) {
            Comparable v1 = (Comparable) iterator.next();
            for (Iterator iterator1 = lados.iterator(); iterator1.hasNext();) {
                Lado lado = (Lado) iterator1.next();
                if (lado.contieneVx(v1)) {
                    if (lMenor != null) {
                        if (lado.getCosto() < lMenor.getCosto()) {
                            lMenor = lado;
                        }
                    } else {
                        lMenor = lado;
                    }
                }

            }
        }
        return lMenor;
    }

    public Conjunto prim2() {
        costos = matrizAdyac;
        Lado l;
        int i, v1, v2, k;
        k = 0;
        Conjunto st = new Conjunto();
        escogido = new int[n + 1];
        for (i = 0; i <= n; i++) {
            escogido[i] = 0;
        }
        escogido[0] = 1;
        while (k < (n - 1)) {
            l = escogeLado();
            v1 = (int) l.getVi();
            v2 = (int) l.getVj();
            escogido[v1] = 1;
            escogido[v2] = 1;
            k = k + 1;
            st.add(l);
        }
        return st;
    }

    public Lado escogeLado() {
        int menor, i, j;
        Lado l = new Lado(0, 0, 0);
        menor = 1000;
        for (i = 0; i < n; i++) {
            if (escogido[i] == 1) {
                for (j = 0; j < n; j++) {
                    if (escogido[j] == 0) {
                        if (costos[i][j] < menor) {
                            menor = costos[i][j];
                            l = new Lado(i, j, menor);
                        }
                    }
                }
            }
        }
        return l;
    }

}
