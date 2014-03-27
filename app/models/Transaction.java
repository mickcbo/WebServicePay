package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "transaction")
public class Transaction extends GenericModel {
	@Id
	@Column
	@GeneratedValue
	Integer id;

	@Column
	String id_commande;

	@Column
	boolean validation;

	@Column
	Date date_transaction;

	@Column
	String nom;

	@Column
	String prenom;

	public Transaction(String id_commande, boolean validation,
			Date date_transaction, String nom, String prenom) {
		super();
		this.id_commande = id_commande;
		this.validation = validation;
		this.date_transaction = date_transaction;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getId_commande() {
		return id_commande;
	}

	public void setId_commande(String id_commande) {
		this.id_commande = id_commande;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public Date getDate_transaction() {
		return date_transaction;
	}

	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
