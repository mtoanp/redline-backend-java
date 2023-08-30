package app.service;

import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Etudiant;
import app.model.entity.Formation;
import app.model.entity.Session;

public interface SessionService {
	public int add(Session session);
	public Integer addGetId(Session session);
	public boolean update(Session session);
	public boolean delete(Session session);
	public Session get(int id);
	public List<Session> getAll();
	public List<Session> getByFormation(Formation formation);
	public Session getWithDetails(int id);
	public Session getByCours(Cours cours);
	public boolean addEtudiant(Session session, Etudiant etudiant);
	public boolean removeEtudiant(Session session, Etudiant etudiant);
}
