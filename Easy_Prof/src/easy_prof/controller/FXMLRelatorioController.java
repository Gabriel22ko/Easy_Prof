/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDAO.RelatorioDAO;
import easy_prof.modelDatabase.Database;
import easy_prof.modelDatabase.DatabaseFactory;
import easy_prof.modelDomain.Relatorio;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLRelatorioController implements Initializable {

    @FXML
    private TableView<Relatorio> tableViewDados;
    @FXML
    private TableColumn<Relatorio, String> tableColumnNomeAluno;
    @FXML
    private TableColumn<Relatorio, Integer> tableColumnQuantidadeContratacoes;
    @FXML
    private TableColumn<Relatorio, Double> tableColumnValorTotal;
    @FXML
    private Button buttonImprimir;
    
    private ObservableList<Relatorio> listaObservavelDados;
    private RelatorioDAO relatorioDAO = new RelatorioDAO();
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        relatorioDAO.setConnection(connection);
        try {
            carregarTableView();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableView() throws SQLException{
        listaObservavelDados = FXCollections.observableArrayList(relatorioDAO.buscarDadosRelatorio());
        tableColumnNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tableColumnQuantidadeContratacoes.setCellValueFactory(new PropertyValueFactory<>("quantContratacoes"));
        tableColumnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotalContratacoes"));
        tableViewDados.setItems(listaObservavelDados);
    }
    
    public void acaoImprimir() throws JRException{
        URL url = getClass().getResource("/easy_prof/relatorioJasper/Projeto_easy_prof.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
}
