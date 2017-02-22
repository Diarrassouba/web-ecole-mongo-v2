package ci.kossovo.ecole.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.kossovo.ecole.dao.EtudiantRepository;
import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;

@Service
public class EtudiantMetierImpl implements IEtudiantMetier {

	@Autowired
	private EtudiantRepository etudiantRepository;
	

	@Override
	public Etudiant creer(Etudiant entity) throws InvalidPersonneException {
		if (entity.getNom()=="" || entity.getPrenom()=="" || entity.getNumCni()=="") {
			throw new InvalidPersonneException("Le nom, prenom ou numCni ne peut etre null");
		};

		Etudiant p = etudiantRepository.findByNumCni(entity.getNumCni());
		if (p != null)
			throw new InvalidPersonneException("Cette etudiant existe dejà.");

		return etudiantRepository.save(entity);
	}

	@Override
	public Etudiant modifier(Etudiant entity) throws InvalidPersonneException {
		Etudiant p = etudiantRepository.findByNumCni(entity.getNumCni());
		if (p!=null && !entity.getId().equals(p.getId())) {
			
				throw new InvalidPersonneException("Cet indentifiant cni existe dejà.");
		}

		return etudiantRepository.save(entity);
	}

	@Override
	public Etudiant find(String id) {
		return etudiantRepository.findOne(id);
	}

	@Override
	public List<Etudiant> findAll() {
		return etudiantRepository.findAll();
	}
	
	
	@Override
	public void spprimer(List<Etudiant> entities) {
		etudiantRepository.delete(entities);

	}

	@Override
	public boolean supprimer(String id) {
		etudiantRepository.delete(id);
		return true;
	}

	@Override
	public boolean existe(String id) {
		return etudiantRepository.exists(id);
	}

	@Override
	public Long compter() {
		return etudiantRepository.count();
	}


	@Override
	public Etudiant chercherParIdentifiantS(String numCni) {
		return etudiantRepository.findByNumCni(numCni);
	}

	@Override
	public Etudiant chercherParMatricule(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> chercherParStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> chercherParFonction(String fonction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> chercherEtudiantParMc(String mc) {
		// TODO Auto-generated method stub
		return null;
	}



}
