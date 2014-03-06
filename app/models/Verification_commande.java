package models;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;

import play.i18n.Messages;

public class Verification_commande 
{
		
		private String prenom;
		
		
		private String nom;
		
		
		private Integer cryptogramme;
		
		
		private Integer num_carte;
		
		
		private  Date date_validite;
		
		
		private List<String> errors;
		
		private boolean commande_valide;
		

		public Verification_commande(String prenom, String nom,
				Integer cryptogramme, Integer num_carte, Date date_validite) {
			super();
			this.prenom = prenom;
			this.nom = nom;
			this.cryptogramme = cryptogramme;
			this.num_carte = num_carte;
			this.date_validite = date_validite;
			this.Verification_bancaire();
		}

		public Integer getCryptogramme() {
			return cryptogramme;
		}

		public void setCryptogramme(Integer cryptogramme) {
			this.cryptogramme = cryptogramme;
		}

		public Integer getNum_carte() {
			return num_carte;
		}

		public void setNum_carte(Integer num_carte) {
			this.num_carte = num_carte;
		}

		public Date getDate_validite() {
			return date_validite;
		}

		public void setDate_validite(Date date_validite) {
			this.date_validite = date_validite;
		}
		
		
		
		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public List<String> getErrors() {
			return errors;
		}

		
		private void Verification_bancaire()
		{
			boolean validation_bancaire=true;
			//nom et prenom
			if(nom.isEmpty() || prenom.isEmpty()){
				errors.add(Messages.get("error_nom_prenom"));
				validation_bancaire=false;
			}
			
			//numero de carte 
			
			String sNumcart=String.valueOf(this.num_carte);
			if(sNumcart.length()<14 ||sNumcart.length()>16){
				this.errors.add(Messages.get("error_Numero_carte_bleu"));
				validation_bancaire=false;
			}
			
			
			//cryptogramme
			String sCryptogramme=String.valueOf(this.cryptogramme);
			if(sCryptogramme.length()==3){
				this.errors.add(Messages.get("error_cryptogramme"));
				validation_bancaire=false;
			}
			
			
			//Date de validité
			if(this.date_validite.before(new Date())){
				this.errors.add(Messages.get("error_date_validite_carte"));
				validation_bancaire=false;
			}
			this.commande_valide=validation_bancaire;
			//Autorisation banque 
			if(validation_bancaire){
				 Random random = new Random();
				 boolean  validiteB= random.nextBoolean();
				 if(!validiteB){
					this.commande_valide=validiteB;
					 this.errors.add(Messages.get("error_banque"));
				 }
			}
			
			
			
			
		}
		
		
		
		
}
