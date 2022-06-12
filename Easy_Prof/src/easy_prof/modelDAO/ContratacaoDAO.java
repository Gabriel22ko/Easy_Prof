/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.Contratacao;
import easy_prof.modelDomain.Professor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class ContratacaoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Contratacao contratacao){
        String sql = "INSERT INTO contratacao(dataDeAula, dataContratacao, alunoContratante, professorContratado, valorContratacao, duracaoAula) "
                + "VALUES(?,?,?,?,?,?)";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(contratacao.getDataDeAula()));
            stmt.setDate(2, Date.valueOf(contratacao.getDataContratacao()));
            stmt.setInt(3, contratacao.getCdAlunoContratante());
            stmt.setInt(4, contratacao.getCdProfessorContratado());
            stmt.setDouble(5, contratacao.getValor());
            stmt.setInt(6, contratacao.getDuracaoAula());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContratacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes() {
        String sql = "SELECT COUNT(cdContratacao), EXTRACT(year from dataContratacao) AS ano, EXTRACT(month from dataContratacao) "
                + "AS mes FROM contratacao GROUP BY ano, mes ORDER BY ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano")))
                {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(ContratacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Contratacao regraDeNegocio(Professor professor, LocalDate date){
        String sql = "SELECT * FROM contratacao WHERE professorContratado = ? and dataDeAula = ?";
        Contratacao contratacaoResultante = new Contratacao();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getCdProfessor());
            stmt.setDate(2, Date.valueOf(date));
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                contratacaoResultante.setCdContratacao(resultado.getInt("cdContratacao"));
                contratacaoResultante.setCdAlunoContratante(resultado.getInt("alunoContratante"));
                contratacaoResultante.setCdProfessorContratado(resultado.getInt("professorContratado"));
                contratacaoResultante.setDataDeAula(resultado.getDate("dataDeAula").toLocalDate());
                contratacaoResultante.setDataContratacao(resultado.getDate("dataContratacao").toLocalDate());
                contratacaoResultante.setDuracaoAula(resultado.getInt("duracaoAula"));
                contratacaoResultante.setValor(resultado.getDouble("valorContratacao"));
            }
            return contratacaoResultante;
        }catch(SQLException ex) {
            Logger.getLogger(ContratacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return contratacaoResultante;
    }
    
    public boolean remover(int cdContratacao){
        String sql = "DELETE FROM contratacao WHERE cdContratacao = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cdContratacao);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContratacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
