package easy_prof.modelDAO;

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
 * @author 20201si008
 */
public class UfDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Uf> listar() {
        String sql = "SELECT * FROM uf";
        ArrayList<Uf> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Uf ufAux = new Uf();
                ufAux.setCdUf(resultado.getInt("cdUf"));
                ufAux.setEstadoUf(resultado.getString("estados"));
                retorno.add(ufAux);
            }
        }catch(SQLException ex){
            Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
           
