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
public class Lado implements Comparable{
    private Comparable vi;
    private Comparable vj;
    private int costo;

    public Lado(Comparable vi, Comparable vj) {
        this.vi = vi;
        this.vj = vj;
    }

    public Lado(Comparable vi, Comparable vj, int costo) {
        this.vi = vi;
        this.vj = vj;
        this.costo = costo;
    }


    public Comparable getVi() {
        return vi;
    }

    public void setVi(Comparable vi) {
        this.vi = vi;
    }

    public Comparable getVj() {
        return vj;
    }

    public void setVj(Comparable vj) {
        this.vj = vj;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contieneVx(Comparable v1) {
        return vi.equals(v1) || vj.equals(v1);
    }
    
    
    
}
