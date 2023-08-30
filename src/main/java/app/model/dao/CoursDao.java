package app.model.dao;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Session;

public interface CoursDao {
	public int add(Cours cours);
	public Integer addGetId(Cours cours);
	public boolean update(Cours cours);
	public boolean delete(Cours cours);
	public Cours get(int id);
	public List<Cours> getAll();
	public List<Cours> getBySession(Session session);
	public List<Cours> getBySession(int id);
}
