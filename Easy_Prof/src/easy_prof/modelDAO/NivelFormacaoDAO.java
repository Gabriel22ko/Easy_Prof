/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDAO;

import easy_prof.modelDomain.NivelFormacao;
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
public class NivelFormacaoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<NivelFormacao> listar() {
        String sql = "SELECT * FROM nivel_formacao_academica";
        ArrayList<NivelFormacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                NivelFormacao formacao = new NivelFormacao();
                formacao.setCdNivelFormacao(resultado.getInt("cdNivel_Formacao"));
                formacao.setNomeNivelFormacao(resultado.getString("nivel_formacao"));
                retorno.add(formacao);
            }
        }catch(SQLException ex){
            Logger.getLogger(NivelFormacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
