/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;



import fr.gsb.rv.dr.entites.Praticien;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JButton;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Mois;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javax.swing.JComboBox;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane {
    private Pane pane = new Pane();
    private TableView<RapportVisite> tvRapports;
    
    public PanneauRapports() {
        VBox grid = new VBox();
        Label label = new Label("Rapports");
        grid.getChildren().add(label);
        grid.setStyle("-fx-background-color : white"); 
        pane.getChildren().add(grid);
        //ObservableList<String> visiteurListe = FXCollections.observableArrayList("a131");
        ComboBox visiteur = new ComboBox();
        visiteur.getItems().addAll("a131");
        //ObservableList<String> moisListe = FXCollections.observableArrayList("Janvier","Février","Mars");
        ComboBox mois = new ComboBox();
        //mois.getItems().addAll(Arrays.asList(Mois.values()));
        mois.getItems().addAll("Janvier","Février","Mars");
        mois.setValue("Mars");
        ObservableList<String> anneeListe = FXCollections.observableArrayList("2019","2020");
        ComboBox annee = new ComboBox(anneeListe);
        visiteur.setEditable(true);
        mois.setEditable(true);
        annee.setEditable(true);
        Button Valider = new Button();
        TableView<RapportVisite> tableListe = new TableView<RapportVisite>();
        TableColumn<RapportVisite,Integer> colNumero = new TableColumn<RapportVisite,Integer>("Numéro");
        TableColumn<RapportVisite,String> colPraticien = new TableColumn<RapportVisite,String>("Praticien");
        TableColumn<RapportVisite,String> colVisiteur = new TableColumn<RapportVisite,String>("Visiteur");
        TableColumn<RapportVisite,String> colRedaction = new TableColumn<RapportVisite,String>("Rédaction");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPraticien.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colVisiteur.setCellValueFactory(new PropertyValueFactory<>("visiteur"));
        colRedaction.setCellValueFactory(new PropertyValueFactory<>("redaction"));
        tableListe.getColumns().addAll(colNumero, colPraticien, colVisiteur, colRedaction);
        GridPane gridPane = new GridPane();
        gridPane.add(visiteur,1,0);
        gridPane.add(mois,2,0);
        gridPane.add(annee,3,0);
        gridPane.add(Valider, 1,1);
        //grid.getChildren().add(tvRapports);
        pane.getChildren().add(gridPane);
        
    }
    
    public Pane getPane() {
        return pane;
    }
    
    public void rafraichir(){
        
    }
}