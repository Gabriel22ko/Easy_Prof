/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.AlunoDAO;
import easy_prof.modelDAO.ContratacaoDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Aluno;
import easy_prof.modelDomain.Contratacao;
import easy_prof.modelDomain.Professor;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLRealizarContratacaoProfessorController implements Initializable {

    @FXML
    private Label labelNome;
    @FXML
    private Label labelIdade;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelDisciplina;
    @FXML
    private Label labelEstado;
    @FXML
    private Label labelFormacao;
    @FXML
    private Label labelCustoHora;
    @FXML
    public Button buttonContratar;
    @FXML
    private ComboBox comboBoxDuracaoAula;
    @FXML
    private TextField textFieldVerificarLogin;
    @FXML
    private PasswordField passwordFieldVerificarSenha;
    @FXML
    private Label labelValor;
    @FXML
    public Button buttonCalcularValor;
    @FXML
    private DatePicker datePickerDataAula;
    
    private final ObservableList<Integer> listaObservavelDuracaoAula = FXCollections.observableArrayList();
    private Aluno aluno = new Aluno();
    private final Contratacao contratacao = new Contratacao();
    private Contratacao contratacaoAux = new Contratacao();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ContratacaoDAO contratacaoDAO = new ContratacaoDAO();
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
        contratacaoDAO.setConnection(connection);
    }

    public void exibirInformacoesProfessores(Professor professor){
        labelNome.setText("Nome: "+professor.getNome());
        labelEmail.setText("Email: "+professor.getEmail());
        labelTelefone.setText("Telefone: "+professor.getTelefone());
        labelIdade.setText("Idade: "+professor.getIdade());
        labelDisciplina.setText("Disciplina: "+professor.getDisciplina().getNomeDisciplina());
        labelFormacao.setText("Formação: "+professor.getFormacao().getNomeNivelFormacao());
        labelEstado.setText("Estado: "+professor.getLocalidade().getEstadoUf());
        labelCustoHora.setText("Custo-Hora: R$"+professor.getCustoHora());
    }
    
    public void carregarComboBoxDuracaoAula(){
        for(int x = 1; x <= 4; x++){
            listaObservavelDuracaoAula.add(x);
        }
        comboBoxDuracaoAula.setItems(listaObservavelDuracaoAula);
    }
    
    public void persistirDados(Professor professor) throws SQLException{
        if(verificarCamposVazios()){
            receberDados(professor);
            if(aluno.getCdAluno() != 0){
                contratacaoAux = contratacaoDAO.regraDeNegocio(professor, contratacao.getDataDeAula());
                if(contratacaoAux.getCdContratacao() == 0){
                    if(contratacaoDAO.inserir(contratacao)){
                        exibirConfirmacao();
                        esvaziarCampos();
                    }
                }else{
                    emitirAlertaViolacaoRegraDeNegocio();
                }
            }else{
                emitirAlertaLoginNaoEncontrado();
            }
        }else{
            emitirAlertaCamposVazios();
        }
    }
    
    public void esvaziarCampos(){
        comboBoxDuracaoAula.setValue(null);
        datePickerDataAula.setValue(null);
        passwordFieldVerificarSenha.setText(null);
        textFieldVerificarLogin.setText(null);
    }
    
    public void calcularValor(Professor professor){
        double resultadoValor = professor.getCustoHora() *( 
        comboBoxDuracaoAula.getSelectionModel().getSelectedIndex()+1);
        labelValor.setText("Valor: R$"+resultadoValor);
    }
    
    public void receberDados(Professor professor) throws SQLException{
        contratacao.setDuracaoAula(comboBoxDuracaoAula.getSelectionModel().getSelectedIndex()+1);
        contratacao.setValor(professor.getCustoHora() * (comboBoxDuracaoAula.getSelectionModel().getSelectedIndex()+1));
        contratacao.setDataDeAula(datePickerDataAula.getValue());
        contratacao.setCdProfessorContratado(professor.getCdProfessor());
        contratacao.setDataContratacao(LocalDate.now());
        aluno = alunoDAO.verificar(textFieldVerificarLogin.getText(), passwordFieldVerificarSenha.getText());
        contratacao.setCdAlunoContratante(aluno.getCdAluno());
    }
    
    
    public void emitirAlertaLoginNaoEncontrado(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Login não encontrado!");
        alerta.setContentText("Preencha novamente ou cadastre-se");
        alerta.show();
    }
    
    public void emitirAlertaCamposVazios(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Existem campos não preenchidos!");
        alerta.setContentText("Preencha corretamente os campos para prosseguir");
        alerta.show();
    }
    
    public void exibirConfirmacao(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Sucesso!");
        alerta.setHeaderText("Contratação realizada com sucesso!");
        alerta.setContentText("Os dados foram gravados!");
        alerta.show();
    }
    
    public void emitirAlertaViolacaoRegraDeNegocio(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Violação da regra de negócio!");
        alerta.setContentText("O professor selecionado já possui aula nesta data");
        alerta.show();
    }
    
    public boolean verificarCamposVazios(){
        if (textFieldVerificarLogin.getText() == null || textFieldVerificarLogin.getText().length() == 0) {
            return false;
        }
        if(comboBoxDuracaoAula.getSelectionModel().getSelectedItem() == null){
            return false;
        }
        if(datePickerDataAula.getValue() == null){
            return false;
        }
        if(passwordFieldVerificarSenha.getText() == null || passwordFieldVerificarSenha.getText().length() == 0){
            return false;
        }
        return true;
    }
}
