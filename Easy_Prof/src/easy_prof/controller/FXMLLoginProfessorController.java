/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.ProfessorDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Professor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLLoginProfessorController implements Initializable {

    @FXML
    private TextField textFieldVerificarLogin;
    @FXML
    private PasswordField passwordFieldVerificarSenha;
    @FXML
    public Button buttonProsseguir;
    
    private Professor professor = new Professor();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        professorDAO.setConnection(connection);
    }    

    @FXML
    public void verificarLogin() throws SQLException, IOException {
         professor = professorDAO.verificar(textFieldVerificarLogin.getText(), passwordFieldVerificarSenha.getText());
        if(professor.getLogin() == null || professor.getSenhaParaAcesso() == null){
            emitirAlertaLoginNaoEncontrado();
        }else{
            alteracaoDadosProfessor();
        }
    }
    
    public void emitirAlertaLoginNaoEncontrado(){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao encontrar!");
            alerta.setHeaderText("Professor não encontrado!");
            alerta.setContentText("Tente novamente!");
            alerta.show();
    }
    
    public void alteracaoDadosProfessor() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easy_prof/view/FXMLAlterarDadosProfessor.fxml"));
        Parent root = loader.load();
        FXMLAlterarDadosProfessorController controlador = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Realize login!");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        controlador.carregarDados(professor);
        controlador.buttonAlterar.setOnAction(e -> controlador.persistirDados(professor));
        controlador.buttonRemover.setOnAction(e -> {
            try {
                controlador.remocaoProfessor(professor);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLLoginProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
