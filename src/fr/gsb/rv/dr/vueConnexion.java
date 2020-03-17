/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Session;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author etudiant
 */
public class vueConnexion {
    
     void start(Stage primaryStage) {
        Session uneSession = null;
        Visiteur unVisiteur = new Visiteur("OB001" ,"BELLILI","Oumayma" );
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        MenuItem itemQuitter = new MenuItem("Quitter");
        menuFichier.getItems().add(itemSeConnecter);
        
        
        SeparatorMenuItem SeparatorQuitter = new SeparatorMenuItem();
        menuFichier.getItems().add( SeparatorQuitter);
     
        menuFichier.getItems().add(itemQuitter);
        barreMenus.getMenus().add(menuFichier);

        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        Menu menuRapports = new Menu("Rapports");
        MenuItem itemConsulter = new MenuItem("Consulter");
        
        Menu menuPraticiens = new Menu("Praticiens");
        MenuItem itemHesitants = new MenuItem("Hésitants");
        
        BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        itemQuitter.setOnAction( actionEvent -> {
        Alert alertQuitter = new Alert( Alert.AlertType.CONFIRMATION);
        alertQuitter.setTitle("Quitter");
        alertQuitter.setHeaderText("Demande de confirmation");
        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
        ButtonType btnOui = new ButtonType("Oui");
        ButtonType btnNon = new ButtonType("Non");
        alertQuitter.getButtonTypes().setAll(btnOui, btnNon);
        Optional<ButtonType> reponse = alertQuitter.showAndWait();
        if(reponse.get() == btnOui) {
            Platform.exit();
        }
        else{
            alertQuitter.close();
        }
        });
        

        itemSeConnecter.setOnAction( actionEvent -> {
          /*  barreMenus.getMenus().clear();
            menuFichier.getItems().clear();
            menuRapports.getItems().clear();
            menuPraticiens.getItems().clear();
            barreMenus.getMenus().add(menuFichier);

            menuFichier.getItems().add( itemSeDeconnecter);
            menuFichier.getItems().add( itemQuitter);         
            menuRapports.getItems().add( itemConsulter );
            menuPraticiens.getItems().add( itemHesitants );
            barreMenus.getMenus().add( menuRapports );
            barreMenus.getMenus().add( menuPraticiens );
            primaryStage.setTitle(unVisiteur.getNom() + " " + unVisiteur.getPrenom() ); */
            
            
            Alert alertAuthentification = new Alert( Alert.AlertType.CONFIRMATION);
            alertAuthentification.setTitle("Authentification");
            alertAuthentification.setHeaderText("Saisir vos données de connexion");
            ButtonType OK_DONE = new ButtonType("Se connecter");
            ButtonType CANCEL_CLOSE = new ButtonType("Annuler");
            alertAuthentification.getButtonTypes().setAll(OK_DONE, CANCEL_CLOSE);
            
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField matricule = new TextField();
            matricule.setPromptText("");
            PasswordField mdp = new PasswordField();
            mdp.setPromptText("");
            grid.add(new Label("Matricule : "), 0, 0);
            grid.add(matricule, 1, 0);
            grid.add(new Label("Mot de passe : "), 0, 1);
            grid.add(mdp, 1, 1);
            
            alertAuthentification.getDialogPane().setContent(grid);
            
            
                new Callback<ButtonType, Pair<String,String>>(){
                    @Override
                    public Pair<String, String>call(ButtonType typeBouton){
                 if ( typeBouton == OK_DONE){
                     String login = matricule.getText();
                     String password = mdp.getText();
                    return new Pair<String, String>(login, password);
        }
            return null;
                    }
        };
            Optional<ButtonType> reponse = alertAuthentification.showAndWait();
            
           
        if(reponse.get() == OK_DONE) {
            ModeleGsbRv connexion = new ModeleGsbRv();
            //connexion.seConnecter();
            
            barreMenus.getMenus().clear();
            menuFichier.getItems().clear();
            menuRapports.getItems().clear();
            menuPraticiens.getItems().clear();
            barreMenus.getMenus().add(menuFichier);

            menuFichier.getItems().add( itemSeDeconnecter);
            menuFichier.getItems().add( itemQuitter);         
            menuRapports.getItems().add( itemConsulter );
            menuPraticiens.getItems().add( itemHesitants );
            barreMenus.getMenus().add( menuRapports );
            barreMenus.getMenus().add( menuPraticiens );
            primaryStage.setTitle(unVisiteur.getNom() + " " + unVisiteur.getPrenom() );
        }
        else{
            alertAuthentification.close();
        }
            });
        
        itemSeDeconnecter.setOnAction( actionEvent -> {
            barreMenus.getMenus().clear();
            menuFichier.getItems().clear();
            
            barreMenus.getMenus().add(menuFichier);
            menuFichier.getItems().add(itemSeConnecter);
            menuFichier.getItems().add(itemQuitter);
            primaryStage.setTitle("GSB-RV");
            });
        
        itemConsulter.setOnAction( actionEvent -> {
            System.out.println("[Rapports] " + unVisiteur.getNom() + " " + unVisiteur.getPrenom());
            });
        
        itemHesitants.setOnAction( actionEvent -> {
            System.out.println("[Praticiens] " + unVisiteur.getNom() + " " + unVisiteur.getPrenom());
            });
    }
   
}
