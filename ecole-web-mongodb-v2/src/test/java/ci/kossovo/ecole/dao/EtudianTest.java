package ci.kossovo.ecole.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import ci.kossovo.ecole.entity.Etudiant;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EtudianTest {
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	  @Before
	    public void setUp() {

	        etudiantRepository.deleteAll();

	        
	    }

	@Test
	public void findAl() {
		Etudiant e=new Etudiant("Mme", "Koné", "Asta", "E00101");
		e.setNomComplet();
		etudiantRepository.save(e);
		Etudiant e1=new Etudiant("Mme", "Koné", "Asta","E00103");
		e1.setNomComplet();
		etudiantRepository.save(e1);
		Etudiant e3=new Etudiant("Mme", "Diabaté", "Mawa", "E00102");
		e3.setNomComplet();
		etudiantRepository.save(e3);

		List<Etudiant> ps = etudiantRepository.findAll();
		
		assertThat(ps.size()).isEqualTo(3);
		assertThat(ps.get(0).getNomComplet()).isEqualTo("Koné Asta");

	}

	


	
	@Test
	public void deleteAll() {
		etudiantRepository.save(new Etudiant("Mme", "Koné", "Asta","E00105"));
		etudiantRepository.save(new Etudiant("Mme", "Diabaté", "Mawa", "E00102"));
		List<Etudiant> ets = etudiantRepository.findAll();
		etudiantRepository.delete(ets);
		List<Etudiant> ets1 = etudiantRepository.findAll();
		assertThat(ets1).isEmpty();
		
	}
			
	@Test
	public void findByNomCompletRegexAndfindByNomCompletContainingIgnoreCase() {
		Etudiant p=new Etudiant("Mme", "Koné", "Asta","E00105");
		p.setNomComplet();
		etudiantRepository.save(p);
		
		Etudiant e=new Etudiant("Mme", "Diabaté", "Mawa", "E00102");
		e.setNomComplet();
		etudiantRepository.save(e);
		
		Etudiant ens=new Etudiant("Mme", "Koné", "Asta", "E00109");
		ens.setNomComplet();
		etudiantRepository.save(ens);
		
		List<Etudiant> ets1 = etudiantRepository.findByNomCompletContainingIgnoreCase("ko");
		assertThat(ets1.size()).isEqualTo(2);
		
		List<Etudiant> ets2 = etudiantRepository.findByNomCompletRegex("Dia");
		assertThat(ets2.size()).isEqualTo(1);
		Etudiant e1 = ets2.get(0);
		assertThat(e1.getNom()).isEqualTo("Diabaté");

	}
	
	
	
	
	

	@Test
	public void findByNom() {
		Etudiant ad=new Etudiant("Mme", "Asta", "Koné", "E00109");
		ad.setNomComplet();
		etudiantRepository.save(ad);
		Etudiant ens=new Etudiant("Mme", "Koné", "Asta", "E00108");
		ens.setNomComplet();
		etudiantRepository.save(ens);
		List<Etudiant> pers = etudiantRepository.findByNomIgnoreCase("koné");
		assertThat(pers.size()).isEqualTo(1);
		Etudiant p = pers.get(0);
		assertThat(p.getNom()).isEqualTo("Koné");
	}
	
	@Test
	public void findByNomAndPrenomIgnoreCase() {
		Etudiant ad=new Etudiant("Mr", "Kaba", "Amara", "E00108");
		ad.setNomComplet();
		etudiantRepository.save(ad);
		Etudiant ens=new Etudiant("Mme", "Koné", "Asta", "E00107");
		ens.setNomComplet();
		etudiantRepository.save(ens);
		
		List<Etudiant> pers = etudiantRepository.findByNomIgnoreCaseAndPrenomIgnoreCase("KABA", "AMARA");
		assertThat(pers.size()).isEqualTo(1);
		Etudiant p = pers.get(0);
		assertThat(p.getNomComplet()).isEqualTo("Kaba Amara");
	}
	
	@Test
	public void findAllAndfindOneAndsave() {
		etudiantRepository.save(new Etudiant("Mme", "Koné", "Asta","E00106"));
		etudiantRepository.save(new Etudiant("Mme", "Diabaté", "Mawa", "E00102"));
		etudiantRepository.save(new Etudiant("Mme", "Koné", "Asta", "E00103"));
		List<Etudiant> ets1 = etudiantRepository.findAll();
		assertThat(ets1.size()).isEqualTo(3);
		String id = ets1.get(0).getId();
		Etudiant p = etudiantRepository.findOne(id);
		assertNotNull(p);
		p.setNom("Diarra");
		p = etudiantRepository.save(p);
		assertThat(p.getNom()).isEqualTo("Diarra");
		etudiantRepository.delete(id);
		assertThat(etudiantRepository.findAll().size()).isEqualTo(2);
		assertNull(etudiantRepository.findOne(id));
		assertNotNull(etudiantRepository.save(new Etudiant("Mme", "Koné", "Asta","E00105")));
		assertThat(etudiantRepository.findAll().size()).isEqualTo(3);
	}
	
	@Test
	public void findByNumCni(){
		Etudiant p1=new Etudiant("Mlle", "Camara", "Moussa", "CN00210045");
		p1.setNomComplet();
		etudiantRepository.save(p1);
		Etudiant e=new Etudiant("Mr", "Gondo", "Jules", "CN00210050", "E00108");
		e.setNomComplet();
		etudiantRepository.save(e);
		Etudiant p2=new Etudiant("Mme", "Soro", "jean", "CN00210060");
		p2.setNomComplet();
		etudiantRepository.save(p2);
		
		Etudiant p =etudiantRepository.findByNumCni("CN00210050");
		assertNotNull(p);
		assertThat(p.getNumCni()).isEqualTo("CN00210050");
		assertThat(p.getNomComplet()).isEqualTo("Gondo Jules");
	}
	
	
	@Test
	public void findByMatricule(){
		Etudiant p1=new Etudiant("Mlle", "Camara", "Moussa", "CN00210045");
		p1.setMatricule("MA201");
		p1.setNomComplet();
		etudiantRepository.save(p1);
		Etudiant e=new Etudiant("Mr", "Gondo", "Jules", "CN00210050", "E00108");
		e.setNomComplet();
		e.setMatricule("MA202");
		etudiantRepository.save(e);
		Etudiant p2=new Etudiant("Mme", "Soro", "jean", "CN00210060");
		p2.setNomComplet();
		p2.setMatricule("MA205");
		etudiantRepository.save(p2);
		
		Etudiant p =etudiantRepository.findByMatricule("MA202");
		assertNotNull(p);
		assertThat(p.getNumCni()).isEqualTo("CN00210050");
		assertThat(p.getNomComplet()).isEqualTo("Gondo Jules");
	}


}
