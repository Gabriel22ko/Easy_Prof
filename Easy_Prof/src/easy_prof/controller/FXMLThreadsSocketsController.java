/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.controller;

import easy_prof.modelDomain.RunnableThreadsSockets;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FXMLThreadsSocketsController implements Initializable {

    @FXML
    private Label labelQtdConexoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RunnableThreadsSockets runnableThreadSockets = new RunnableThreadsSockets(labelQtdConexoes);
        Thread threadQtdConexoes = new Thread(runnableThreadSockets);
        threadQtdConexoes.start();
    }
}
