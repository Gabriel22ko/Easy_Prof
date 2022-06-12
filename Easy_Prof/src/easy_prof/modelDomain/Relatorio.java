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
public class Relatorio {
    String nomeAluno;
    int quantContratacoes;
    double valorTotalContratacoes;

    public Relatorio(String nomeAluno, int quantContratacoes, double valorTotalContratacoes) {
        this.nomeAluno = nomeAluno;
        this.quantContratacoes = quantContratacoes;
        this.valorTotalContratacoes = valorTotalContratacoes;
    }
    
    public Relatorio(){
        
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getQuantContratacoes() {
        return quantContratacoes;
    }

    public void setQuantContratacoes(int quantContratacoes) {
        this.quantContratacoes = quantContratacoes;
    }

    public double getValorTotalContratacoes() {
        return valorTotalContratacoes;
    }

    public void setValorTotalContratacoes(double valorTotalContratacoes) {
        this.valorTotalContratacoes = valorTotalContratacoes;
    }
}
