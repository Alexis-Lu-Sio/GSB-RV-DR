/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author etudiant
 */
public class dr extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        vueConnexion vue = new vueConnexion();
        vue.start(primaryStage);
       
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
