package app.service;
import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Service;

import app.model.dao.FormationDaoImplementation;
import app.model.dao.MatiereDao;
import app.model.dao.MatiereDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Cours;
import app.model.entity.Matiere;
@Service

public class MatiereServiceImplementation implements MatiereService {
	Connection connection;
	MatiereDao matiereDao;

	public MatiereServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		matiereDao=new MatiereDaoImplementation(connection);
		
	}


	@Override
	public int add(Matiere matiere) {
		// TODO Auto-generated method stub
		return matiereDao.add(matiere);
	}

	@Override
	public Integer addGetId(Matiere matiere) {
		// TODO Auto-generated method stub
		return matiereDao.addGetId(matiere);
	}


	@Override
	public boolean update(Matiere matiere) {
		// TODO Auto-generated method stub
		return matiereDao.update(matiere);
	}


	@Override
	public boolean delete(Matiere matiere) {
		// TODO Auto-generated method stub
		return matiereDao.delete(matiere);
	}


	@Override
	public Matiere get(int id) {
		// TODO Auto-generated method stub
		return matiereDao.get(id);
	}


	@Override
	public List<Matiere> getAll() {
		// TODO Auto-generated method stub
		return matiereDao.getAll();
	}


	@Override
	public List<Matiere> getByName(String nom) {
		// TODO Auto-generated method stub
		return matiereDao.getByName(nom);
	}


	@Override
	public Matiere getByCours(Cours cours) {
		// TODO Auto-generated method stub
		return matiereDao.getByCours(cours);
	}


	

}
