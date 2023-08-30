package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.ProfesseurDao;
import app.model.dao.ProfesseurDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Cours;
import app.model.entity.Professeur;
@Service

public class ProfesseurServiceImplementation implements ProfesseurService{
	Connection connection;
	ProfesseurDao professeurDao;  
	
	public ProfesseurServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		
		professeurDao = new ProfesseurDaoImplementation(connection);
	}

	@Override
	public int add(Professeur professeur) {
		// TODO Auto-generated method stub
		return professeurDao.add(professeur);

	}

	@Override
	public Integer addGetId(Professeur professeur) {
		// TODO Auto-generated method stub
		return professeurDao.addGetId(professeur);
	}

	@Override
	public boolean update(Professeur professeur) {
		// TODO Auto-generated method stub
		return professeurDao.update(professeur);
	}

	@Override
	public boolean delete(Professeur professeur) {
		// TODO Auto-generated method stub
		return professeurDao.delete(professeur);
	}

	@Override
	public Professeur get(int id) {
		// TODO Auto-generated method stub
		return professeurDao.get(id);
	}

	@Override
	public List<Professeur> getAll() {
		// TODO Auto-generated method stub
		return professeurDao.getAll();
	}

	@Override
	public Professeur getByCours(Cours cours) {
		// TODO Auto-generated method stub
		return professeurDao.getByCours(cours);
	}

}
