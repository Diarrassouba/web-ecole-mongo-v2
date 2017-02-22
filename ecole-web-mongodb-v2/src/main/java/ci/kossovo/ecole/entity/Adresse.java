package ci.kossovo.ecole.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String quartier;

    private List<String> contacts;

    
    private String codePostal;

    
    private String email;

    public Adresse() {

    }

    public Adresse(String quartier, String codePostal, String email) {
		super();
		this.quartier = quartier;
		this.codePostal = codePostal;
		this.email = email;
	}
    
    

	public Adresse(String quartier, List<String> contacts, String codePostal, String email) {
		super();
		this.quartier = quartier;
		this.contacts = contacts;
		this.codePostal = codePostal;
		this.email = email;
	}

	public String getQuartier() {
        return this.quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public List<String> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
