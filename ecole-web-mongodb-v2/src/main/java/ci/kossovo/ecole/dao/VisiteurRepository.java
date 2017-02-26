package ci.kossovo.ecole.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ecole.entity.Visiteur;
import java.lang.String;
import java.util.List;

public interface VisiteurRepository extends MongoRepository<Visiteur, String> {
	
	List<Visiteur> findByRaison(String raison);
	Visiteur findByNumCni(String numcni);
	List<Visiteur> findByNomCompletRegex(String nomcomplet);

}
