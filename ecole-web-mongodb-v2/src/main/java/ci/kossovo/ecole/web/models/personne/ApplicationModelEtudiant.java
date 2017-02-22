package ci.kossovo.ecole.web.models.personne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.entity.Personne;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;
import ci.kossovo.ecole.metier.IEtudiantMetier;

@Component
public class ApplicationModelEtudiant implements IEtudiantMetier {
	@Autowired
	private IEtudiantMetier etudiantMetier;


	@Override
	public Etudiant creer(Etudiant entity) throws InvalidPersonneException {
		return etudiantMetier.creer(entity);
	}

	@Override
	public Etudiant modifier(Etudiant entity) throws InvalidPersonneException {
		return etudiantMetier.modifier(entity);
	}

	@Override
	public Etudiant find(String id) {
		return etudiantMetier.find(id);
	}

	@Override
	public List<Etudiant> findAll() {
		return etudiantMetier.findAll();
	}

	@Override
	public void spprimer(List<Etudiant> entities) {
		etudiantMetier.spprimer(entities);

	}

	@Override
	public boolean supprimer(String id) {
		return etudiantMetier.supprimer(id);
	}

	@Override
	public boolean existe(String id) {
		return etudiantMetier.existe(id);
	}

	@Override
	public Long compter() {
		return etudiantMetier.compter();
	}

	@Override
	public Etudiant chercherParMatricule(String matricule) {
		return etudiantMetier.chercherParMatricule(matricule);
	}

	@Override
	public Etudiant chercherParIdentifiantS(String numCni) {
		return etudiantMetier.chercherParIdentifiantS(numCni);
	}

	@Override
	public List<Etudiant> chercherParStatus(String status) {
		return etudiantMetier.chercherParStatus(status);
	}

	@Override
	public List<Etudiant> chercherParFonction(String fonction) {
		return etudiantMetier.chercherParFonction(fonction);
	}

	@Override
	public List<Etudiant> chercherEtudiantParMc(String mc) {
		return etudiantMetier.chercherEtudiantParMc(mc);
	}

	


	

}
