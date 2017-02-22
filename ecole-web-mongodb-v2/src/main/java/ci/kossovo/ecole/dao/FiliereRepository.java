package ci.kossovo.ecole.dao;



import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ecole.entity.Filiere;

public interface FiliereRepository extends MongoRepository<Filiere, String> {

}
