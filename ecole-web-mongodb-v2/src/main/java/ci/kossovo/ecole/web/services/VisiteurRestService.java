package ci.kossovo.ecole.web.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.kossovo.ecole.entity.Adresse;
import ci.kossovo.ecole.entity.Personne;
import ci.kossovo.ecole.entity.Visiteur;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;
import ci.kossovo.ecole.web.models.Reponse;
import ci.kossovo.ecole.web.models.personne.ApplicationModelVisiteur;
import ci.kossovo.ecole.web.models.personne.PostAjoutVisiteur;
import ci.kossovo.ecole.web.models.personne.PostModifVisiteur;
import ci.kossovo.ecole.web.utilitaires.Static;

@CrossOrigin
@RestController
public class VisiteurRestService {
	@Autowired
	private ApplicationModelVisiteur modelVisiteur;

	
	@Autowired
	private ObjectMapper jsonMapper;

	// Methode local
	private Reponse<Visiteur> getVisteur(String id) {
		// On recupère la personne
		Visiteur visit = null;
		try {
			visit = modelVisiteur.find(id);
		} catch (RuntimeException e) {
			return new Reponse<>(1, Static.getErreurforexception(e), null);
		}

		// personne existe?
		if (visit == null) {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("Le visiteur [%s] n'existe pas", id));
			return new Reponse<Visiteur>(2, messages, null);
		}
		// OK
		return new Reponse<Visiteur>(0, null, visit);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Créé une personne
	@PostMapping("/visiteurs")
	public String creer(@RequestBody PostAjoutVisiteur v) throws JsonProcessingException {
		Reponse<Visiteur> reponse;
		Visiteur entity = new Visiteur(v.getTitre(), v.getNom(), v.getPrenom(), v.getNumCni(),v.getRaison());
		Adresse ad = new Adresse(v.getQuartier(), v.getCodePostal(), v.getEmail());

		entity.setAdresse(ad);
		entity.setNomComplet();

		try {
			reponse = new Reponse<Visiteur>(0, null, modelVisiteur.creer(entity));
		} catch (InvalidPersonneException e) {
			reponse = new Reponse<Visiteur>(1, Static.getErreurforexception(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	/////////////////////////////////////////////
	// Modifier une personne
	@PutMapping("/visiteurs")
	public String modifier(@RequestBody PostModifVisiteur v) throws JsonProcessingException {
		Reponse<Visiteur> reponse;
		Reponse<Visiteur> reponseModif = getVisteur(v.getId());
		Visiteur entity = reponseModif.getBody();
		entity.setTitre(v.getTitre());
		entity.setNom(v.getNom());
		entity.setPrenom(v.getPrenom());
		entity.setNumCni(v.getNumCni());
		Adresse ad = new Adresse(v.getQuartier(), v.getCodePostal(), v.getEmail());
		entity.setAdresse(ad);
		entity.setNomComplet();

		try {
			reponse = new Reponse<Visiteur>(0, null, modelVisiteur.modifier(entity));
		} catch (InvalidPersonneException e) {
			reponse = new Reponse<Visiteur>(1, Static.getErreurforexception(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	
	
	///////////////////////////////////////////

	// Trouver une visiteur par son id
	@GetMapping("/visiteurs/{id}")
	public String find(@PathVariable("id") String id) throws JsonProcessingException {
		Reponse<Visiteur> reponse;
		reponse = getVisteur(id);
		return jsonMapper.writeValueAsString(reponse);
	}

	
	//////////////////////////////////////////
	// La liste des visiteurs.
	@GetMapping("/visiteurs")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Visiteur>> reponse = null;
		try {
			List<Visiteur> visiteurTous = modelVisiteur.findAll();
	
			if (!visiteurTous.isEmpty()) {
				reponse = new Reponse<List<Visiteur>>(0, null, visiteurTous);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de visiteurs enregistrées à ce jour");
				reponse = new Reponse<List<Visiteur>>(3, messages, null);
			}
		} catch (RuntimeException e) {
			reponse = new Reponse<List<Visiteur>>(1, Static.getErreurforexception(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	public void spprimer(List<Visiteur> entities) {
		modelVisiteur.spprimer(entities);
	}

	///////////////////////////////////////////////////////////////
	// supprimer un visiteur
	@DeleteMapping("/visiteurs/{id}")
	public String supprimer(@PathVariable("id") String id) throws JsonProcessingException {
		// La reponse
		Reponse<Boolean> reponse = null;
		boolean erreur = false;

		// On recupère le visiteur
		if (!erreur) {
			Reponse<Visiteur> reponseVisits = getVisteur(id);
			if (reponseVisits.getStatus() != 0) {
				reponse = new Reponse<>(reponseVisits.getStatus(), reponseVisits.getMessages(), null);
				erreur = true;
			}
		}
		//visiteur existe donc
		if (!erreur) {
			// Suppression
			try {
				reponse = new Reponse<>(0, null, modelVisiteur.supprimer(id));
			} catch (RuntimeException e) {
				reponse = new Reponse<>(3, Static.getErreurforexception(e), null);
			}

		}
		// La reponse
		return jsonMapper.writeValueAsString(reponse);
	}

	////////////////////////////////////////////
	public boolean existe(String id) {
		return modelVisiteur.existe(id);
	}
	
	

	///////////////////////////////////////////
	public Long compter() {
		return modelVisiteur.compter();
	}

	

	//////////////////////////////////////////
	public Personne chercherParIdentifiantS(String numCni) {
		return modelVisiteur.chercherParIdentifiantS(numCni);
	}

	
	///////////////////////////////////////////
	public List<Visiteur> chercherParRaison(String raison) {
		return modelVisiteur.chercherParRaison(raison);
	}
	

}
