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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @version 1.0
 * @since 19-11-2018
 * @author Luis Mateo
 */
public class PracticaIVGrafos {

    /**
     * Metodo principal
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        File archivo = new File("archivo.txt");
        Conjunto<String> conjVert = new Conjunto<String>();

        conjVert = contarVertices(archivo);

        //Despues de leer el archivo la primera vez queda en cada vertice queda:
        //"19,1:esquina 1"
        for (int i = 0; i < conjVert.size(); i++) {
            conjVert.set(i + ":" + conjVert.get(i), i);
        }
        //Despues del for "0:19,1:esquina 1"
        int[][] matrizAdya;

        //crea la matriz a partir de un archivo y un conunto de vertices
        matrizAdya = constMatrizAdya(archivo, conjVert);

        //imprime la matriz
        imprimirMatriz(matrizAdya);

        //imprime el conjunto
        for (int i = 0; i < conjVert.size(); i++) {
            System.out.println(conjVert.get(i).toString());
        }
        matrizAdyacencia matriz = new matrizAdyacencia();
        matriz.setMatrizAdyac(matrizAdya);
        matriz.setN(conjVert.size());
        matriz.dfs();
        //solicita el punto de partida

        matriz.matrizAdyacToLLdaAdyac(matrizAdya);
        Conjunto<String> conjAux = new Conjunto<String>();
        conjAux = matriz.prim();    
        System.out.println("stop");
        System.out.println("Ingrese la coordenada de partida, por ejemplo: 19,1");
        int inicio = sc.nextInt();
    }

//------------------Metodos de lectura del archivo------------------------------
    /**
     *
     * Lee el archivo linea a linea y almacena las coordenandas de cada linea en
     * un conjunto (sin repeticion)
     *
     * La estructura para cada linea del archivo es:
     *
     * Coordenada 1: “nombre sitio 1”: distancia : Coordenada 2: “nombre sitio
     * 2” Ejemplo: 19,1:”esquina 1”:25:19,2:”esquina 2”
     *
     * retorna un conjunto de vertices cada vertice es un string de la forma
     * "coordenada:nombre coordenada"
     *
     * @param file Archivo
     * @return conjVert conjunto de vertices
     * @throws Exception
     */
    private static Conjunto<String> contarVertices(File file) {
        Conjunto<String> conjVert = new Conjunto<String>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(file, "r");
            String linea;

            while ((linea = archivo.readLine()) != null) {
                String[] auxString = linea.split(":");
                String caracter = auxString[1].charAt(0) + "";
                String[] auxString2 = auxString[1].split(caracter);
                conjVert.add(auxString[0] + ":" + auxString2[1]);
                auxString2 = auxString[4].split(caracter);
                conjVert.add(auxString[3] + ":" + auxString2[1]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en archivo");
        }
        return conjVert;
    }

    /**
     * Crea un matriz nxn segun el tamaño del conjunto de vertices y la llena de
     * ceros
     *
     * Lee el archivo linea a linea, consulta el indice de cada par de vertices
     * y asigna un 1 para cada relacion dentro de la matrizde adyacencia nxn
     *
     * La estructura para cada linea del archivo es:
     *
     * Coordenada 1: “nombre sitio 1”: distancia : Coordenada 2: “nombre sitio
     * 2” Ejemplo: 19,1:”esquina 1”:25:19,2:”esquina 2”
     *
     * @param file archivo
     * @param conjVert conjunto de vertices
     * @return
     */
    private static int[][] constMatrizAdya(File file, Conjunto<String> conjVert) {

        //crea una matriz nxn llena de ceros
        int[][] matrizAdya = new int[conjVert.size()][conjVert.size()];
        for (int i = 0; i < conjVert.size(); i++) {
            for (int j = 0; j < conjVert.size(); j++) {
                matrizAdya[i][j] = 0;
            }
        }

        try {
            RandomAccessFile archivo = new RandomAccessFile(file, "r");
            String lineaArchivo;

            while ((lineaArchivo = archivo.readLine()) != null) {
                String[] auxString = lineaArchivo.split(":");

                int indice1 = conjVert.obtenerIndice(auxString[0]);
                int indice2 = conjVert.obtenerIndice(auxString[3]);
                if (indice1 != (-1) && indice2 != (-1)) {
                    matrizAdya[indice1][indice2] = 1;
                    matrizAdya[indice2][indice1] = 1;
                } else {
                    System.out.println("Problema en linea: " + lineaArchivo);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en archivo");
        }
        return matrizAdya;
    }

//------------------Metodo para imprimir matriz de adyacencia-------------------
    /**
     * Imprime matriz nxn
     *
     * @param matriz nxn
     */
    private static void imprimirMatriz(int[][] matriz) {
        for (int[] matriz1 : matriz) {
            System.out.print("|");
            for (int y = 0; y < matriz1.length; y++) {
                System.out.print(matriz1[y]);
                if (y != matriz1.length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("|");
        }
    }
}
