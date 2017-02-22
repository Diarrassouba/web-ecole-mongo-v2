package ci.kossovo.ecole.metier;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.kossovo.ecole.dao.PersonneRepository;
import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.entity.Personne;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;

@Service
public class PersonneMetierImpl implements IPersonneMetier {

	@Autowired
	private PersonneRepository personneRepository;
	

	@Override
	public Personne creer(Personne entity) throws InvalidPersonneException {
		if (entity.getNom()=="" || entity.getPrenom()=="" || entity.getNumCni()=="") {
			throw new InvalidPersonneException("Le nom, prenom ou numCni ne peut etre null");
		};

		Personne p = personneRepository.findByNumCni(entity.getNumCni());
		if (p != null)
			throw new InvalidPersonneException("Cette personne existe dejà.");

		return personneRepository.save(entity);
	}

	@Override
	public Personne modifier(Personne entity) throws InvalidPersonneException {
		Personne p = personneRepository.findByNumCni(entity.getNumCni());
		if (p!=null && !entity.getId().equals(p.getId())) {
			
				throw new InvalidPersonneException("Cet indentifiant cni existe dejà.");
		}

		return personneRepository.save(entity);
	}

	@Override
	public Personne find(String id) {
		return personneRepository.findOne(id);
	}

	@Override
	public List<Personne> findAll() {
		return personneRepository.findAll();
	}
	
	/*//liste de personnes par type
		@Override
		public List<Personne> personneAll(Object o) {
			List<Personne> personnes=personneRepository.findAll();
			//filtre par type de personnes
			List<Personne> typePersonnes=personnes.stream().filter(
					p-> p.).collect(Collectors.toList());
			return typePersonnes;
		}
*/
	@Override
	public void spprimer(List<Personne> entities) {
		personneRepository.delete(entities);

	}

	@Override
	public boolean supprimer(String id) {
		personneRepository.delete(id);
		return true;
	}

	@Override
	public boolean existe(String id) {
		return personneRepository.exists(id);
	}

	@Override
	public Long compter() {
		return personneRepository.count();
	}


	@Override
	public List<Personne> chercherUserParMc(String mc) {
		return personneRepository.findByNomCompletContainingIgnoreCase(mc);
	}


	@Override
	public List<Personne> userAll() {
		return personneRepository.findAll();
	}


	@Override
	public Personne chercherParIdentifiantS(String numCni) {
		return personneRepository.findByNumCni(numCni);
	}

	@Override
	public Personne chercherParMatricule(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> chercherParStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> chercherParFonction(String fonction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> chercherEtudiantParMc(String mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> chercherEnseignantParMc(String mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> chercherAdministrateurParMc(String mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> listEtudiants() {
		List<Personne> pers=personneRepository.findAll();
		List<Personne>etudiants=pers.stream().filter(p->p.getClass()==Etudiant.class).collect(Collectors.toList());
		return etudiants;
	}

	@Override
	public List<Personne> listEnseignants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> listAdministrateurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> personneAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

	/*@Override
	public List<Personne> personneAll() {
		return personneRepository.findByType("PE");
	}*/

}
