package app.service;

import java.sql.Connection;
import java.util.List;

import app.model.dao.*;
import app.model.entity.*;
import org.springframework.stereotype.Service;

import app.model.dbConnection.ConnectionDatabase;

@Service
public class SessionServiceImplementation implements SessionService {
	Connection connection;
	ThemeDao themeDao;
	FormationDao formationDao;
	SessionDao sessionDao;
	EtudiantDao etudiantDao;
	CoursDao coursDao;
	MatiereDao matiereDao;
	ProfesseurDao professeurDao;
	SalleDao salleDao;
	
	public SessionServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		themeDao = new ThemeDaoImplementation(connection);
		formationDao = new FormationDaoImplementation(connection);
		sessionDao = new SessionDaoImplementation(connection);
		etudiantDao = new EtudiantDaoImplementation(connection);
		coursDao = new CoursDaoImplementation(connection);
		matiereDao = new MatiereDaoImplementation(connection);
		professeurDao = new ProfesseurDaoImplementation(connection);
		salleDao = new SalleDaoImplementation(connection);
	}

	@Override
	public int add(Session session) {
		return sessionDao.add(session);
	}

	@Override
	public Integer addGetId(Session session) {
		return sessionDao.addGetId(session);
	}

	@Override
	public boolean update(Session session) {
		return sessionDao.update(session);
	}

	@Override
	public Session getByCours(Cours cours) {
		return sessionDao.getByCours(cours);
	}

	@Override
	public boolean delete(Session session) {
		return sessionDao.delete(session);
	}

	@Override
	public Session get(int id) {
		return sessionDao.get(id);
	}

	@Override
	public List<Session> getAll() {
		return sessionDao.getAll();
	}

	@Override
	public List<Session> getByFormation(Formation formation) {
		return sessionDao.getByFormation(formation);
	}

	@Override
	public Session getWithDetails(int id) {
		Session session = sessionDao.get(id);
		session.setFormation(formationDao.get(session.getIdFormation()));
		session.setEtudiants(etudiantDao.getBySession(session));
		session.setCandidats(etudiantDao.getCandidatsBySession(session));
		List<Cours> coursList = coursDao.getBySession(session);
		int index = 0;
		for(Cours c : coursList) {
			c.setSession(sessionDao.get(c.getIdSession()));
			if (c.getIdProfesseur() != null)	c.setProfesseur(professeurDao.get(c.getIdProfesseur()));
			if (c.getIdMatiere() != null)		c.setMatiere(matiereDao.get(c.getIdMatiere()));
			if (c.getIdSalle() != null)			c.setSalle(salleDao.get(c.getIdSalle()));
			coursList.set(index++, c);
		}
		session.setCoursList(coursList);
		return session;
	}

	@Override
	public Candidature getCandidature(Candidature candidature) {
		return sessionDao.getCandidature(candidature);
	}
	@Override
	public boolean addCandidature(Candidature candidature) {
        return sessionDao.addCandidature(candidature);
	}

	@Override
	public boolean removeCandidature(Candidature candidature) {
        return sessionDao.removeCandidature(candidature);
	}

	@Override
	public boolean updateCandidature(Candidature candidature) {
//		System.out.println(candidature.getIdSession() + "  " +candidature.getIdEtudiant()+ "  " +candidature.isValide());
		Candidature candidatureCheck = sessionDao.getCandidature(candidature);
		if(candidatureCheck != null)
			return sessionDao.updateCandidature(candidature);
		else
			return false;
	}

	@Override
	public List<Candidature> getCandidaturesBySession(int idSession) {
		return sessionDao.getCandidaturesBySession(idSession);
	}

}
