package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.SalleDao;
import app.model.dao.SalleDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Cours;
import app.model.entity.Salle;

@Service
public class SalleServiceImplementation implements SalleService {

	Connection connection;
	SalleDao salleDao;
	
	public SalleServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		salleDao = new SalleDaoImplementation(connection);
	}
	
	public int add(Salle sall) {
		return  salleDao.add(sall);
	}

	public Integer addGetId(Salle sall) {
		return salleDao.addGetId(sall);
	}

	public boolean update(Salle sall) {
		return salleDao.update(sall);
	}

	public boolean delete(Salle sall) {
		return salleDao.delete(sall);
	}

	public Salle get(int id) {
		return salleDao.get(id);
	}

	public List<Salle> getAll() {
		return salleDao.getAll();
	}

	public Salle getByCours(Cours cours) {
		return salleDao.getByCours(cours);
	}

}
