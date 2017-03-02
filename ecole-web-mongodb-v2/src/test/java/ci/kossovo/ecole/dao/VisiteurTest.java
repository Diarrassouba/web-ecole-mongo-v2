package ci.kossovo.ecole.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import ci.kossovo.ecole.entity.Visiteur;

@RunWith(SpringRunner.class)
@DataMongoTest
public class VisiteurTest {
	
	@Autowired
	VisiteurRepository visiteurRepository;
	
	  @Before
	    public void setUp() {

	        visiteurRepository.deleteAll();

	        
	    }

	@Test
	public void findAl() {
		Visiteur v=new Visiteur("Mme", "Koné", "Asta", "CNI32","Partenaire");
		v.setNomComplet();
		visiteurRepository.save(v);
		Visiteur e1=new Visiteur("Mme", "Koné", "Asta","CNI103","Partenaire");
		e1.setNomComplet();
		visiteurRepository.save(e1);
		Visiteur e3=new Visiteur("Mme", "Diabaté", "Mawa", "CNi102","Partenaire");
		e3.setNomComplet();
		visiteurRepository.save(e3);

		List<Visiteur> ps = visiteurRepository.findAll();
		
		assertThat(ps.size()).isEqualTo(3);
		assertThat(ps.get(0).getNomComplet()).isEqualTo("Koné Asta");

	}

	


	
	@Test
	public void deleteAll() {
		visiteurRepository.save(new Visiteur("Mme", "Koné", "Asta","CNI105","Partenaire"));
		visiteurRepository.save(new Visiteur("Mme", "Diabaté", "Mawa", "CNI102","Partenaire"));
		List<Visiteur> ets = visiteurRepository.findAll();
		visiteurRepository.delete(ets);
		List<Visiteur> ets1 = visiteurRepository.findAll();
		assertThat(ets1).isEmpty();
		
	}
	
	@Test
	public void findByNomCompletRegexAndfindByNomCompletContainingIgnoreCase() {
		Visiteur p=new Visiteur("Mme", "Koné", "Asta","E00105","Partenaire");
		p.setNomComplet();
		visiteurRepository.save(p);
		
		Visiteur e=new Visiteur("Mme", "Diabaté", "Mawa", "CNI102","Partenaire");
		e.setNomComplet();
		visiteurRepository.save(e);
		
		Visiteur ens=new Visiteur("Mme", "Koné", "Asta", "AT109","Partenaire");
		ens.setNomComplet();
		visiteurRepository.save(ens);
		
		List<Visiteur> ets1 = visiteurRepository.findByNomCompletContainingIgnoreCase("ko");
		assertThat(ets1.size()).isEqualTo(2);
		
		List<Visiteur> ets2 = visiteurRepository.findByNomCompletRegex("Dia");
		assertThat(ets2.size()).isEqualTo(1);
		Visiteur e1 = ets2.get(0);
		assertThat(e1.getNom()).isEqualTo("Diabaté");

	}
	
	
	
	
	

	@Test
	public void findByNom() {
		Visiteur ad=new Visiteur("Mme", "Asta", "Koné", "AT109","Partenaire");
		ad.setNomComplet();
		visiteurRepository.save(ad);
		Visiteur ens=new Visiteur("Mme", "Koné", "Asta", "CNI108","Partenaire");
		ens.setNomComplet();
		visiteurRepository.save(ens);
		List<Visiteur> pers = visiteurRepository.findByNomIgnoreCase("koné");
		assertThat(pers.size()).isEqualTo(1);
		Visiteur p = pers.get(0);
		assertThat(p.getNom()).isEqualTo("Koné");
	}
	
	
	
	@Test
	public void findAllAndfindOneAndsave() {
		visiteurRepository.save(new Visiteur("Mme", "Koné", "Asta","CNI106","Partenaire"));
		visiteurRepository.save(new Visiteur("Mme", "Diabaté", "Mawa", "CNI102","Partenaire"));
		visiteurRepository.save(new Visiteur("Mme", "Koné", "Asta", "AT103","Partenaire"));
		List<Visiteur> ets1 = visiteurRepository.findAll();
		assertThat(ets1.size()).isEqualTo(3);
		String id = ets1.get(0).getId();
		Visiteur p = visiteurRepository.findOne(id);
		assertNotNull(p);
		p.setNom("Diarra");
		p = visiteurRepository.save(p);
		assertThat(p.getNom()).isEqualTo("Diarra");
		visiteurRepository.delete(id);
		assertThat(visiteurRepository.findAll().size()).isEqualTo(2);
		assertNull(visiteurRepository.findOne(id));
		assertNotNull(visiteurRepository.save(new Visiteur("Mme", "Koné", "Asta","PAS0105","Partenaire")));
		assertThat(visiteurRepository.findAll().size()).isEqualTo(3);
	}
	
	
	@Test
	public void findByNumCni(){
		Visiteur p1=new Visiteur("Mlle", "Camara", "Moussa", "CN00210045","Partenaire");
		p1.setNomComplet();
		visiteurRepository.save(p1);
		Visiteur e=new Visiteur("Mr", "Gondo", "Jules", "CN00210050", "Partenaire");
		e.setNomComplet();
		visiteurRepository.save(e);
		Visiteur p2=new Visiteur("Mme", "Soro", "jean", "CN00210060","Partenaire");
		p2.setNomComplet();
		visiteurRepository.save(p2);
		
		Visiteur p =visiteurRepository.findByNumCni("CN00210050");
		assertNotNull(p);
		assertThat(p.getNumCni()).isEqualTo("CN00210050");
		assertThat(p.getNomComplet()).isEqualTo("Gondo Jules");
	}
	
	
	
	@Test
	public void findByRaison(){
		Visiteur p1=new Visiteur("Mlle", "Camara", "Moussa", "CN00210045","Partenaire");
		p1.setNomComplet();
		visiteurRepository.save(p1);
		Visiteur e=new Visiteur("Mr", "Gondo", "Jules", "CN00210050", "Partenaire2");
		e.setNomComplet();
		visiteurRepository.save(e);
		
		Visiteur p2=new Visiteur("Mme", "Soro", "jean", "CN00210060","Partenaire");
		p2.setNomComplet();
		visiteurRepository.save(p2);
		
		List<Visiteur> ps =visiteurRepository.findByRaison("Partenaire2");
		assertNotNull(ps);
		assertThat(ps.get(0).getNumCni()).isEqualTo("CN00210050");
		assertThat(ps.get(0).getNomComplet()).isEqualTo("Gondo Jules");
	}


}
