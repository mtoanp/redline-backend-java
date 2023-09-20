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
//		List<Cours> coursListDetails = new ArrayList<Cours>();
		int index = 0;
		for(Cours c : coursList) {
			c.setSession(sessionDao.get(c.getIdSession()));
			c.setProfesseur(professeurDao.get(c.getIdProfesseur()));
			c.setMatiere(matiereDao.get(c.getIdMatiere()));
			c.setSalle(salleDao.get(c.getIdSalle()));
			coursList.set(index++, c);
//			coursListDetails.add(c);
		}
		session.setCoursList(coursList);
		return session;
	}

	@Override
	public boolean addCandidature(Session session, Etudiant etudiant) {
        return sessionDao.addCandidature(session, etudiant);
	}

	@Override
	public boolean removeCandidature(Session session, Etudiant etudiant) {
        return sessionDao.removeCandidature(session, etudiant);
	}

	@Override
	public boolean acceptCandidature(Session session, Etudiant etudiant) {
		Candidature candidature = sessionDao.getCandidature(session, etudiant);
		if(candidature != null)
			return sessionDao.acceptCandidature(candidature);
		else
			return false;
	}

	@Override
	public boolean denyCandidature(Session session, Etudiant etudiant) {
		Candidature candidature = sessionDao.getCandidature(session, etudiant);
		if(candidature != null)
			return sessionDao.denyCandidature(candidature);
		else
			return false;
	}
	
}
