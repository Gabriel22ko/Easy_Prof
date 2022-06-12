package easy_prof.modelDAO;

import easy_prof.modelDomain.DisciplinaAtuacao;
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
public class DisciplinaAtuacaoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<DisciplinaAtuacao> listar() {
        String sql = "SELECT * FROM disciplina_de_atuacao";
        ArrayList<DisciplinaAtuacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                DisciplinaAtuacao disciplina = new DisciplinaAtuacao();
                disciplina.setCdDisciplina(resultado.getInt("cdDisciplina"));
                disciplina.setNomeDisciplina(resultado.getString("disciplinas"));
                retorno.add(disciplina);
            }
        }catch(SQLException ex){
            Logger.getLogger(DisciplinaAtuacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
           
