/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.DisciplinaAtuacaoDAO;
import easy_prof.modelDAO.NivelFormacaoDAO;
import easy_prof.modelDAO.ProfessorDAO;
import easy_prof.modelDAO.UfDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.DisciplinaAtuacao;
import easy_prof.modelDomain.NivelFormacao;
import easy_prof.modelDomain.Professor;
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
public class FXMLCadastroProfessorController implements Initializable {

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
    @FXML
    private ComboBox<DisciplinaAtuacao> comboBoxDisciplinas;
    @FXML
    private ComboBox<NivelFormacao> comboBoxFormacoes;
    @FXML
    private TextField textFieldCustoHora;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final UfDAO ufDAO = new UfDAO();
    private final DisciplinaAtuacaoDAO disciplinasDAO = new DisciplinaAtuacaoDAO();
    private final NivelFormacaoDAO nivelFormacaoDAO = new NivelFormacaoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final Professor professor = new Professor();
 
    private ObservableList<Uf> listaObservavelLocalidade;
    private ObservableList<DisciplinaAtuacao> listaObservavelDisciplinas;
    private ObservableList<NivelFormacao> listaObservavelFormacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ufDAO.setConnection(connection);
        disciplinasDAO.setConnection(connection);
        nivelFormacaoDAO.setConnection(connection);
        professorDAO.setConnection(connection);
        carregarComboBox();
    }

    public void carregarComboBox(){
        listaObservavelLocalidade = FXCollections.observableArrayList(ufDAO.listar());
        listaObservavelDisciplinas = FXCollections.observableArrayList(disciplinasDAO.listar());
        listaObservavelFormacao = FXCollections.observableArrayList(nivelFormacaoDAO.listar());
        comboBoxLocalidade.setItems(listaObservavelLocalidade);
        comboBoxDisciplinas.setItems(listaObservavelDisciplinas);
        comboBoxFormacoes.setItems(listaObservavelFormacao);
    }
    
    public void receberDadosInterface(){
        if(verificarPreenchimentoDeDados() == true){
            professor.setNome(textFieldNome.getText());
            professor.setLogin(textFieldLogin.getText());
            professor.setEmail(textFieldEmail.getText());
            professor.setIdade(Integer.parseInt(textFieldIdade.getText()));
            professor.setTelefone(textFieldTelefone.getText());
            professor.setCpf(textFieldCpf.getText());
            professor.setLocalidade(comboBoxLocalidade.getSelectionModel().getSelectedItem());
            professor.setDisciplina(comboBoxDisciplinas.getSelectionModel().getSelectedItem());
            professor.setFormacao(comboBoxFormacoes.getSelectionModel().getSelectedItem());
            professor.setCustoHora(Double.parseDouble(textFieldCustoHora.getText()));
            professor.setSenhaParaAcesso(passwordFieldSenhaParaAcesso.getText());
        }
    }
    
    @FXML
    public void persistirDados(){
        receberDadosInterface();
        if(professorDAO.inserir(professor)){
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
        passwordFieldSenhaParaAcesso.setText(null);
        textFieldCustoHora.setText(null);
        comboBoxLocalidade.setValue(null);
        comboBoxFormacoes.setValue(null);
        comboBoxDisciplinas.setValue(null);
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
        if(comboBoxDisciplinas.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Disciplina inválida!\n";
        }
        if(comboBoxFormacoes.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Formação inválida!\n";
        }
        if(textFieldCustoHora.getText() == null || textFieldCustoHora.getText().length() <= 0){
            errorMessage += "Custo Inválido!\n";
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
