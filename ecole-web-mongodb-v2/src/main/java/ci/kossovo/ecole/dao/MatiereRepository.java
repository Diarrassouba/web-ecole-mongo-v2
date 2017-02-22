package ci.kossovo.ecole.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ecole.entity.Matiere;

public interface MatiereRepository extends MongoRepository<Matiere, String> {

}
