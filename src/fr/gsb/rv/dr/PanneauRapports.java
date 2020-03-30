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
public class PanneauRapports extends Pane {

    public PanneauRapports() {
        VBox vBox = new VBox();
        Label label = new Label("Rapports");
        vBox.getChildren().add(label);
        this.getChildren().add(vBox);    
}
}