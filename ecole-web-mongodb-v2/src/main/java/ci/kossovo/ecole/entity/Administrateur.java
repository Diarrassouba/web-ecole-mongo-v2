package ci.kossovo.ecole.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrateur extends Personne{
	private static final long serialVersionUID = 1L;
	
	private String fonction;

    public Administrateur() {

    }

   

	public Administrateur(String titre, Adresse adresse, String nom, String prenom, String fonction) {
		super(titre, adresse, nom, prenom);
		this.fonction = fonction;
	}



	public Administrateur(String titre, String nom, String prenom, String fonction) {
		super(titre, nom, prenom);
		this.fonction = fonction;
	}

	


	public Administrateur(String titre, String nom, String prenom, String numCni, String fonction) {
		super(titre, nom, prenom, numCni);
		this.fonction = fonction;
	}



	@Override
	public String toString() {
		return String.format("Administrateur[%s]", super.toString());
	}



	public String getFonction() {
        return this.fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
}
