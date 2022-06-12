/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.AlunoDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Aluno;
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
public class FXMLLoginAlunoController implements Initializable {

    @FXML
    public TextField textFieldVerificarLogin;
    @FXML
    public PasswordField passwordFieldVerificarSenha;
    @FXML
    public Button buttonProsseguir;
    
    private Aluno aluno = new Aluno();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
    }
    
    @FXML
    public void verificarLogin() throws SQLException, IOException{
        aluno = alunoDAO.verificar(textFieldVerificarLogin.getText(), passwordFieldVerificarSenha.getText());
        if(aluno.getLogin() == null || aluno.getSenhaParaAcesso() == null){
            emitirAlertaLoginNaoEncontrado();
        }else{
            alteracaoDadosAluno();
        }
    }
    
    public void emitirAlertaLoginNaoEncontrado(){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao encontrar!");
            alerta.setHeaderText("Aluno não encontrado!");
            alerta.setContentText("Tente novamente!");
            alerta.show();
    }
    
    public void alteracaoDadosAluno() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easy_prof/view/FXMLAlterarDadosAluno.fxml"));
        Parent root = loader.load();
        FXMLAlterarDadosAlunoController controlador = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Realize login!");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        controlador.carregarDados(aluno);
        controlador.buttonAlterar.setOnAction(e -> controlador.persistirDados(aluno));
        controlador.buttonRemover.setOnAction(e -> {
            try {
                controlador.remocaoAluno(aluno);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLLoginAlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
