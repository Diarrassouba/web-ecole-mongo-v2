package ci.kossovo.ecole.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.entity.Personne;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {

	List<Etudiant> findByNomCompletContainingIgnoreCase(String mc);

	List<Etudiant> findByNomIgnoreCase(String nom);

	List<Etudiant> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

	List<Etudiant> findByTypeAndNomAndPrenom(String type, String nom, String prenom);
	
	Etudiant findByNumCni(String numcni);
}
