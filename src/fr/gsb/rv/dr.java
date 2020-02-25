/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv;

import static com.sun.javaws.ui.SplashScreen.hide;
import static java.awt.SystemColor.menu;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author etudiant
 */
public class dr extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        MenuItem itemQuitter = new MenuItem("Quitter");
        menuFichier.getItems().add(itemSeConnecter);
        menuFichier.getItems().add(itemSeDeconnecter);
        
        
        SeparatorMenuItem SeparatorQuitter = new SeparatorMenuItem();
        menuFichier.getItems().add( SeparatorQuitter);
     
        menuFichier.getItems().add(itemQuitter);
        barreMenus.getMenus().add(menuFichier);

        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        Menu menuRapports = new Menu("Rapports");
        MenuItem itemConsulter = new MenuItem("Consulter");
        menuRapports.getItems().add(itemConsulter);
        barreMenus.getMenus().add(menuRapports);
        
        Menu menuPraticiens = new Menu("Praticiens");
        MenuItem itemHesitants = new MenuItem("Hésitants");
        menuPraticiens.getItems().add(itemHesitants);
        barreMenus.getMenus().add(menuPraticiens);
        
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
            });
        
        itemSeDeconnecter.setOnAction( actionEvent -> {
                
            });
        
        itemConsulter.setOnAction( actionEvent -> {
            
            });
        
        itemHesitants.setOnAction( actionEvent -> {
            
            });
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
