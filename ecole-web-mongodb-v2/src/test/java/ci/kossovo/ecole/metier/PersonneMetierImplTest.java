/*package ci.kossovo.ecole.metier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ci.kossovo.ecole.dao.PersonneRepository;
import ci.kossovo.ecole.entity.Enseignant;
import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.entity.Personne;
import ci.kossovo.ecole.exceptions.InvalidPersonneException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonneMetierImplTest {
	
	@Autowired
	private IPersonneMetier personneMetier;

	@MockBean
	private PersonneRepository personneRepositoryMock;

	@Test
	public void creerUnePersone() throws InvalidPersonneException {
		
		// given
		Personne p = new Personne("Mme", "Koné", "Asta", "CN00210048");
		Personne p1 = new Personne("Mme", "Koné", "Asta", "CN00210070");
		p1.setId(1L);
		
		given(personneRepositoryMock.save(p)).willReturn(p1);

		// when
		Personne ps = personneMetier.creer(p);

		// then
		verify(personneRepositoryMock).save(p);
		assertThat(ps).isEqualTo(p1);

	}

	@Test
	public void creerUnePersoneSansNomEtprenom() {
		// given
		given(personneRepositoryMock.findByNumCni(anyString())).willReturn(null);

		Personne p = new Personne();
		p.setTitre("Mr");
		given(personneRepositoryMock.save(p)).willThrow(new RuntimeException("Le nom ou le prenom ne peut etre null"));

		// when
		Personne p1 = null;
		try {
			p1 = personneMetier.creer(p);
		} catch (InvalidPersonneException e) {
			e.printStackTrace();
		}

		// then
		verify(personneRepositoryMock, never()).save(p);
		assertThat(p1).isEqualTo(null);

	}

	@Test
	public void creerUnePersoneQuiExiste() {
		// given
		Personne pex = new Personne("Mlle", "Bamba", "Kady", "CN00210045");
		pex.setId(3L);
		
		given(personneRepositoryMock.findByNumCni("CN00210045")).willReturn(pex);

		Personne p = new Personne("Mlle", "Bamba", "Kady", "CN00210045");

		// when
		Personne p1 = null;
		try {
			p1 = personneMetier.creer(p);
		} catch (InvalidPersonneException e) {
			e.printStackTrace();
		}

		// then
		verify(personneRepositoryMock).findByNumCni("CN00210045");
		verify(personneRepositoryMock, never()).save(p);
		assertThat(p1).isEqualTo(null);

	}
	

	@Test
	public void modifierPersonne() {
		// given
		Personne pex = new Personne("Mlle", "Bamba", "Kady", "CN00210045");
		pex.setId(3L);
		given(personneRepositoryMock.findOne(3L)).willReturn(pex);
		given(personneRepositoryMock.findByNumCni("CN00210045")).willReturn(pex);
		pex.setNom("Diomandé");
		given(personneRepositoryMock.save(pex)).willReturn(pex);
		
		//when
		Personne ps=null;
		try {
			 ps=personneMetier.modifier(pex);
		} catch (InvalidPersonneException e) {
			e.printStackTrace();
		}
		
		verify(personneRepositoryMock).findByNumCni("CN00210045");
		verify(personneRepositoryMock).save(pex);
		
		assertThat(ps.getNom()).isEqualTo("Diomandé");
		assertThat(ps.getNomComplet()).isEqualTo(pex.getNomComplet());
		
	}
	
	
	@Test
	public void modifierPersonneAvecNumCniExistant() {
		// given
		
		Personne entity = new Personne("Mlle", "Bamba", "Kady", "CN00840070");
		entity.setId(3L);
		Personne pex = new Personne("Mr", "Coulibaly", "Kassoum", "CN00210045");
		pex.setId(2L);
		given(personneRepositoryMock.findByNumCni("CN00210045")).willReturn(pex);
		
		entity.setNumCni("CN00210045");
		given(personneRepositoryMock.save(entity)).willThrow(new RuntimeException("Cet identifiant existe dejà."));
		
		//when
		Personne ps=null;
		try {
			ps=personneMetier.modifier(entity);
		} catch (InvalidPersonneException e) {
			e.printStackTrace();
		}
		
		verify(personneRepositoryMock).findByNumCni("CN00210045");
		verify(personneRepositoryMock,never()).save(entity);
		assertThat(ps).isEqualTo(null);
		
		
	}
	
	@Test
	public void trouverDesPersonnesParType() {
		//donnée
		Personne p1=new Personne("Mr", "Traoré", "Abdoulaye", "CNI9999901");
		p1.setId(1L);
		p1.setType("PE");
	
		Personne p2=new Etudiant("Mr", "Diarrassouba", "Drissa", "CNI888888802", "M3254");
		p2.setId(2L);
		p2.setType("ET");;
		
		Personne p3=new Enseignant("Mr", "Kouadio", "Marco", "titulaire");
		p3.setNumCni("CNI777777777777");
		p3.setId(3L);
		p3.setType("EN");
		
		List<Personne>personnes=Arrays.asList(p1,p2,p3);
		
		
		given(personneRepositoryMock.findAll()).willReturn(personnes);
		
		//when
		List<Personne>etuds=personneMetier.personneAll("ET");
		Etudiant et= (Etudiant) etuds.get(0);
		List<Personne>pers=personneMetier.personneAll("PE");
		List<Personne>ens=personneMetier.personneAll("EN");
		Enseignant en=(Enseignant)ens.get(0);
		
		
		assertNotNull(et);
		assertThat(etuds.size()).isEqualTo(1);
		assertThat(pers.size()).isEqualTo(1);
		assertThat(ens.size()).isEqualTo(1);
		assertThat(et.getMatricule()).isEqualTo("M3254");
		assertThat(pers.get(0).getNom()).isEqualTo("Traoré");
		assertThat(en.getStatus()).isEqualTo("titulaire");
		
		
		
	}

}
*/