package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;

import play.i18n.Messages;

public class Verification_commande 
{
		
		private String prenom;
		
		
		private String nom;
		
		
		private String cryptogramme;
		
		
		private String num_carte;
		
		
		private  Date date_validite;
		
		
		private ArrayList<String> errors;
		
		private boolean commande_valide;
		

		public Verification_commande(String prenom, String nom,
				String cryptogramme, String num_carte, Date date_validite) {
			super();
			this.prenom = prenom;
			this.nom = nom;
			this.cryptogramme = cryptogramme;
			this.num_carte = num_carte;
			this.date_validite = date_validite;
			this.errors=new ArrayList<String>();
			this.Verification_bancaire();
		}

		public String getCryptogramme() {
			return cryptogramme;
		}

		public String getNum_carte() {
			return num_carte;
		}
		
		public Date getDate_validite() {
			return date_validite;
		}
		
		public String getPrenom() {
			return prenom;
		}

		

		public String getNom() {
			return nom;
		}


		public ArrayList<String> getErrors() {
			return errors;
		}

		
		private void Verification_bancaire()
		{
			
			//--------------------------Verification de la carte -------------------------------
			//nom et prenom
			
			if(nom.isEmpty() || prenom.isEmpty()){
				
				this.errors.add(Messages.get("error_nom_prenom"));
			}
			//numero de carte 
			
			if(this.num_carte.length()<14 ||this.num_carte.length()>16){
				
				this.errors.add(Messages.get("error_Numero_carte_bleu"));
			}
			//cryptogramme
			
			if(this.cryptogramme.length()!=3){
				
				this.errors.add(Messages.get("error_cryptogramme"));
			}
			//Date de validité
			/**
			if(this.date_validite.before(new Date())){
				
				this.errors.add(Messages.get("error_date_validite_carte"));
			}
			*/
			//-----------------------------------------------------------------------------------
			
			//Autorisation banque 
			if(this.errors.isEmpty()){
				 Random random = new Random();
				 boolean  validiteB= random.nextBoolean();
				 if(!validiteB){
					 this.errors.add(Messages.get("error_banque"));
				 }
			}
			
			
			
		}
		
		
		
		
}
