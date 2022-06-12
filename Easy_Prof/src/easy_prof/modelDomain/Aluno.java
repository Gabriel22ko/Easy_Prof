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
public class Aluno {
    private int cdAluno;
    private String nome;
    private String login;
    private String email;
    private String cpf;
    private String telefone;
    private int idade;
    private Uf localidade;
    private String senhaParaAcesso;

    public Aluno(int cdAluno, String nome, String login, String email, String cpf, String telefone, int idade, Uf localidade, String senhaParaAcesso) {
        this.cdAluno = cdAluno;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idade = idade;
        this.localidade = localidade;
        this.senhaParaAcesso = senhaParaAcesso;
    }
    
    
    public Aluno(){
        
    }

    public int getCdAluno() {
        return cdAluno;
    }

    public void setCdAluno(int cdAluno) {
        this.cdAluno = cdAluno;
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

    public String getSenhaParaAcesso() {
        return senhaParaAcesso;
    }

    public void setSenhaParaAcesso(String senhaParaAcesso) {
        this.senhaParaAcesso = senhaParaAcesso;
    }
    
    public int getCdLocalidade(){
        return this.localidade.getCdUf();
    }
}
