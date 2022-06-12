/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.AlunoDAO;
import easy_prof.modelDAO.UfDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Aluno;
import easy_prof.modelDomain.Uf;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLCadastroAlunoController implements Initializable {

    @FXML
    private ComboBox<Uf> comboBoxLocalidade;
    @FXML
    private PasswordField passwordFieldSenhaParaAcesso;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldIdade;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private Button buttonCadastrar;
    
    private ObservableList<Uf> listaObservavelLocalidade;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UfDAO ufDAO = new UfDAO();
    private Aluno aluno = new Aluno();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ufDAO.setConnection(connection);
        alunoDAO.setConnection(connection);
        carregarComboBoxLocalidade();
    }    
    
    public void carregarComboBoxLocalidade(){
        listaObservavelLocalidade = FXCollections.observableArrayList(ufDAO.listar());
        comboBoxLocalidade.setItems(listaObservavelLocalidade);
    }
    
    public void receberDadosInterface(){
        if(verificarPreenchimentoDeDados() == true){
            aluno.setNome(textFieldNome.getText());
            aluno.setLogin(textFieldLogin.getText());
            aluno.setEmail(textFieldEmail.getText());
            aluno.setIdade(Integer.parseInt(textFieldIdade.getText()));
            aluno.setTelefone(textFieldTelefone.getText());
            aluno.setCpf(textFieldCpf.getText());
            aluno.setLocalidade(comboBoxLocalidade.getSelectionModel().getSelectedItem());
            aluno.setSenhaParaAcesso(passwordFieldSenhaParaAcesso.getText());
        }
    }
    
    @FXML
    public void persistirDados(){
        receberDadosInterface();
        if(alunoDAO.inserir(aluno)){
            exibirConfirmacao();
            esvaziarCampos();
        }else{
            emitirAlertaErro();
        }
    }
    
    public void esvaziarCampos(){
        textFieldNome.setText(null);
        textFieldLogin.setText(null);
        textFieldEmail.setText(null);
        textFieldIdade.setText(null);
        textFieldCpf.setText(null);
        textFieldTelefone.setText(null);
        comboBoxLocalidade.setValue(null);
        passwordFieldSenhaParaAcesso.setText(null);
    }
    
    public void exibirConfirmacao(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Sucesso!");
        alerta.setHeaderText("Cadastro realizado com sucesso!");
        alerta.setContentText("Os dados foram gravados!");
        alerta.show();
    }
    
    public void emitirAlertaErro(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Erro ao cadastrar!");
        alerta.setContentText("Tente Novamente!");
        alerta.show();
    }
    
    
    public boolean verificarPreenchimentoDeDados(){
        String errorMessage = "";
        
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
             errorMessage += "Nome inválido!\n";
        }
        if (textFieldLogin.getText() == null || textFieldLogin.getText().length() == 0) {
            errorMessage += "Login inválido!\n";;
        }
        if (textFieldEmail.getText() == null || textFieldEmail.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
        }
        if (textFieldIdade.getText() == null || textFieldIdade.getText().length() <= 0){
            errorMessage += "Idade inválida!\n";
        }
        if(textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0){
            errorMessage += "Telefone inválido!\n";
        }
        if(textFieldCpf.getText() == null || textFieldCpf.getText().length() == 0){
            errorMessage += "Cpf inválido!\n";
        }
        if(comboBoxLocalidade.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Localidade inválida!\n";
        }
        if(passwordFieldSenhaParaAcesso.getText() == null || passwordFieldSenhaParaAcesso.getText().length() == 0){
            errorMessage += "Senha inválida!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
