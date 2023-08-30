package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Salle;

public class SalleDaoImplementation implements SalleDao {

	private Connection connection;
	
	public SalleDaoImplementation(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public int add(Salle salle) {

		String query = "INSERT INTO salle(nom) value(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, salle.getNom());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	@Override
	public Integer addGetId(Salle salle) {
		
		String query = "INSERT INTO salle(nom) value(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, salle.getNom());
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
	public boolean update(Salle salle) {
		String query = "UPDATE salle SET nom = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, salle.getNom());
			statement.setInt(2, salle.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(Salle salle) {
		String query = "DELETE FROM salle WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, salle.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public Salle get(int id) {
		String query = "SELECT * FROM salle WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Salle salle = new Salle(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom")
	            );
				return salle;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Salle> getAll() {
		List<Salle> salles = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM salle";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Salle salle = new Salle(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom")
	            );
	            salles.add(salle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salles;
	}
	
	
	@Override
	public Salle getByCours(Cours cours) {
		String query = "SELECT * FROM salle WHERE id = " + cours.getIdSalle();
		Salle salle = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				salle = new Salle(
						resultSet.getInt("id"),
						resultSet.getString("nom")
				);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}
	
}
