package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
       String requete = "select vis_nom, vis_prenom from Visiteur where vis_matricule = ? and vis_mdp =?";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString( 2 , mdp );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur(matricule, resultat.getString("vis_nom"), resultat.getString("vis_prenom")) ;
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
        
        
        
        
        
    }
    
  public static List<Praticien> getPraticiensHesitants() throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        String requete = "select RapportVisite.pra_num, pra_nom, pra_ville, pra_coefnotoriete, rap_date_visite , rap_coef_confiance " +
                                "from RapportVisite " +
                                "inner join Praticien " +
                                "on RapportVisite.pra_num = Praticien.pra_num " +
                                "where rap_coef_confiance < 5 " +
                                "and rap_date_visite = ( " +
                                "select MAX(rap_date_visite) " +
                                "from RapportVisite " +
                                "where RapportVisite.pra_num = Praticien.pra_num )" ;
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            List<Praticien> praticiensHesitants = new ArrayList<Praticien>();
            while (resultat.next()) {
                Praticien praticien = new Praticien(resultat.getString("pra_num"), 
                resultat.getString("pra_nom"), resultat.getString("pra_ville"), 
                Float.parseFloat(resultat.getString("pra_coefnotoriete")), 
                LocalDate.parse(resultat.getString("rap_date_visite"), 
                DateTimeFormatter.ofPattern("yyyy-MM-d")), 
                Integer.parseInt(resultat.getString("rap_coef_confiance")));
                praticiensHesitants.add(praticien);
            }
            requetePreparee.close();
            return praticiensHesitants;
        }
        catch(SQLException e) {
            return null;
        }
    }
}
