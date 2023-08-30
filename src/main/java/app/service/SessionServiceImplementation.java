package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.CoursDao;
import app.model.dao.CoursDaoImplementation;
import app.model.dao.EtudiantDao;
import app.model.dao.EtudiantDaoImplementation;
import app.model.dao.SessionDao;
import app.model.dao.SessionDaoImplementation;
import app.model.dao.ThemeDao;
import app.model.dao.ThemeDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Cours;
import app.model.entity.Etudiant;
import app.model.entity.Formation;
import app.model.entity.Session;

@Service
public class SessionServiceImplementation implements SessionService {
	Connection connection;
	ThemeDao themeDao;
	SessionDao sessionDao;
	EtudiantDao etudiantDao;
	CoursDao coursDao;
	
	public SessionServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		themeDao = new ThemeDaoImplementation(connection);
		sessionDao = new SessionDaoImplementation(connection);
		etudiantDao = new EtudiantDaoImplementation(connection);
		coursDao = new CoursDaoImplementation(connection);
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
		session.setEtudiants(etudiantDao.getBySession(session));
		session.setCoursList(coursDao.getBySession(session));
		return session;
	}
	
	@Override
	public boolean addEtudiant(Session session, Etudiant etudiant) {
		if(sessionDao.addEtudiant(session, etudiant))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean removeEtudiant(Session session, Etudiant etudiant) {
		if(sessionDao.removeEtudiant(session, etudiant))
			return true;
		else
			return false;
	}
	
}