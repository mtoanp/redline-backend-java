package app.service;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Session;

public interface CoursService {
	int add (Cours cours);
	Integer addGetId(Cours cours);
	boolean update(Cours cours);
	boolean delete(Cours cours);
	Cours get(int id);
	List<Cours> getAll();
	List<Cours> getBySession(Session session);
	List<Cours> getBySession(int id);
}
