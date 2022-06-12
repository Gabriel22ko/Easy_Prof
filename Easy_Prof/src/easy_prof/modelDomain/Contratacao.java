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
public class Contratacao {
    private int cdContratacao;
    private LocalDate dataDeAula;
    private LocalDate dataContratacao;
    private double valor;
    private int duracaoAula;
    private int cdAlunoContratante;
    private int cdProfessorContratado;

    public Contratacao(int cdContratacao, LocalDate dataDeAula, LocalDate dataContratacao, double valor, int duracaoAula, int cdAlunoContratante, int cdProfessorContratado) {
        this.cdContratacao = cdContratacao;
        this.dataDeAula = dataDeAula;
        this.dataContratacao = dataContratacao;
        this.valor = valor;
        this.duracaoAula = duracaoAula;
        this.cdAlunoContratante = cdAlunoContratante;
        this.cdProfessorContratado = cdProfessorContratado;
    }

     public Contratacao(){
         
     }

    public int getCdContratacao() {
        return cdContratacao;
    }

    public void setCdContratacao(int cdContratacao) {
        this.cdContratacao = cdContratacao;
    }

    public LocalDate getDataDeAula() {
        return dataDeAula;
    }

    public void setDataDeAula(LocalDate dataDeAula) {
        this.dataDeAula = dataDeAula;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoAula() {
        return duracaoAula;
    }

    public void setDuracaoAula(int duracaoAula) {
        this.duracaoAula = duracaoAula;
    }

    public int getCdAlunoContratante() {
        return cdAlunoContratante;
    }

    public void setCdAlunoContratante(int cdAlunoContratante) {
        this.cdAlunoContratante = cdAlunoContratante;
    }

    public int getCdProfessorContratado() {
        return cdProfessorContratado;
    }

    public void setCdProfessorContratado(int cdProfessorContratado) {
        this.cdProfessorContratado = cdProfessorContratado;
    }
}
