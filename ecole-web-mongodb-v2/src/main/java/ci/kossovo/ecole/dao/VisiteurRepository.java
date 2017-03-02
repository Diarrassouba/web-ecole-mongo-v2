package ci.kossovo.ecole.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ecole.entity.Visiteur;

public interface VisiteurRepository extends MongoRepository<Visiteur, String> {
	
	List<Visiteur> findByRaison(String raison);
	Visiteur findByNumCni(String numcni);
	List<Visiteur> findByNomCompletRegex(String nomcomplet);
	List<Visiteur> findByNomCompletContainingIgnoreCase(String mc);
	List<Visiteur> findByNomIgnoreCase(String nom);

}
