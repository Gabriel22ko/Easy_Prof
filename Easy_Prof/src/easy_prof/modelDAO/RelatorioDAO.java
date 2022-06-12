/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.Relatorio;
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
public class RelatorioDAO {
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public ArrayList<Relatorio> buscarDadosRelatorio() throws SQLException{
        String sql = "SELECT alunos.nome, COUNT(alunos.cdAluno), SUM(contratacao.valorContratacao) AS total \n" +
        "FROM alunos INNER JOIN contratacao on contratacao.alunoContratante = alunos.cdAluno GROUP BY alunos.nome";
        ArrayList<Relatorio> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Relatorio relatorioAux = new Relatorio();
                relatorioAux.setNomeAluno(resultado.getString("nome"));
                relatorioAux.setQuantContratacoes(resultado.getInt("count"));
                relatorioAux.setValorTotalContratacoes(resultado.getDouble("total"));
                retorno.add(relatorioAux);
            }
            return retorno;
        }catch(SQLException ex) {
            Logger.getLogger(RelatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
