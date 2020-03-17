/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class VueConnexion extends Dialog<Pair<String, String>> {

    public VueConnexion() {
        ButtonType OK_DONE = new ButtonType ("Confirmer");
        ButtonType CANCEL_CLOSE = new ButtonType ("Annuler");
        
        TextField tfMatricule = new TextField("");
        PasswordField pfMdp = new PasswordField();
        
        Label labelId = new Label("Matricule : ");
        Label labelMdp = new Label("Mot de passe : ");
        
        GridPane grid = new GridPane();
        
        grid.add(labelId, 0, 0);
        grid.add(tfMatricule, 1, 0);
        grid.add(labelMdp, 0, 1);
        grid.add(pfMdp, 1, 1);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        grid.setHalignment(labelId, HPos.LEFT);
        grid.setHalignment(tfMatricule, HPos.RIGHT);
        grid.setHalignment(labelMdp, HPos.LEFT);
        grid.setHalignment(pfMdp, HPos.RIGHT);
        
        this.setTitle("Connexion");
        this.setHeaderText("Se connecter :");
        this.getDialogPane().setContent(grid);
        this.getDialogPane().getButtonTypes().setAll(CANCEL_CLOSE, OK_DONE);
        
        setResultConverter((ButtonType typeBouton) -> {
            if (typeBouton == OK_DONE) {
                String matricule = tfMatricule.getText();
                String mdp = pfMdp.getText();
                if ("".equals(matricule) || "".equals(mdp)) {
                    matricule = "erreur";
                    mdp = "erreur";
                }
                return new Pair<String, String>(matricule, mdp);
            }
            return null;
        });
    }
    
}