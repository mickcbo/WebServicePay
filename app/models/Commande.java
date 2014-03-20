package models;

import java.util.ArrayList;

public class Commande {
	Boolean valide;

	ArrayList<String> erreurs;

	public Commande(Boolean valide, ArrayList<String> erreurs) {
		super();
		this.valide = valide;
		this.erreurs = erreurs;
	}

}
