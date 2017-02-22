package ci.kossovo.ecole.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Enseignant extends Personne {
	private static final long serialVersionUID = 1L;

	private String status;

	public Enseignant() {

	}

	public Enseignant(String titre, Adresse adresse, String nom, String prenom, String status) {
		super(titre, adresse, nom, prenom);
		this.status = status;
	}

	public Enseignant(String titre, String nom, String prenom, String status) {
		super(titre, nom, prenom);
		this.status = status;
	}

	public Enseignant(String titre, String nom, String prenom, String numCni, String status) {
		super(titre, nom, prenom, numCni);
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("Enseignant[%s]", super.toString());
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
