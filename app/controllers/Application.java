package controllers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

import models.Commande;
import models.Transaction;
import models.Verification_commande;
import play.Logger;
import play.Play;
import play.i18n.Messages;
import play.mvc.Controller;

public class Application extends Controller {

	public static void getAuthentification(String nom, String prenom,
			String numero_carteBleue, Date date_validite, String cryptogramme,
			String id_commande) {
		Verification_commande verifCarte = new Verification_commande(prenom,
				prenom, cryptogramme, numero_carteBleue, date_validite);

		if (verifCarte.getErrors().isEmpty()) {
			
			try {
				String urlFacturation = Play.configuration
						.getProperty("facture.url");
				urlFacturation += id_commande;
				
				
				URL urlUse = new URL(urlFacturation);
		        HttpURLConnection conn = null;
		        conn = (HttpURLConnection) urlUse.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-length", "0");
		        conn.setUseCaches(false);
		        conn.setAllowUserInteraction(false);
		        conn.connect();
		        Logger.info("Connection a la facturation :"+conn.getURL());
		        
		        Logger.info("message facturaction : %s", conn.getResponseMessage());
		        if(!conn.getResponseMessage().equals("OK")){
		        	
		        	verifCarte.add_error(Messages.get("error_facturation"));
		        }

			} catch (Exception e) {
				Logger.error("erreur de la connection a l'url Facture : url inconnu");
			}
		} 
			Transaction trans = Transaction.find("id_commande=?", id_commande).first();
			if(trans==null){
				Integer.valueOf(id_commande);
				trans = new Transaction(id_commande, verifCarte.getErrors().isEmpty(), new Date(), nom, prenom);
			}else{
				trans.setDate_transaction(new Date());
				trans.setValidation(verifCarte.getErrors().isEmpty());
			}
			try{
				trans.save();
			}catch(Exception e){
				Logger.error("la sauvegarde de la transation a echoué");
			}
			
			
			Commande commande = new Commande(verifCarte.getErrors().isEmpty(), verifCarte.getErrors());
			renderJSON(commande);
		

	}
	
	

}