package ci.kossovo.ecole.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Etudiant extends Personne {
	private static final long serialVersionUID = 1L;

	private String matricule;
	private Date dateNaissance;
	private String photo;
	private String status;

	public Etudiant() {

	}

	public Etudiant(String titre, Adresse adresse, String nom, String prenom, String matricule, Date dateNaissance,
			String photo, String status) {
		super(titre, adresse, nom, prenom);
		this.matricule = matricule;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
		this.status = status;
	}

	public Etudiant(String matricule, Date dateNaissance, String photo, String status) {
		super();
		this.matricule = matricule;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
		this.status = status;
	}

	public Etudiant(String titre, String nom, String prenom, String matricule) {
		super(titre, nom, prenom);
		this.matricule = matricule;
	}
	
	

	public Etudiant(String titre, String nom, String prenom, String numCni, String matricule) {
		super(titre, nom, prenom, numCni);
		this.matricule = matricule;
	}

	@Override
	public String toString() {
		return String.format("Etudiant[%s]", super.toString());
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
