package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Etudiant;
import app.model.entity.Session;

public class EtudiantDaoImplementation implements EtudiantDao {

	private Connection connection;
	
	public EtudiantDaoImplementation(Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public int add(Etudiant etudiant) {

		String query = "INSERT INTO etudiant(nom, prenom) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, etudiant.getNom());
			statement.setString(2, etudiant.getPrenom());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public Integer addGetId(Etudiant etudiant) {
		
		String query = "INSERT INTO etudiant(nom, prenom) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, etudiant.getNom());
			statement.setString(2, etudiant.getPrenom());
		    if (statement.executeUpdate() > 0) {
		        ResultSet generatedKeys = statement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            return generatedKeys.getInt(1);
		        }
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public boolean update(Etudiant etudiant) {
		String query = "UPDATE etudiant SET nom = ?, prenom = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, etudiant.getNom());
			statement.setString(2, etudiant.getPrenom());
			statement.setInt(3, etudiant.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(Etudiant etudiant) {
		String query = "DELETE FROM etudiant WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, etudiant.getId());
			if(statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Etudiant get(int id) {
		String query = "SELECT * FROM etudiant WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Etudiant etudiant = new Etudiant(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom")
	            );
				return etudiant;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Etudiant> getAll() {
		List<Etudiant> etudiants = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM etudiant";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Etudiant etudiant = new Etudiant(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom")
	            );
	            etudiants.add(etudiant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etudiants;
	}

	
	public List<Etudiant> getBySession(Session session) {
		List<Etudiant> etudiants = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
//			String query = "SELECT * FROM etudiant WHERE id_session = " + id;
			String query = "SELECT * FROM etudiant e "
							+ " LEFT JOIN session_etudiant es"
							+ " ON e.id = es.id_etudiant"
							+ " WHERE es.id_session = " + session.getId() 
							+ " AND es.valide IS true";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Etudiant etudiant = new Etudiant(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom")
	            );
	            etudiants.add(etudiant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etudiants;
	}
	
	public List<Etudiant> getCandidatsBySession(Session session) {
		List<Etudiant> etudiants = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM etudiant e "
							+ " LEFT JOIN session_etudiant es"
							+ " ON e.id = es.id_etudiant"
							+ " WHERE es.id_session = " + session.getId() 
							+ " AND es.valide IS false";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Etudiant etudiant = new Etudiant(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom")
	            );
	            etudiants.add(etudiant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etudiants;
	}
	
}
