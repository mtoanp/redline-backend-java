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
	boolean addCandidat(Session session, Etudiant etudiant);
	boolean removeCandidat(Session session, Etudiant etudiant);
	Candidature getCandidature(Session session, Etudiant etudiant);
	boolean addEtudiant(Candidature candidature);
	boolean removeEtudiant(Candidature candidature);
}
