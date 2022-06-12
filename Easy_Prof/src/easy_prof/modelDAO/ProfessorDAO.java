/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.DisciplinaAtuacao;
import easy_prof.modelDomain.NivelFormacao;
import easy_prof.modelDomain.Professor;
import easy_prof.modelDomain.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class ProfessorDAO {
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Professor professor){
        String sql = "INSERT INTO professores(nome, login, email, cpf, telefone, idade, localidade, "
                + "disciplina, formacao, custo_hora, senha) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getLogin());
            stmt.setString(3, professor.getEmail());
            stmt.setString(4, professor.getCpf());
            stmt.setString(5, professor.getTelefone());
            stmt.setInt(6, professor.getIdade());
            stmt.setInt(7, professor.getCdLocalidade());
            stmt.setInt(8, professor.getCdDisciplina());
            stmt.setInt(9, professor.getCdFormacao());
            stmt.setDouble(10, professor.getCustoHora());
            stmt.setString(11, professor.getSenhaParaAcesso());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Professor verificar(String login, String senha) throws SQLException{
        String sql = "SELECT * FROM professores INNER JOIN uf ON professores.localidade = uf.cdUf\n" +
        "INNER JOIN disciplina_de_atuacao ON professores.disciplina = disciplina_de_atuacao.cdDisciplina\n" +
        "INNER JOIN nivel_formacao_academica ON professores.formacao = nivel_formacao_academica.cdNivel_Formacao"
        + " WHERE login = ? and senha = ? ";
        Professor professorResultado = new Professor();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Uf ufAux = new Uf();
                DisciplinaAtuacao disciplinaAux = new DisciplinaAtuacao();
                NivelFormacao formacaoAux = new NivelFormacao();
                professorResultado.setCdProfessor(resultado.getInt("cdProfessor"));
                professorResultado.setLogin(resultado.getString("login"));
                professorResultado.setSenhaParaAcesso(resultado.getString("senha"));
                professorResultado.setNome(resultado.getString("nome"));
                professorResultado.setEmail(resultado.getString("email"));
                professorResultado.setCpf(resultado.getString("cpf"));
                professorResultado.setTelefone(resultado.getString("telefone"));
                professorResultado.setIdade(resultado.getInt("idade"));
                professorResultado.setCustoHora(resultado.getDouble("custo_hora"));
                ufAux.setCdUf(resultado.getInt("cdUf"));
                ufAux.setEstadoUf(resultado.getString("estados"));
                formacaoAux.setCdNivelFormacao(resultado.getInt("cdNivel_Formacao"));
                formacaoAux.setNomeNivelFormacao(resultado.getString("nivel_formacao"));
                disciplinaAux.setCdDisciplina(resultado.getInt("cdDisciplina"));
                disciplinaAux.setNomeDisciplina(resultado.getString("disciplinas"));
                professorResultado.setLocalidade(ufAux);
                professorResultado.setFormacao(formacaoAux);
                professorResultado.setDisciplina(disciplinaAux);
            }
        }catch(SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return professorResultado;
    }
    
    public boolean atualizar(Professor professor){
        String sql = "UPDATE professores SET nome = ?, login = ?, email = ?, cpf = ?, telefone = ?, idade = ?,"
                + " localidade = ?, senha = ?, disciplina = ?, formacao = ?, custo_hora = ? WHERE cdProfessor = ?";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getLogin());
            stmt.setString(3, professor.getEmail());
            stmt.setString(4, professor.getCpf());
            stmt.setString(5, professor.getTelefone());
            stmt.setInt(6, professor.getIdade());
            stmt.setInt(7, professor.getCdLocalidade());
            stmt.setString(8, professor.getSenhaParaAcesso());
            stmt.setInt(9, professor.getCdDisciplina());
            stmt.setInt(10, professor.getCdFormacao());
            stmt.setDouble(11, professor.getCustoHora());
            stmt.setInt(12, professor.getCdProfessor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Professor> buscarProfessores() throws SQLException{
        String sql = "SELECT * FROM professores INNER JOIN uf ON professores.localidade = uf.cdUf\n" +
        "INNER JOIN disciplina_de_atuacao ON professores.disciplina = disciplina_de_atuacao.cdDisciplina\n" +
        "INNER JOIN nivel_formacao_academica ON professores.formacao = nivel_formacao_academica.cdNivel_Formacao";
        ArrayList<Professor> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Professor professorAux = new Professor();
                Uf ufAux = new Uf();
                DisciplinaAtuacao disciplinaAux = new DisciplinaAtuacao();
                NivelFormacao formacaoAux = new NivelFormacao();
                professorAux.setCdProfessor(resultado.getInt("cdProfessor"));
                professorAux.setNome(resultado.getString("nome"));
                professorAux.setEmail(resultado.getString("email"));
                professorAux.setIdade(resultado.getInt("idade"));
                professorAux.setCustoHora(resultado.getDouble("custo_hora"));
                professorAux.setTelefone(resultado.getString("telefone"));
                ufAux.setCdUf(resultado.getInt("cdUf"));
                ufAux.setEstadoUf(resultado.getString("estados"));
                disciplinaAux.setCdDisciplina(resultado.getInt("cdDisciplina"));
                disciplinaAux.setNomeDisciplina(resultado.getString("disciplinas"));
                formacaoAux.setCdNivelFormacao(resultado.getInt("cdNivel_Formacao"));
                formacaoAux.setNomeNivelFormacao(resultado.getString("nivel_formacao"));
                professorAux.setLocalidade(ufAux);
                professorAux.setDisciplina(disciplinaAux);
                professorAux.setFormacao(formacaoAux);
                retorno.add(professorAux);
            }
        }catch(SQLException ex){
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean remover(Professor professor) throws SQLException{
        String sql = "DELETE FROM professores WHERE cdProfessor = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getCdProfessor());
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Professor> buscarFiltradoUf(int cdLocalidade) throws SQLException{
        String sql = "SELECT * FROM professores INNER JOIN uf ON professores.localidade = uf.cdUf\n" +
        "INNER JOIN disciplina_de_atuacao ON professores.disciplina = disciplina_de_atuacao.cdDisciplina\n" +
        "INNER JOIN nivel_formacao_academica ON professores.formacao = nivel_formacao_academica.cdNivel_Formacao\n"
        + "WHERE localidade = ? ";
        ArrayList<Professor> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cdLocalidade);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Professor professorAux = new Professor();
                Uf ufAux = new Uf();
                DisciplinaAtuacao disciplinaAux = new DisciplinaAtuacao();
                NivelFormacao formacaoAux = new NivelFormacao();
                professorAux.setCdProfessor(resultado.getInt("cdProfessor"));
                professorAux.setNome(resultado.getString("nome"));
                professorAux.setEmail(resultado.getString("email"));
                professorAux.setIdade(resultado.getInt("idade"));
                professorAux.setCustoHora(resultado.getDouble("custo_hora"));
                professorAux.setTelefone(resultado.getString("telefone"));
                ufAux.setCdUf(resultado.getInt("cdUf"));
                ufAux.setEstadoUf(resultado.getString("estados"));
                disciplinaAux.setCdDisciplina(resultado.getInt("cdDisciplina"));
                disciplinaAux.setNomeDisciplina(resultado.getString("disciplinas"));
                formacaoAux.setCdNivelFormacao(resultado.getInt("cdNivel_Formacao"));
                formacaoAux.setNomeNivelFormacao(resultado.getString("nivel_formacao"));
                professorAux.setLocalidade(ufAux);
                professorAux.setDisciplina(disciplinaAux);
                professorAux.setFormacao(formacaoAux);
                retorno.add(professorAux);
            }
        }catch(SQLException ex){
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
    