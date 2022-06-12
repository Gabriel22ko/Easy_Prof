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
public class Professor {
    private int cdProfessor;
    private String nome;
    private String login;
    private String email;
    private String cpf;
    private String telefone;
    private int idade;
    private Uf localidade;
    private String senhaParaAcesso;
    private DisciplinaAtuacao disciplina;
    private NivelFormacao formacao;
    private double custoHora;

    public Professor(int cdProfessor, String nome, String login, String email, String cpf, String telefone, int idade, Uf localidade, String senhaParaAcesso, DisciplinaAtuacao disciplina, NivelFormacao formacao, double custoHora) {
        this.cdProfessor = cdProfessor;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idade = idade;
        this.localidade = localidade;
        this.senhaParaAcesso = senhaParaAcesso;
        this.disciplina = disciplina;
        this.formacao = formacao;
        this.custoHora = custoHora;
    }
    
    public Professor(){
        
    }

    public int getCdProfessor() {
        return cdProfessor;
    }

    public void setCdProfessor(int cdProfessor) {
        this.cdProfessor = cdProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Uf getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Uf localidade) {
        this.localidade = localidade;
    }
    
    public int getCdLocalidade() {
        return localidade.getCdUf();
    }

    public String getSenhaParaAcesso() {
        return senhaParaAcesso;
    }

    public void setSenhaParaAcesso(String senhaParaAcesso) {
        this.senhaParaAcesso = senhaParaAcesso;
    }

    public DisciplinaAtuacao getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaAtuacao disciplina) {
        this.disciplina = disciplina;
    }
    
     public int getCdDisciplina() {
        return disciplina.getCdDisciplina();
    }

    public NivelFormacao getFormacao() {
        return formacao;
    }

    public void setFormacao(NivelFormacao formacao) {
        this.formacao = formacao;
    }
    
    public int getCdFormacao() {
        return formacao.getCdNivelFormacao();
    }

    public double getCustoHora() {
        return custoHora;
    }

    public void setCustoHora(double custoHora) {
        this.custoHora = custoHora;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
    
}
