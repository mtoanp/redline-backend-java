package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Cours;
import app.model.entity.Matiere;

public class MatiereDaoImplementation implements MatiereDao {
	
	private Connection connection;
	
	public MatiereDaoImplementation(Connection connection) {
		this.connection = connection;
	}


	@Override
	public int add(Matiere matiere) {

		String query = "INSERT INTO matiere(nom) value(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matiere.getNom());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	@Override
	public Integer addGetId(Matiere matiere) {
		
		String query = "INSERT INTO matiere(nom) value(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, matiere.getNom());
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
	public boolean update(Matiere matiere) {
		String query = "UPDATE matiere SET nom = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matiere.getNom());
			statement.setInt(2, matiere.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean delete(Matiere matiere) {
		String query = "DELETE FROM matiere WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, matiere.getId());
			if(statement.executeUpdate() > 0)	return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	

	@Override
	public Matiere get(int id) {
		String query = "SELECT * FROM matiere WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Matiere matiere = new Matiere(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom")
	            );
				return matiere;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public List<Matiere> getAll() {
		List<Matiere> matieres = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM matiere";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            Matiere matiere = new Matiere(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom")
	            );
	            matieres.add(matiere);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matieres;
	}
	
	
	@Override
	public List<Matiere> getByName(String nom) {
		List<Matiere> matieres = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM matiere WHERE nom LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%"+nom+"%");
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				Matiere matiere = new Matiere(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom")
	            );
	            matieres.add(matiere);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matieres;
	}
	
	
	@Override
	public Matiere getByCours(Cours cours) {
		String query = "SELECT * FROM matiere WHERE id = " + cours.getIdMatiere();
		Matiere matiere = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				matiere = new Matiere(
						resultSet.getInt("id"),
						resultSet.getString("nom")
				);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matiere;
	}
}

