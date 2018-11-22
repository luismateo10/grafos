/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaivgrafos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alejandroescobar
 * @param <T> Comparable
 */
public class Conjunto<T extends Comparable> {
/**
 * Lista de datos
 */
    List<T> datos = new ArrayList<>();

    public void add(T objeto) {
        boolean yaEsta = false;
        for (Comparable next : datos) {
            if (next.equals(objeto)) {
                yaEsta = true;
                break;
            }
        }
        if (!yaEsta) {
            datos.add(objeto);
        }
    }

    public void prueba() {
    }

    public T get() {
        if (!datos.isEmpty()) {
            if (datos.get(0) != null) {
                return datos.get(0);
            }
        }
        return null;
    }

    public void set(T t, int n) {
        if (!datos.isEmpty()) {
            if (datos.get(n) != null) {
                datos.set(n, t);
            }
        }
    }

    public void remove(T o) {
        for (Comparable next : datos) {
            if (next.equals(o)) {
                datos.remove(next);
                break;
            }
        }
    }

    public boolean empty() {
        return datos.isEmpty();
    }

    public Iterator iterator() {
        return datos.iterator();
    }

//-----------------------------Metodos añadidos-------------------------------->
    /**
     * Determina el tamaño o numero de datos del conjunto
     *
     * @return size - tamaño
     */
    public int size() {
        return datos.size();
    }

    /**
     * Obtiene el indice de un objeto en el conjunto si el objeto no existe
     * devuelve "-1"
     *
     * recuerde que el metodo trbaja para objetos de la forma
     * "indice:coordenada:nombre coordenada","0:19,1:esquina 1"
     *
     * el metodo compara la coordenada de entrada con la de cada objeto del
     * conjunto y retorna el indice
     *
     * @param coordenada por ejemplo "19,1"
     * @return indice
     */
    public int obtenerIndice(String coordenada) {
        for (T dato : datos) {
            String[] vert = dato.toString().split(":");
            if (vert[1].equals(coordenada)) {
                return Integer.parseInt(vert[0]);
            }
        }
        return -1;
    }

    /**
     * Retorna un elemento del conjunto a partir del indice ingresado
     * verificando que éste exista o no sea nulo
     *
     * @param n indice en conjunto
     * @return dato
     */
    public T get(int n) {
        if (!datos.isEmpty()) {
            if (datos.get(n) != null) {
                return datos.get(n);
            }
        }
        return null;
    }
}
