/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

/**
 *
 * @author etudiant
 */
public class Session{
    
    private static Session session = null;
    private static Visiteur unVisiteur;
    
    

    private Session(Visiteur unVisiteur){
        
        Session.unVisiteur = unVisiteur;
    }
    
    public static void ouvrir(Visiteur visiteur){
       Session.session=new Session(visiteur); 
    }
    
    public static void fermer(){
        Session.session = null;
        Session.unVisiteur = null;
    }

    public static Visiteur getLeVisiteur() {
        return Session.unVisiteur;
    }
    

    public Session getSession() {
        return Session.session;
    }
    
        public static boolean estOuverte(){
        return Session.session != null ;
    }
    
}