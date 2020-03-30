/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.entites.Visiteur;
import static fr.gsb.rv.dr.modeles.ModeleGsbRv.seConnecter;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import static fr.gsb.rv.dr.technique.Session.ouvrir;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static com.sun.javaws.ui.SplashScreen.hide;
import static java.awt.SystemColor.menu;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.Session;
import java.sql.PreparedStatement;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class AppliRVDR extends Application {

    Visiteur visiteur = null;//Couche technique 2-8 et Modèle 3-4
    boolean session = Session.estOuverte();
    //TEST Couche technique 2-4    Visiteur visiteur = new Visiteur("OB0041", "Oumayma", "BELLILI);
    @Override
    public void start(Stage primaryStage) throws ConnexionException, SQLException {
            
        /*
       Connection connexion = ConnexionBD.getConnexion(); //Couche technique 2-8 et Modèle 3-4
        ResultSet res;
        String req = "SELECT vis_nom, vis_prenom, vis_matricule, vis_mdp FROM Visiteur where vis_matricule = ?";
        PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(req);
        pstmt.setString(1, "a131");
        res = pstmt.executeQuery();
        while (res.next()) {
            visiteur = new Visiteur(res.getString(3), res.getString(1), res.getString(2));
            
        }
        System.out.println(visiteur.getMatricule() + visiteur.getNom() + visiteur.getPrenom());*/
        
        
        
        PanneauAccueil vueAccueil = new PanneauAccueil();
        vueAccueil.setStyle("-fx-background-color: white;");
        PanneauPraticiens vuePraticiens = new PanneauPraticiens();
        vuePraticiens.setStyle("-fx-background-color: white;");
        PanneauRapports vueRapports= new PanneauRapports();
        vueRapports.setStyle("-fx-background-color: white;");
        StackPane panneau = new StackPane();
        panneau.getChildren().add(vueAccueil);
        panneau.getChildren().add(vuePraticiens);
        panneau.getChildren().add(vueRapports);
        
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu( "Fichier" ) ;
        MenuItem itemSeConnecter = new MenuItem( "Se Connecter");
        menuFichier.getItems().add( itemSeConnecter );
        barreMenus.getMenus().add( menuFichier );
 
        MenuItem itemSeDeconnecter = new MenuItem( "Se Deconnecter");
        
        SeparatorMenuItem SeparatorQuitter = new SeparatorMenuItem();
        menuFichier.getItems().add( SeparatorQuitter);
     
        
        MenuItem itemQuitter = new MenuItem( "Quitter");
        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        menuFichier.getItems().add( itemQuitter);

        
        Menu menuRapports = new Menu( "Rapports" ) ;
        MenuItem itemConsulter = new MenuItem( "Consulter");

        
        
        Menu menuPraticiens = new Menu( "Praticiens" ) ;
        MenuItem itemHesitants = new MenuItem( "Hésitants");

        //SeparatorMenuItem SeparatorQuitter = new SeparatorMenuItem();
       // MenuItem itemQuitter = new MenuItem( "Quitter");
        //   menuFichier.getItems().add( itemQuitter);
        

        
        

        
        BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        //root.getChildren().add(barreMenus);

        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("GSB-RV");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        itemQuitter.setOnAction(actionEvent ->{
            Alert alertQuitter = new Alert (Alert.AlertType.CONFIRMATION);
            
            alertQuitter.setTitle("Quitter");
            alertQuitter.setHeaderText("Demande de confirmation");
            alertQuitter.setContentText("Voulez-vous quitter l'application");
            ButtonType btnOui = new ButtonType("Oui");
            ButtonType btnNon = new ButtonType("Non");
            
            alertQuitter.getButtonTypes().setAll(btnOui, btnNon);
            Optional<ButtonType> reponse = alertQuitter.showAndWait();
            if(reponse.get() == btnOui){
                Platform.exit();
            }
            else{
                alertQuitter.close();
            }
 
        });
        
        
                itemSeConnecter.setOnAction(actionEvent -> {
           
            VueConnexion vue = new VueConnexion();
            Optional<Pair<String, String>> reponse = vue.showAndWait();
            if (reponse.isPresent()) {
                //TEST Couche modèle 3.4
                try {   
                    String[] resultat = reponse.get().toString().split("=");
                    visiteur = ModeleGsbRv.seConnecter(resultat[0], resultat[1]);
                } catch (ConnexionException ex) {
                    Logger.getLogger(AppliRVDR.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (visiteur != null) {
                    Session.ouvrir(visiteur);
                    session = Session.estOuverte();
                    primaryStage.setTitle("GSB-RV-DR " + Session.getLeVisiteur().getNom() + " " + Session.getLeVisiteur().getPrenom());
                    

                    
                    barreMenus.getMenus().clear();
                    menuFichier.getItems().clear();
                    barreMenus.getMenus().add(menuFichier);

                    menuFichier.getItems().add( itemSeDeconnecter);
                    menuFichier.getItems().add( itemQuitter);         
                    menuRapports.getItems().add( itemConsulter );
                    menuPraticiens.getItems().add( itemHesitants );
                    barreMenus.getMenus().add( menuRapports );
                    barreMenus.getMenus().add( menuPraticiens );
            
            
                } else {
                    Alert dlgNok = new Alert (Alert.AlertType.ERROR);
                    dlgNok.setTitle ("Erreur !");
                    dlgNok.setHeaderText("Connexion annulée :");
                    dlgNok.setContentText("Matricule ou mot de passe éronnée!");
                    dlgNok.showAndWait();
                }
            }
        });
  
            

                itemSeDeconnecter.setOnAction(actionEvent ->{
                    
            Session.fermer();
            session = Session.estOuverte();        
            barreMenus.getMenus().clear();
            menuFichier.getItems().clear();
            menuRapports.getItems().clear();
            menuPraticiens.getItems().clear();
            
            barreMenus.getMenus().add(menuFichier);

            menuFichier.getItems().add( itemSeConnecter);
            menuFichier.getItems().add( itemQuitter);  
            primaryStage.setTitle("GSB-RV");
            
        });
                
       itemConsulter.setOnAction(actionEvent ->{
           System.out.println("[Rapports] " + Session.getLeVisiteur().getNom() + " " + Session.getLeVisiteur().getPrenom());//TEST Couche technique 2-4 2-8 et Couche Modèle 3-4
           vueAccueil.setVisible(false);
           vueRapports.setVisible(true);
           vuePraticiens.setVisible(false);
       });
       
       itemHesitants.setOnAction(actionEvent ->{
           System.out.println("[Praticiens] " + Session.getLeVisiteur().getNom() + " " + Session.getLeVisiteur().getPrenom());//TEST Couche technique 2-4 2-8 et Couche Modèle 3-4
           vueAccueil.setVisible(false);
           vueRapports.setVisible(false);
           vuePraticiens.setVisible(true);
           
       });
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}