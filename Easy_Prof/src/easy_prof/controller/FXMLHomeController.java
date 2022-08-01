/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private MenuItem menuItemAlterarCadastroAluno;
    @FXML
    private MenuItem menuItemAlterarCadastroProfessor;
    @FXML
    private Button buttonInicio;
    @FXML
    private Button buttonCadastrarAluno;
    @FXML
    private Button buttonCadastrarProfessor;
    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private Button buttonRelatorios;
    @FXML
    private Button buttonGrafico;
    @FXML
    private Button buttonContratarProfessor;
    @FXML
    private MenuItem menuItemAlterarContratacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void trocarInterfaceInicio() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLImagemInicio.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    @FXML
    public void trocarInterfaceCadastrarAluno() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLCadastroAluno.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    @FXML
    public void trocarInterfaceCadastrarProfessor() throws IOException{
        Parent interfaceCadastrarProfessor = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLCadastroProfessor.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceCadastrarProfessor);
    }
    
    @FXML
    public void trocarInterfaceLoginAluno() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLLoginAluno.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    public void trocarInterfaceAlterarDadosAluno() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLAlterarDadosAluno.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    @FXML
    public void trocarInterfaceLoginProfessor() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLLoginProfessor.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }

    @FXML
    public void trocarInterfaceContratarProfessor() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLContratarProfessor.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    public void trocarInterfaceGrafico() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLGraficos.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    public void trocarInterfaceRelatorio() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLRelatorio.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    public void trocarInterfaceRemocaoContratacao() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLRemocaoContratacao.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
    
    public void trocarInterfaceThreadsSockets() throws IOException{
        Parent interfaceInicio = FXMLLoader.load(getClass().getResource("/easy_prof/view/FXMLThreadsSockets.fxml"));
        anchorPanePrincipal.getChildren().removeAll();
        anchorPanePrincipal.getChildren().setAll(interfaceInicio);
    }
}