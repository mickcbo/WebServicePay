package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name="transaction")
public class Transaction extends GenericModel {
	@Id
	@Column
	@GeneratedValue
	Integer id_commande;

	@Column
	boolean validation;

	@Column
	Date date_transaction;

	@Column
	String nom;

	@Column
	String prenom;

	public Transaction(Integer id_commande, boolean validation,
			Date date_transaction, String nom, String prenom) {
		super();
		this.id_commande = id_commande;
		this.validation = validation;
		this.date_transaction = date_transaction;
		this.nom = nom;
		this.prenom = prenom;
	}

}
