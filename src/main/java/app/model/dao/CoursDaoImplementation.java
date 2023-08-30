package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Session;

public class CoursDaoImplementation implements CoursDao {
	
	private Connection connection;

	public CoursDaoImplementation(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public int add(Cours cours) {
		String query = "INSERT INTO cours(nom, date, id_session, id_professeur, id_matiere, id_salle) "
				+ "		value(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cours.getNom());
			statement.setString(2, cours.getDate());
			statement.setInt(3, cours.getIdSession());
			statement.setInt(4, cours.getIdProfesseur());
			statement.setInt(5, cours.getIdMatiere());
			statement.setInt(6, cours.getIdSalle());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	@Override
	public Integer addGetId(Cours cours) {	
		String query = "INSERT INTO cours(nom, date, id_session, id_professeur, id_matiere, id_salle) "
				+ "		value(?, ?, ?, ?,  ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cours.getNom());
			statement.setString(2, cours.getDate());
			statement.setInt(3, cours.getIdSession());
			statement.setInt(4, cours.getIdProfesseur());
			statement.setInt(5, cours.getIdMatiere());
			statement.setInt(6, cours.getIdSalle());
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
	public boolean update(Cours cours) {
		String query = "UPDATE cours SET nom = ?, "
				+ "date = ?,"
				+ "id_session = ?, id_professeur = ?, "
				+ "id_matiere = ?, id_salle = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cours.getNom());
			statement.setString(2, cours.getDate());
			statement.setInt(3, cours.getIdSession());
			statement.setInt(4, cours.getIdProfesseur());
			statement.setInt(5, cours.getIdMatiere());
			statement.setInt(6, cours.getIdSalle());
			statement.setInt(7, cours.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(Cours cours) {
		String query = "DELETE FROM cours WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, cours.getId());
			if(statement.executeUpdate() > 0)	return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public Cours get(int id) {
		String query = "SELECT * FROM cours WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Cours cours = new Cours(
	            		resultSet.getInt("id"),
	            		resultSet.getString("nom"),
	            		resultSet.getString("date"),
	            		resultSet.getInt("id_session"),
	            		resultSet.getInt("id_professeur"),
	            		resultSet.getInt("id_matiere"),
	                    resultSet.getInt("id_salle")
	            );
				return cours;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Cours> getAll() {
		List<Cours> coursList = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM cours";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Cours cours = new Cours(
	            		resultSet.getInt("id"),
	            		resultSet.getString("nom"),
	            		resultSet.getString("date"),
	            		resultSet.getInt("id_session"),
	            		resultSet.getInt("id_professeur"),
	            		resultSet.getInt("id_matiere"),
	                    resultSet.getInt("id_salle")
	            );
	            coursList.add(cours);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coursList;
	}
	
	
	@Override
	public List<Cours> getBySession(Session session) {
		List<Cours> coursList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			
			String query = "SELECT * FROM cours "
							+ "WHERE id_session = " + session.getId();
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Cours cours = new Cours(
	            		resultSet.getInt("id"),
	            		resultSet.getString("nom"),
	            		resultSet.getString("date"),
	            		resultSet.getInt("id_session"),
	            		resultSet.getInt("id_professeur"),
	            		resultSet.getInt("id_matiere"),
	                    resultSet.getInt("id_salle")
	            );
	            coursList.add(cours);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coursList;
	}
	
	@Override
	public List<Cours> getBySession(int id) {
		List<Cours> coursList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			
			String query = "SELECT * FROM cours "
							+ "WHERE id_session =? " + id;
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Cours cours = new Cours(
	            		resultSet.getInt("id"),
	            		resultSet.getString("nom"),
	            		resultSet.getString("date"),
	            		resultSet.getInt("id_session"),
	            		resultSet.getInt("id_professeur"),
	            		resultSet.getInt("id_matiere"),
	                    resultSet.getInt("id_salle")
	            );
	            coursList.add(cours);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coursList;
	}
}
