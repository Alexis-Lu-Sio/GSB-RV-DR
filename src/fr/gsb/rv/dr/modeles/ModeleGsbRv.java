package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String requete = "SELECT p.pra_num, p.pra_nom, p.pra_ville, p.pra_coefnotoriete, MAX(r.rap_date_visite) AS date, r.rap_coef_confiance"
                       + " FROM Praticien p INNER JOIN RapportVisite r ON p.pra_num = r.pra_num"
                       + " GROUP BY p.pra_num";
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            List<Praticien> praticiensHesitants = new ArrayList<Praticien>();
            while (resultat.next()) {
                Praticien praticien = new Praticien(resultat.getString("pra_num"),
                resultat.getString("pra_nom"), 
                resultat.getString("pra_ville"),
                Float.parseFloat(resultat.getString("pra_coefnotoriete")),
                Date.valueOf(resultat.getString("date")).toLocalDate(), 
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
    
    
        public static List<Visiteur> getVisiteur() throws ConnexionException, SQLException{
        Connection connexion = ConnexionBD.getConnexion() ;
       
        
        String requete = "SELECT vis_matricule, vis_nom, vis_prenom "
                            + "FROM Visiteur;";
        
        try {
            
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            List<Visiteur> listeVisiteur = new ArrayList<Visiteur>();
            while( resultat.next() ){
                
                
                Visiteur visiteur = new Visiteur( 
                resultat.getString("vis_matricule"), 
                resultat.getString( "vis_nom" ),
                resultat.getString( "vis_prenom" ) );
                
                listeVisiteur.add(visiteur);
            }
            requetePreparee.close();
            return listeVisiteur;
        }
        catch( SQLException e ){
            
            return null;
        } 
    }
        
            public static List<RapportVisite> getRapportVisite(String matricule, int mois, int annee) throws ConnexionException, SQLException{
        Connection connexion = ConnexionBD.getConnexion() ;
        Statement requetePreparee = (Statement) connexion.createStatement();
        
        LocalDate date = LocalDate.of(annee, mois, 1);
        String laDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String requete = "select * " +
                            "from RapportVisite r, Visiteur v, Praticien p " +
                            "where p.pra_num = r.pra_num  " +
                     
                            "and v.vis_matricule = r.vis_matricule "+
                            "and v.vis_matricule = \""+matricule+"\" " +
                            "and rap_date_visite like '"+laDate+"%';";
        
        try {
            
            ResultSet resultat = requetePreparee.executeQuery(requete) ;
            List<RapportVisite> listeRapport = new ArrayList<>();
            
            while( resultat.next() ){
                RapportVisite rapport = new RapportVisite() ; 
                rapport.setNumero( resultat.getInt( "rap_num" ) );
                rapport.setDateVisite( resultat.getDate( "rap_date_visite" ).toLocalDate() );
                rapport.setDateRedaction( resultat.getDate( "rap_date_redaction" ).toLocalDate() );
                rapport.setBilan( resultat.getString( "rap_bilan" ) );
                rapport.setMotif( resultat.getString( "mo_libelle" ) );
                rapport.setCoefConfiance( resultat.getInt( "rap_coefConfiance" ) );
                rapport.setLu( resultat.getBoolean( "rap_lu" ) );
                
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( resultat.getString("vis_matricule") );
                visiteur.setNom(resultat.getString( "vis_nom" ));
                visiteur.setPrenom(resultat.getString( "vis_prenom" ));
                rapport.setLeVisiteur( visiteur );
                
                Praticien unPraticien = new Praticien();
                unPraticien.setNumero(resultat.getString( "pra_num" ));
                unPraticien.setVille(resultat.getString( "pra_ville" ));
                unPraticien.setNom(resultat.getString( "pra_nom" ));
                unPraticien.setPrenom(resultat.getString( "pra_prenom" ));
                rapport.setLePraticien( unPraticien );
                
                listeRapport.add(rapport);
                
            }
            return listeRapport;
        }
        catch( SQLException e ){
            
            return null;
        } 
    }
            
   public static void setRapportVisiteLu(String matricule, int numRapport) throws ConnexionException, SQLException{
       
        
        String requete = "update RapportVisite \n" +
                            "set rap_lu = 1 \n" +
                            "where rap_num = ? \n" +
                            "and vis_matricule = ?;";
        
        try { 
            Connection connexion = ConnexionBD.getConnexion() ;
            PreparedStatement pstmt = connexion.prepareStatement(requete);
            pstmt.setInt(1,numRapport);
            pstmt.setString(2, matricule);
            
            pstmt.executeUpdate();
            
        }
        catch( SQLException e ){
        } 
    }

    public static void setRapportVisite(String matricule, Integer numero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}



