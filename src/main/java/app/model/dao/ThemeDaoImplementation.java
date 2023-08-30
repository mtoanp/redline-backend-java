package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Formation;
import app.model.entity.Theme;

public class ThemeDaoImplementation implements ThemeDao {
	
	private Connection connection;

	public ThemeDaoImplementation(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public int add(Theme theme) {

		String query = "INSERT INTO theme(nom, id_parent) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, theme.getNom());
			statement.setInt(2, theme.getIdParent());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	@Override
	public Integer addGetId(Theme theme) {
		
		String query = "INSERT INTO theme(nom, id_parent) value(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, theme.getNom());
			statement.setInt(2, theme.getIdParent());
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
	public boolean update(Theme theme) {
		String query = "UPDATE theme SET nom = ?, id_parent = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, theme.getNom());
			statement.setInt(2, theme.getIdParent());
			statement.setInt(3, theme.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(Theme theme) {
		String query = "DELETE FROM theme WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, theme.getId());
			if(statement.executeUpdate() > 0)	return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	
	@Override
	public Theme get(int id) {
		String query = "SELECT * FROM theme WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
	            );
				return theme;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Theme> getAll() {
		List<Theme> themes = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM theme";
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
	            Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
	            );
	            themes.add(theme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return themes;
	}
	
	
	@Override
	public List<Theme> getByName(String nom) {
		List<Theme> themes = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM theme WHERE nom LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%"+nom+"%");
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
	            Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
	            );
	            themes.add(theme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return themes;
	}
	
	
	
	public List<Theme> getByFormation(Formation formation) {
		List<Theme> themes = new ArrayList<>();
		String query = "SELECT * FROM theme WHERE id = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
//			statement.setInt(1, formation.getIdTheme());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
				);
				themes.add(theme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return themes;
	}
	
	

	public List<Theme> getSubThemesOf(Theme themeParent) {
		List<Theme> themes = new ArrayList<>();
		String query = "SELECT * FROM theme WHERE id_parent = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, themeParent.getId());
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
				);
				themes.add(theme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return themes;		
	}
	
	
	
	public Theme getTreeRoot() {
		Theme theme = null;
		String query = "SELECT * FROM theme WHERE ISNULL(id_parent)";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				theme = new Theme(
						resultSet.getInt("id"),
						resultSet.getString("nom"),
						resultSet.getInt("id_parent")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theme;
	}
	
	
	public List<Theme> getTreeRoots() {
		List<Theme> themes = new ArrayList<>();
		String query = "SELECT * FROM theme WHERE ISNULL(id_parent)";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Theme theme = new Theme(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getInt("id_parent")
				);
				themes.add(theme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return themes;
	}
}
