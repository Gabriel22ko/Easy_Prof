/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDomain;

/**
 *
 * @author gabri
 */
public class Uf {
    private int cdUf;
    private String estadoUf;

    public Uf(int cdUf, String estadoUf) {
        this.cdUf = cdUf;
        this.estadoUf = estadoUf;
    }

    public Uf() {
        
    }

    public int getCdUf() {
        return cdUf;
    }

    public void setCdUf(int cdUf) {
        this.cdUf = cdUf;
    }

    public String getEstadoUf() {
        return estadoUf;
    }

    public void setEstadoUf(String estadoUf) {
        this.estadoUf = estadoUf;
    }
    
    @Override
    public String toString() {
        return this.getEstadoUf();
    }
}
