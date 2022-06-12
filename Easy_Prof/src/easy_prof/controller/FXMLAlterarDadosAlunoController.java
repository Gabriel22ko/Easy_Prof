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
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLAlterarDadosAlunoController implements Initializable {

    @FXML
    private ComboBox<Uf> comboBoxLocalidade;
    @FXML
    private PasswordField passwordFieldSenhaParaAcesso;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private TextField textFieldIdade;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldCpf;
    @FXML
    public Button buttonAlterar;
    
    private ObservableList<Uf> listaObservavelLocalidade;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final UfDAO ufDAO = new UfDAO();
    @FXML
    private ImageView imageViewAluno;
    @FXML
    private Text textCadastroAluno;
    @FXML
    public Button buttonRemover;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
        ufDAO.setConnection(connection);
    }

    public void persistirDados(Aluno aluno) {
        if(verificarPreenchimentoDeDados() == true){
        receberDadosInterface(aluno);
        if(alunoDAO.atualizar(aluno)){
            exibirConfirmacao();
            esvaziarCampos();
            }else{
                emitirAlertaErro();
            }
        }
    }
    
    public void remocaoAluno(Aluno aluno) throws SQLException{
        if(verificarPreenchimentoDeDados()){
            if(alunoDAO.remover(aluno)){
            exibirConfirmacao();
            esvaziarCampos();
            }else{
            exibirErro();
            }
        }
    }
    
    public void carregarDados(Aluno aluno){
        textFieldNome.setText(aluno.getNome());
        textFieldEmail.setText(aluno.getEmail());
        textFieldLogin.setText(aluno.getLogin());
        textFieldIdade.setText(String.valueOf(aluno.getIdade()));
        textFieldTelefone.setText(aluno.getTelefone());
        textFieldCpf.setText(aluno.getCpf());
        passwordFieldSenhaParaAcesso.setText(aluno.getSenhaParaAcesso());
        comboBoxLocalidade.setValue(aluno.getLocalidade());
        carregarComboBoxLocalidade();
    }
    
    public void carregarComboBoxLocalidade(){
        listaObservavelLocalidade = FXCollections.observableArrayList(ufDAO.listar());
        comboBoxLocalidade.setItems(listaObservavelLocalidade);
    }
    
    public void receberDadosInterface(Aluno aluno){
        aluno.setNome(textFieldNome.getText());
        aluno.setLogin(textFieldLogin.getText());
        aluno.setEmail(textFieldEmail.getText());
        aluno.setIdade(Integer.parseInt(textFieldIdade.getText()));
        aluno.setTelefone(textFieldTelefone.getText());
        aluno.setCpf(textFieldCpf.getText());
        aluno.setLocalidade(comboBoxLocalidade.getSelectionModel().getSelectedItem());
        aluno.setSenhaParaAcesso(passwordFieldSenhaParaAcesso.getText());
    }
    
    public void exibirConfirmacao(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Sucesso!");
        alerta.setHeaderText("Ralizado com sucesso!");
        alerta.setContentText("Os dados foram gravados!");
        alerta.show();
    }
    
    public void exibirErro(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Erro ao remover!");
        alerta.setContentText("Não é possível remover alunos que já possuem transações!");
        alerta.show();
    }
    
    public void emitirAlertaErro(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Erro ao cadastrar!");
        alerta.setContentText("Tente Novamente!");
        alerta.show();
    }
    
    public void esvaziarCampos(){
        textFieldNome.setText(null);
        textFieldLogin.setText(null);
        textFieldEmail.setText(null);
        textFieldIdade.setText(null);
        textFieldCpf.setText(null);
        textFieldTelefone.setText(null);
        passwordFieldSenhaParaAcesso.setText(null);
        comboBoxLocalidade.setValue(null);
    }
    
    public boolean verificarPreenchimentoDeDados(){
        String errorMessage = "";
        
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
             errorMessage += "Nome inválido!\n";
        }
        if (textFieldLogin.getText() == null || textFieldLogin.getText().length() == 0) {
            errorMessage += "Login inválido!\n";
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
