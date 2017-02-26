package ci.kossovo.ecole.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Visiteur extends Personne {
	private static final long serialVersionUID = 1L;
	
	private String raison;

	public Visiteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Visiteur(String titre, String nom, String prenom, String numCni, String raison) {
		super(titre, nom, prenom, numCni);
		this.raison = raison;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	

}
