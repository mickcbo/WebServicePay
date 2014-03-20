package controllers;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import models.Commande;
import models.Verification_commande;
import play.Play;
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
				URL myURL = new URL(urlFacturation);
				URLConnection myURLConnection = myURL.openConnection();
				myURLConnection.connect();

			} catch (Exception e) {
			}
		} 
			Commande commande = new Commande(verifCarte.getErrors().isEmpty(), verifCarte.getErrors());
			renderJSON(commande);
		

	}

}