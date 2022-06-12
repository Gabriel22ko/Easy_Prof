/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDomain;

import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class DadosContratacao {
    int cdContratacao;
    String nomeAluno;
    String nomeProfessor;
    LocalDate dataDeAula;
    int duracaoAula;

    public DadosContratacao(int cdContratacao, String nomeAluno, String nomeProfessor, LocalDate dataDeAula, int duracaoAula) {
        this.cdContratacao = cdContratacao;
        this.nomeAluno = nomeAluno;
        this.nomeProfessor = nomeProfessor;
        this.dataDeAula = dataDeAula;
        this.duracaoAula = duracaoAula;
    }
    
    public DadosContratacao(){
        
    }

    public int getCdContratacao() {
        return cdContratacao;
    }

    public void setCdContratacao(int cdContratacao) {
        this.cdContratacao = cdContratacao;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public LocalDate getDataDeAula() {
        return dataDeAula;
    }

    public void setDataDeAula(LocalDate dataDeAula) {
        this.dataDeAula = dataDeAula;
    }

    public int getDuracaoAula() {
        return duracaoAula;
    }

    public void setDuracaoAula(int duracaoAula) {
        this.duracaoAula = duracaoAula;
    }
    
    
}
