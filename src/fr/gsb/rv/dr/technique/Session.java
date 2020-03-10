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
        
        this.unVisiteur = unVisiteur;
    }
    
    public static void ouvrir(Visiteur unVisiteur){
       Session.session=new Session(unVisiteur); 
    }
    
    public static void fermer(){
        Session.session = null;
    }

    public static Visiteur getUnVisiteur() {
        return unVisiteur;
    }
    

    public Session getSession() {
        return session;
    }
    
}