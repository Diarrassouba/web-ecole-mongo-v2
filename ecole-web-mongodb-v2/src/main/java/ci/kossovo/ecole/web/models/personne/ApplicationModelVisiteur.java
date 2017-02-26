package ci.kossovo.ecole.web.models.personne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ci.kossovo.ecole.entity.Visiteur;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;
import ci.kossovo.ecole.metier.IVisiteur;

@Component
public class ApplicationModelVisiteur implements IVisiteur {
	
	@Autowired
	private IVisiteur visiteurMetier;

	@Override
	public Visiteur creer(Visiteur entity) throws InvalidPersonneException {
		return visiteurMetier.creer(entity);
	}

	@Override
	public Visiteur modifier(Visiteur entity) throws InvalidPersonneException {
		return visiteurMetier.modifier(entity);
	}

	@Override
	public Visiteur find(String id) {
		return visiteurMetier.find(id);
	}

	@Override
	public List<Visiteur> findAll() {
		return visiteurMetier.findAll();
	}

	@Override
	public void spprimer(List<Visiteur> entities) {
visiteurMetier.spprimer(entities);
	}

	@Override
	public boolean supprimer(String id) {
		return visiteurMetier.supprimer(id);
	}

	@Override
	public boolean existe(String id) {
		return visiteurMetier.existe(id);
	}

	@Override
	public Long compter() {
		return visiteurMetier.compter();
	}

	@Override
	public Visiteur chercherParIdentifiantS(String numCni) {
		return visiteurMetier.chercherParIdentifiantS(numCni);
	}

	@Override
	public List<Visiteur> chercherParRaison(String raison) {
		return visiteurMetier.chercherParRaison(raison);
	}

	@Override
	public List<Visiteur> chercherVisiteurParMc(String mc) {
		return visiteurMetier.chercherVisiteurParMc(mc);
	}

}
