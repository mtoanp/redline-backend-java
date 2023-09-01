package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.EtudiantDao;
import app.model.dao.EtudiantDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Etudiant;
import app.model.entity.Session;

@Service
public class EtudiantServiceImplementation implements EtudiantService {

	EtudiantDao etudiantDao;
	Connection connection;
	
	public EtudiantServiceImplementation() {
		Connection connection = ConnectionDatabase.getConnection();
		etudiantDao = new EtudiantDaoImplementation(connection);
	}
	
	public int add(Etudiant etudiant) {
		return etudiantDao.add(etudiant);
	}
	
	public boolean delete(Etudiant etudiant) {
		return etudiantDao.delete(etudiant);
	}
	
	public Etudiant get(int id) {
		return etudiantDao.get(id);
	}
	
	public List<Etudiant> getAll() {
		return etudiantDao.getAll();
	}
	
	public boolean update(Etudiant etudiant) {
		return etudiantDao.update(etudiant);
	}
	
	public Integer addGetId(Etudiant etudiant) {
		return etudiantDao.addGetId(etudiant);
	}
	
	public List<Etudiant> getBySession(Session session) {
		return etudiantDao.getBySession(session);
	}

	@Override
	public List<Etudiant> getBySessionNoValide(Session session) {
		return etudiantDao.getBySession(session);
	}
	
}
