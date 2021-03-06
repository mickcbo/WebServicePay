package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import play.Logger;
import play.i18n.Messages;

public class Verification_commande {

	private String prenom;

	private String nom;

	private String cryptogramme;

	private String num_carte;

	private Date date_validite;

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
		this.errors = new ArrayList<String>();
		this.verification_bancaire();
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

	public void add_error(String erreur) {
		this.errors.add(erreur);
	}

	private void verification_bancaire() {

		// --------------------------Verification de la carte
		// -------------------------------
		// nom et prenom
		Logger.info("Verification de la carte");
		if (nom.isEmpty() || prenom.isEmpty()) {

			this.errors.add(Messages.get("error_nom_prenom"));
		}
		// numero de carte

		if (this.num_carte.length() < 14 || this.num_carte.length() > 16) {

			this.errors.add(Messages.get("error_Numero_carte_bleu"));
		}
		// cryptogramme

		if (this.cryptogramme.length() != 3) {

			this.errors.add(Messages.get("error_cryptogramme"));
		}
		// Date de validité
		if(this.date_validite!=null){
			if (this.date_validite.before(new Date())) {

				this.errors.add(Messages.get("error_date_validite_carte"));
			}
		}else{
			this.errors.add(Messages.get("error_date_validation_inexistante"));
		}
		

		// -----------------------------------------------------------------------------------
		this.verification_carte_by_banque();

	}

	/** choisi une des erreurs de la banque factice */
	private int banque_error(Integer min, Integer max) {
		int value_error;
		Random rand = new Random();
		value_error = rand.nextInt((max - min + 1) + min);

		return value_error;

	}

	private void verification_carte_by_banque() {
		Logger.info("Verification de la banque");
		// Autorisation banque
		if (this.errors.isEmpty()) {
			Random random = new Random();
			boolean acceptation_banque = random.nextBoolean();
			Logger.info("confirmation de la banque : %s", acceptation_banque);
			if (!acceptation_banque) {
				Logger.info("generation du code erreur");
				int error = banque_error(1, 8);
				Logger.info("Numero erreur banque : %s", String.valueOf(error));
				switch (error) {
				case 1:
					this.errors.add(Messages
							.get("error_banque_service_indispo"));
					break;
				case 2:
					this.errors.add(Messages
							.get("error_banque_service_indispo"));
					break;
				case 3:
					this.errors.add(Messages
							.get("error_banque_autho_depacement"));
					break;
				case 4:
					this.errors
							.add(Messages.get("error_banque_compte_cloture"));
					break;
				case 5:
					this.errors.add(Messages
							.get("error_banque_numero_carte_inconnu"));
					break;
				case 6:
					this.errors.add(Messages.get("error_banque_date_carte"));
					break;
				case 7:
					this.errors.add(Messages.get("error_banque_crypto"));
					break;
				case 8:
					this.errors.add(Messages.get("error_banque_nom_prenom"));
					break;
				}

			}
		}
	}

}
