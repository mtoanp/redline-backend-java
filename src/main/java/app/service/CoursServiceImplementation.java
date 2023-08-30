package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.CoursDao;
import app.model.dao.CoursDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Cours;
import app.model.entity.Session;

@Service
public class CoursServiceImplementation implements CoursService {

	CoursDao coursDao;
	Connection connection;
	
	public CoursServiceImplementation() {
		Connection connection = ConnectionDatabase.getConnection();
		coursDao = new CoursDaoImplementation(connection);
	}

	public int add(Cours cours) {
		return coursDao.add(cours);
	}

	public Integer addGetId(Cours cours) {
		return coursDao.addGetId(cours);
	}

	public boolean update(Cours cours) {
		return coursDao.update(cours);
	}

	public boolean delete(Cours cours) {
		return coursDao.delete(cours);
	}

	public Cours get(int id) {
		return coursDao.get(id);
	}

	public List<Cours> getAll() {
		return coursDao.getAll();
	}

	@Override
	public List<Cours> getBySession(Session session) {
		return coursDao.getBySession(session);
	}

	public List<Cours> getBySession(int id) {
		return coursDao.getBySession(id);
	}
	
	
}
