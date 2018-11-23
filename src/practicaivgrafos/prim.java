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

/**
 *
 * @author Luis Mateo
 */
public class prim {
//Realizado por CH ST.

    int n;
    int[] escogido;
    int[][] costos;

    public Conjunto prim() {
        Lado l;
        int i, v1, v2, k;
        k = 0;
        ;;
        Conjunto st = new Conjunto();
        escogido = new int[n + 1];
        for (i = 1; i <= n; i++) {
            escogido[i] = 0;
        }
        escogido[1] = 1;
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
        for (i = 1; i <= n; i++) {
            if (escogido[i] == 1) {
                for (j = 1; j <= n; j++) {
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
