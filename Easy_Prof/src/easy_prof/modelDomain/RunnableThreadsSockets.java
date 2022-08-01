/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy_prof.modelDomain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author gabri
 */
public class RunnableThreadsSockets implements Runnable {
    
    final private Label label;
    
    public RunnableThreadsSockets(Label label){
        this.label = label;
    }
    
    @Override
    public void run() {
       while(true){
           try{
               Socket socket = new Socket("34.125.13.172", 12345);
               ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
               int qtdConexoes = (Integer) input.readObject();
               Platform.runLater(() -> label.setText(String.valueOf(qtdConexoes)));
               Thread.sleep(10000);
           }catch(IOException | ClassNotFoundException | InterruptedException ex){
               Logger.getLogger(RunnableThreadsSockets.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
}
