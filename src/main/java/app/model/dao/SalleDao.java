package app.model.dao;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Salle;

public interface SalleDao {
	public int add(Salle sall);
	public Integer addGetId(Salle sall);
	public boolean update(Salle sall);
	public boolean delete(Salle sall);
	public Salle get(int id);
	public List<Salle> getAll();
	public Salle getByCours(Cours cours);
}
