package controllers;

import play.*;
import play.mvc.*;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void getAuthentification(String nom,String prenom,
    		String numero_carteBleue,Date date_validite,String cryptogramme,String id_commande) 
    {
    	Verification_commande verifCarte= new Verification_commande(prenom, prenom, cryptogramme, numero_carteBleue, date_validite);
    	
    	try{
    		String urlFacturation=Play.configuration.getProperty("droit.url");
    		URL myURL = new URL(urlFacturation);
    	    URLConnection myURLConnection = myURL.openConnection();
    	    myURLConnection.connect();
    	}catch(Exception e){
    		System.out.println(e.getCause());
    	}
    	if(verifCarte.getErrors().isEmpty()){
    		Commande commande = new Commande(true,verifCarte.getErrors());
    		renderJSON(commande);
    	}else{
    		Commande commande = new Commande(false,verifCarte.getErrors());
    		renderJSON(commande);
    	}
    	
    	
    }
   

}