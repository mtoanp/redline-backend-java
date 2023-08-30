package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.FormationDao;
import app.model.dao.FormationDaoImplementation;
import app.model.dao.SessionDao;
import app.model.dao.SessionDaoImplementation;
import app.model.dao.ThemeDao;
import app.model.dao.ThemeDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Formation;
import app.model.entity.Session;
import app.model.entity.Theme;

@Service
public class FormationServiceImplementation implements FormationService {
	Connection connection;
	ThemeDao themeDao;
	FormationDao formationDao;
	SessionDao sessionDao;
	
	
	public FormationServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		themeDao = new ThemeDaoImplementation(connection);
		formationDao = new FormationDaoImplementation(connection);
		sessionDao = new SessionDaoImplementation(connection);
	}

	@Override
	public int add(Formation formation) {
		return formationDao.add(formation);
	}

	@Override
	public Integer addGetId(Formation formation) {
		return formationDao.addGetId(formation);
	}

	@Override
	public boolean update(Formation formation) {
		return formationDao.update(formation);
	}

	@Override
	public boolean delete(Formation formation) {
		return formationDao.delete(formation);
	}

	@Override
	public Formation get(int id) {
		return formationDao.get(id);
	}

	@Override
	public List<Formation> getAll() {
		return formationDao.getAll();
	}

	@Override
	public List<Formation> getByName(String nom) {
		return formationDao.getByName(nom);
	}

	@Override
	public Formation getBySession(Session session) {
		return formationDao.getBySession(session);
	}

	@Override
	public List<Formation> getByTheme(Theme theme) {
		return formationDao.getByTheme(theme);
	}
	
	@Override
	public Formation getWithDetails(int id) {
		Formation formation = formationDao.get(id);
		List<Session> sessions = sessionDao.getByFormation(formation);
		formation.setThemes(themeDao.getByFormation(formation));
		formation.setSessions(sessions);
		return formation;
	}
	
	@Override
	public boolean addTheme(Formation formation, Theme theme) {
		if(formationDao.addTheme(formation, theme))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean removeTheme(Formation formation, Theme theme) {
		if(formationDao.removeTheme(formation, theme))
			return true;
		else
			return false;
	}

	@Override
	public boolean addSession(Formation formation, Session session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSession(Formation formation, Session session) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
