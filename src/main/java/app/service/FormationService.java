package app.service;

import java.util.List;

import app.model.entity.Formation;
import app.model.entity.Session;
import app.model.entity.Theme;

public interface FormationService {
	public int add(Formation formation);
	public Integer addGetId(Formation formation);
	public boolean update(Formation formation);
	public boolean delete(Formation fromation);
	public Formation get(int id);
	public List<Formation> getAll();
	public List<Formation> getByName(String nom);
	public Formation getBySession(Session session);
	public List<Formation> getByTheme(Theme theme);
	public boolean addTheme(Formation formation, Theme theme);
	public boolean removeTheme(Formation formation, Theme theme);
	public boolean addSession(Formation formation, Session session);
	public boolean removeSession(Formation formation, Session session);
	public Formation getWithDetails(int id);
}
