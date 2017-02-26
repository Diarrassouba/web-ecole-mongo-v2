package ci.kossovo.ecole.metier;

import java.util.List;

import ci.kossovo.ecole.entity.Visiteur;

public interface IVisiteur extends IMetier<Visiteur, String> {

	public Visiteur chercherParIdentifiantS(String numCni);
	public List<Visiteur> chercherParRaison(String raison);
	public List<Visiteur> chercherVisiteurParMc(String mc);
}
