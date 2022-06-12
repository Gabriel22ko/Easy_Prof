/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.ContratacaoDAO;
import easy_prof.modelDAO.DadosContratacaoDAO;
import easy_prof.modelDAO.ProfessorDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.DadosContratacao;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLRemocaoContratacaoController implements Initializable {

    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private TableView<DadosContratacao> tableViewContratacoes;
    @FXML
    private TableColumn<DadosContratacao, Integer> tableColumnCodigo;
    @FXML
    private TableColumn<DadosContratacao, String> tableColumnAluno;
    @FXML
    private TableColumn<DadosContratacao, String> tableColumnProfessor;
    @FXML
    private TableColumn<DadosContratacao, LocalDate> tableColumnDataDeAula;
    @FXML
    private TableColumn<DadosContratacao, Double> tableColumnDuracao;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private ObservableList listaObservavelContratacoes;
    private final DadosContratacaoDAO dadosContratacaoDAO = new DadosContratacaoDAO();
    private final ContratacaoDAO contratacaoDAO = new ContratacaoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dadosContratacaoDAO.setConnection(connection);
        contratacaoDAO.setConnection(connection);
        professorDAO.setConnection(connection);
        carregarTableViewContratacoes();
    }    
    
    public void carregarTableViewContratacoes(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdContratacao"));
        tableColumnAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tableColumnProfessor.setCellValueFactory(new PropertyValueFactory<>("nomeProfessor"));
        tableColumnDataDeAula.setCellValueFactory(new PropertyValueFactory<>("dataDeAula"));
        tableColumnDuracao.setCellValueFactory(new PropertyValueFactory<>("duracaoAula"));
        
        listaObservavelContratacoes = FXCollections.observableArrayList(dadosContratacaoDAO.retornarContratacoes());
        tableViewContratacoes.setItems(listaObservavelContratacoes);
    }
    
    public void removerContratacao(){
        if(tableViewContratacoes.getSelectionModel().getSelectedItem() != null){
            if(contratacaoDAO.remover(tableViewContratacoes.getSelectionModel().getSelectedItem().getCdContratacao())){
                listaObservavelContratacoes.remove(tableViewContratacoes.getSelectionModel().getSelectedItem());
                emitirAlertaSucesso();
            }else{
                emitirAlertaErro();
            }
        }else{
            emitirAlertaSelecionarCampo();
        }
    }
    
    public void emitirAlertaSelecionarCampo(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Nenhuma Contratação selecionada!");
        alerta.setContentText("Selecione uma contratação para prosseguir!");
        alerta.show();
    }
    
    public void emitirAlertaSucesso(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Sucesso!");
        alerta.setHeaderText("Sucesso!");
        alerta.setContentText("Os dados foram removidos!");
        alerta.show();
    }
    
    public void emitirAlertaErro(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro!");
        alerta.setHeaderText("Erro ao remover!!");
        alerta.setContentText("Tente novamente!");
        alerta.show();
    }
}
