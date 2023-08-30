package app.model.dao;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Professeur;

public interface ProfesseurDao {
	public int add(Professeur professeur);
	public Integer addGetId(Professeur professeur);
	public boolean update(Professeur professeur);
	public boolean delete(Professeur professeur);
	public Professeur get(int id);
	public List<Professeur> getAll();
	public Professeur getByCours(Cours cours);
}
