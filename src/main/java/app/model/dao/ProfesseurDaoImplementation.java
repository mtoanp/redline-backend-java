package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Professeur;

public class ProfesseurDaoImplementation implements ProfesseurDao {
	
	private Connection connection;
	
	public ProfesseurDaoImplementation(Connection connection) {
		this.connection = connection;
	}


	@Override
	public int add(Professeur professeur) {
		String query = "INSERT INTO professeur(nom, prenom) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, professeur.getNom());
			statement.setString(2, professeur.getPrenom());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	
	@Override
	public Integer addGetId(Professeur professeur) {
		
		String query = "INSERT INTO professeur(nom, prenom) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, professeur.getNom());
			statement.setString(2, professeur.getPrenom());
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
	public boolean update(Professeur professeur) {
		String query = "UPDATE professeur SET nom=?, prenom=? WHERE id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);			
			statement.setString(1, professeur.getNom());
			statement.setString(2, professeur.getPrenom());
			statement.setInt(3, professeur.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean delete(Professeur professeur) {
		String query = "DELETE FROM professeur WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, professeur.getId());
			if(statement.executeUpdate() > 0)	return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	

	@Override
	public Professeur get(int id) {
		String query = "SELECT * FROM professeur WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Professeur professeur = new Professeur(
						resultSet.getInt("id"),	
						resultSet.getString("nom"),	
						resultSet.getString("prenom")	
				);
				return professeur;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	@Override
	public List<Professeur> getAll() {
		List<Professeur> professeurs = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM professeur";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Professeur professeur = new Professeur(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom")
	            );
	            professeurs.add(professeur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return professeurs;
	}

	
	@Override
	public Professeur getByCours(Cours cours) {
		String query = "SELECT * FROM professeur WHERE id = " + cours.getIdProfesseur();
		Professeur professeur = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				professeur = new Professeur(
						resultSet.getInt("id"),
						resultSet.getString("nom"),
						resultSet.getString("prenom")
				);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professeur;
	}
}
