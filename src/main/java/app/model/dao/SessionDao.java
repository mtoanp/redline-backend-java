package app.model.dao;

import java.util.List;

import app.model.entity.*;

public interface SessionDao {
	int add(Session session);
	Integer addGetId(Session session);
	boolean update(Session session);
	boolean delete(Session session);
	Session get(int id);
	List<Session> getAll();
	List<Session> getByFormation(Formation formation);
	Session getByCours(Cours cours);
	Candidature getCandidature(Candidature candidature);
	boolean addCandidature(Candidature candidature);
	boolean removeCandidature(Candidature candidature);
	boolean updateCandidature(Candidature candidature);

	List<Candidature> getCandidaturesBySession(int idSession);
}
