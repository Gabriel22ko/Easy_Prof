/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.Aluno;
import easy_prof.modelDomain.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class AlunoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Aluno aluno){
        String sql = "INSERT INTO alunos(nome, login, email, cpf, telefone, idade, localidade, senha) "
                + "VALUES(?,?,?,?,?,?,?,?)";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getTelefone());
            stmt.setInt(6, aluno.getIdade());
            stmt.setInt(7, aluno.getCdLocalidade());
            stmt.setString(8, aluno.getSenhaParaAcesso());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Aluno verificar(String login, String senha) throws SQLException{
        String sql = "SELECT * FROM alunos INNER JOIN uf ON alunos.localidade = uf.cdUf "
                + "WHERE login = ? and senha = ? ";
        Aluno alunoResultado = new Aluno();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Uf ufAux = new Uf();
                alunoResultado.setCdAluno(resultado.getInt("cdAluno"));
                alunoResultado.setLogin(resultado.getString("login"));
                alunoResultado.setSenhaParaAcesso(resultado.getString("senha"));
                alunoResultado.setNome(resultado.getString("nome"));
                alunoResultado.setEmail(resultado.getString("email"));
                alunoResultado.setCpf(resultado.getString("cpf"));
                alunoResultado.setTelefone(resultado.getString("telefone"));
                alunoResultado.setIdade(resultado.getInt("idade"));
                ufAux.setCdUf(resultado.getInt("cdUf"));
                ufAux.setEstadoUf(resultado.getString("estados"));
                alunoResultado.setLocalidade(ufAux);
            }
        }catch(SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alunoResultado;
    }
    
    public boolean atualizar(Aluno aluno){
        String sql = "UPDATE alunos SET nome = ?, login = ?, email = ?, cpf = ?, telefone = ?, idade = ?,"
                + " localidade = ?, senha = ? WHERE cdAluno = ?";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getTelefone());
            stmt.setInt(6, aluno.getIdade());
            stmt.setInt(7, aluno.getCdLocalidade());
            stmt.setString(8, aluno.getSenhaParaAcesso());
            stmt.setInt(9, aluno.getCdAluno());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Aluno aluno) throws SQLException{
        String sql = "DELETE FROM alunos WHERE cdAluno = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getCdAluno());
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
