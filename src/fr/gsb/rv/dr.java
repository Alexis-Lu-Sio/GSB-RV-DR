/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv;

import static com.sun.javaws.ui.SplashScreen.hide;
import static java.awt.SystemColor.menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
        menuFichier.getItems().add(itemQuitter);
        barreMenus.getMenus().add(menuFichier);
        
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
            Platform.exit();
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
