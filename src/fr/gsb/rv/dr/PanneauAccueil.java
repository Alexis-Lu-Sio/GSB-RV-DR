/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author etudiant
 */
public class PanneauAccueil extends Pane {

    public PanneauAccueil() {
        VBox vBox = new VBox();
        Label label = new Label("Accueil");
        vBox.getChildren().add(label);
        this.getChildren().add(vBox);
        
        
    }
    
  
    }

    
