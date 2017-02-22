package ci.kossovo.ecole.metier;

import java.util.List;

import ci.kossovo.ecole.entity.Etudiant;
import ci.kossovo.ecole.entity.Personne;

public interface IEtudiantMetier extends IMetier<Etudiant, String> {
	
	public Etudiant chercherParMatricule(String matricule);
	public Etudiant chercherParIdentifiantS(String numCni);
	public List<Etudiant> chercherParStatus(String status);
	public List<Etudiant> chercherParFonction(String fonction);
	public List<Etudiant> chercherEtudiantParMc(String mc);
	
	

}
