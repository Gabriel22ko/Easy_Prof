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
public class NivelFormacao {
    private int cdNivelFormacao;
    private String nomeNivelFormacao;

    public NivelFormacao(int cdNivelFormacao, String nomeNivelFormacao) {
        this.cdNivelFormacao = cdNivelFormacao;
        this.nomeNivelFormacao = nomeNivelFormacao;
    }
    
    public NivelFormacao(){
        
    }

    public int getCdNivelFormacao() {
        return cdNivelFormacao;
    }

    public void setCdNivelFormacao(int cdNivelFormacao) {
        this.cdNivelFormacao = cdNivelFormacao;
    }

    public String getNomeNivelFormacao() {
        return nomeNivelFormacao;
    }

    public void setNomeNivelFormacao(String nomeNivelFormacao) {
        this.nomeNivelFormacao = nomeNivelFormacao;
    }
    
    @Override
    public String toString() {
        return this.getNomeNivelFormacao();
    }
}
