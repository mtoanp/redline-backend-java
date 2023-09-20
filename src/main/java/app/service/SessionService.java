package app.service;

import java.util.List;

import app.model.entity.*;

public interface SessionService {
	int add(Session session);
	Integer addGetId(Session session);
	boolean update(Session session);
	boolean delete(Session session);
	Session get(int id);
	List<Session> getAll();
	List<Session> getByFormation(Formation formation);
	Session getWithDetails(int id);
	Session getByCours(Cours cours);

	Candidature getCandidature(Session session, Etudiant etudiant);
	boolean addCandidature(Session session, Etudiant etudiant);
	boolean removeCandidature(Session session, Etudiant etudiant);
	boolean acceptCandidature(Session session, Etudiant etudiant);
	boolean denyCandidature(Session session, Etudiant etudiant);
}
