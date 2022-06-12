/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.DadosContratacao;
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
public class DadosContratacaoDAO {
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public ArrayList<DadosContratacao> retornarContratacoes(){
        String sql = "SELECT cdContratacao, alunos.nome alnome, professores.nome profnome, dataDeAula, duracaoAula FROM contratacao\n"
                + "INNER JOIN alunos ON contratacao.alunoContratante = alunos.cdAluno\n"
                + "INNER JOIN professores ON contratacao.professorContratado =  professores.cdProfessor";
        DadosContratacao contratacaoResultante = new DadosContratacao();
        ArrayList<DadosContratacao> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                contratacaoResultante.setCdContratacao(resultado.getInt("cdContratacao"));
                contratacaoResultante.setNomeAluno(resultado.getString("alnome"));
                contratacaoResultante.setNomeProfessor(resultado.getString("profnome"));
                contratacaoResultante.setDataDeAula(resultado.getDate("dataDeAula").toLocalDate());
                contratacaoResultante.setDuracaoAula(resultado.getInt("duracaoAula"));
                retorno.add(contratacaoResultante);
            }
            return retorno;
        }catch(SQLException ex) {
            Logger.getLogger(DadosContratacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return retorno;
    }
}
