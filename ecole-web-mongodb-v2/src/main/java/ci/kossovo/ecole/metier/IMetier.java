package ci.kossovo.ecole.metier;

import java.util.List;

import ci.kossovo.ecole.exceptions.InvalidPersonneException;

public interface IMetier<T, U> {

	public T creer(T entity) throws InvalidPersonneException;

	public T modifier(T entity) throws InvalidPersonneException;

	public T find(U id);

	public List<T> findAll();

	public void spprimer(List<T> entities);

	public boolean supprimer(U id);

	public boolean existe(U id);

	public Long compter();

}
