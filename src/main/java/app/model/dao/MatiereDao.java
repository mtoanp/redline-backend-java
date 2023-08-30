package app.model.dao;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Matiere;

public interface MatiereDao {
	public int add(Matiere matiere);
	public Integer addGetId(Matiere matiere);
	public boolean update(Matiere matiere);
	public boolean delete(Matiere matiere);
	public Matiere get(int id);
	public List<Matiere> getAll();
	public List<Matiere> getByName(String nom);
	public Matiere getByCours(Cours cours);
}
