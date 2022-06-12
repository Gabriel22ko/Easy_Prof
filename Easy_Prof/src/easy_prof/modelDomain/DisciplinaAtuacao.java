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
public class DisciplinaAtuacao {
    private int cdDisciplina;
    private String nomeDisciplina;

    public DisciplinaAtuacao(int cdDisciplina, String nomeDisciplina) {
        this.cdDisciplina = cdDisciplina;
        this.nomeDisciplina = nomeDisciplina;
    }
    
    public DisciplinaAtuacao(){
        
    }

    public int getCdDisciplina() {
        return cdDisciplina;
    }

    public void setCdDisciplina(int cdDisciplina) {
        this.cdDisciplina = cdDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    
    @Override
    public String toString() {
        return this.getNomeDisciplina();
    }
}
