/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.ProfessorDAO;
import easy_prof.modelDAO.UfDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Professor;
import easy_prof.modelDomain.Uf;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLContratarProfessorController implements Initializable {

    @FXML
    private ListView<Professor> listViewProfessoresDisponiveis;
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
    private Button buttonContratar;
    @FXML
    private ComboBox<Uf> comboBoxFiltrarUf;
    @FXML
    private Button buttonFiltrarUf;
    
    private Professor professor = new Professor();
    private ObservableList<Professor> listaObservavelProfessoresDisponiveis;
    private ObservableList<Uf> listaObservavelUf;
    private final UfDAO ufDAO = new UfDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        professorDAO.setConnection(connection);
        ufDAO.setConnection(connection);
        carregarComboBoxFiltrarUf();
        try {
            carregarListViewProfessoresDisponiveis();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLContratarProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarListViewProfessoresDisponiveis() throws SQLException{
        listaObservavelProfessoresDisponiveis = FXCollections.observableArrayList(professorDAO.buscarProfessores());
        listViewProfessoresDisponiveis.setItems(listaObservavelProfessoresDisponiveis);
    }
    
    public void carregarComboBoxFiltrarUf(){
        listaObservavelUf = FXCollections.observableArrayList(ufDAO.listar());
        comboBoxFiltrarUf.setItems(listaObservavelUf);
    }
    
    public void filtrarPorUf() throws SQLException{
        listaObservavelProfessoresDisponiveis = FXCollections.observableArrayList
        (professorDAO.buscarFiltradoUf(comboBoxFiltrarUf.getSelectionModel().getSelectedIndex()+1));
        listViewProfessoresDisponiveis.setItems(listaObservavelProfessoresDisponiveis);
        esvaziarCampos();
    }
    
    public void esvaziarCampos(){
        labelNome.setText("Nome: ");
        labelEmail.setText("Email: ");
        labelTelefone.setText("Telefone: ");
        labelIdade.setText("Idade: ");
        labelDisciplina.setText("Disciplina: ");
        labelFormacao.setText("Formação: ");
        labelEstado.setText("Estado: ");
        labelCustoHora.setText("Custo-Hora: R$");
    }
    
    @FXML
    public void exibirInformacoesProfessores(){
        professor = listViewProfessoresDisponiveis.getSelectionModel().getSelectedItem();
        labelNome.setText("Nome: "+professor.getNome());
        labelEmail.setText("Email: "+professor.getEmail());
        labelTelefone.setText("Telefone: "+professor.getTelefone());
        labelIdade.setText("Idade: "+professor.getIdade());
        labelDisciplina.setText("Disciplina: "+professor.getDisciplina().getNomeDisciplina());
        labelFormacao.setText("Formação: "+professor.getFormacao().getNomeNivelFormacao());
        labelEstado.setText("Estado: "+professor.getLocalidade().getEstadoUf());
        labelCustoHora.setText("Custo-Hora: R$"+professor.getCustoHora());
    }
    
    public void exibirAlertaSelecionarProfessor(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Nenhum professor selecionado!");
        alerta.setContentText("Selecione um professor para prosseguir!");
        alerta.show();
    }
    
    @FXML
    public void realizarContratacao() throws IOException{
        if(listViewProfessoresDisponiveis.getSelectionModel().getSelectedItem() == null){
            exibirAlertaSelecionarProfessor();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easy_prof/view/FXMLRealizarContratacaoProfessor.fxml"));
        Parent root = loader.load();
        FXMLRealizarContratacaoProfessorController controlador = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Realize login!");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        controlador.exibirInformacoesProfessores(professor);
        controlador.carregarComboBoxDuracaoAula();
        controlador.buttonCalcularValor.setOnAction(e -> controlador.calcularValor(professor));
        controlador.buttonContratar.setOnAction(e -> {
            try {
                controlador.persistirDados(professor);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLContratarProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
}