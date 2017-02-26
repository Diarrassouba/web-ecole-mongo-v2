package ci.kossovo.ecole.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.kossovo.ecole.dao.VisiteurRepository;
import ci.kossovo.ecole.entity.Visiteur;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;

@Service
public class VisiteurMetierImpl implements IVisiteur {
	@Autowired
	VisiteurRepository visiteurRepository;

	@Override
	public Visiteur creer(Visiteur entity) throws InvalidPersonneException {
		if (entity.getNom() == "" || entity.getPrenom() == "" || entity.getNumCni() == "") {
			throw new InvalidPersonneException("Le nom, prenom ou numCni ne peut etre null");
		}
		;

		Visiteur v = visiteurRepository.findByNumCni(entity.getNumCni());
		if (v != null)
			throw new InvalidPersonneException("Cette visiteur existe dejà.");

		return visiteurRepository.save(entity);
	}

	@Override
	public Visiteur modifier(Visiteur entity) throws InvalidPersonneException {
		Visiteur p = visiteurRepository.findByNumCni(entity.getNumCni());
		if (p != null && !entity.getId().equals(p.getId())) {

			throw new InvalidPersonneException("Cet indentifiant cni existe dejà.");
		}

		return visiteurRepository.save(entity);
	}

	@Override
	public Visiteur find(String id) {
		return visiteurRepository.findOne(id);
	}

	@Override
	public List<Visiteur> findAll() {
		return visiteurRepository.findAll();
	}

	@Override
	public void spprimer(List<Visiteur> entities) {
		visiteurRepository.delete(entities);

	}

	@Override
	public boolean supprimer(String id) {
		visiteurRepository.delete(id);
		return true;
	}

	@Override
	public boolean existe(String id) {

		return visiteurRepository.exists(id);
	}

	@Override
	public Long compter() {
		return visiteurRepository.count();
	}

	@Override
	public Visiteur chercherParIdentifiantS(String numCni) {
		return visiteurRepository.findByNumCni(numCni);
	}

	@Override
	public List<Visiteur> chercherParRaison(String raison) {
		return visiteurRepository.findByRaison(raison);
	}

	@Override
	public List<Visiteur> chercherVisiteurParMc(String mc) {
		return visiteurRepository.findByNomCompletRegex(mc);
	}

}
