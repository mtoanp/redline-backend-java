package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.Formation;
import app.model.entity.Session;
import app.model.entity.Theme;

public class FormationDaoImplementation implements FormationDao {
	
	private Connection connection;

	public FormationDaoImplementation(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public int add(Formation formation) {

		String query = "INSERT INTO formation(nom, description, prix) value(?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, formation.getNom());
			statement.setString(2, formation.getDescription());
			statement.setInt(3, formation.getPrix());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	@Override
	public Integer addGetId(Formation formation) {
		
		String query = "INSERT INTO formation(nom, description, prix) value(?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, formation.getNom());
			statement.setString(2, formation.getDescription());
			statement.setInt(3, formation.getPrix());
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
	public boolean update(Formation formation) {
		String query = "UPDATE formation SET nom = ?, description = ?, prix = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, formation.getNom());
			statement.setString(2, formation.getDescription());
			statement.setInt(3, formation.getPrix());
			statement.setInt(4, formation.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(Formation formation) {
		String query = "DELETE FROM formation WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, formation.getId());
			if(statement.executeUpdate() > 0)	return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public Formation get(int id) {
		String query = "SELECT * FROM formation WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            Formation formation = new Formation(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("prix")
	            );
				return formation;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Formation> getAll() {
		List<Formation> formations = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM formation";
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
	            Formation formation = new Formation(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("prix")
	            );
	            formations.add(formation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	
	@Override
	public List<Formation> getByName(String nom) {
		List<Formation> formations = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM formation WHERE nom LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%"+nom+"%");
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
	            Formation formation = new Formation(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("prix")
	            );
	            formations.add(formation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	
	@Override
	public List<Formation> getByTheme(Theme theme) {
		List<Formation> formations = new ArrayList<>();
		String query = "SELECT * FROM formation f"
				+ " LEFT JOIN formation_theme ft"
				+ " ON f.id = ft.id_formation"
				+ " WHERE ft.id_theme = " + theme.getId();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
	            Formation formation = new Formation(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("prix")
	            );
	            formations.add(formation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	
	@Override
	public Formation getBySession(Session session) {
		Formation formation = null;
		String query = "SELECT * FROM formation WHERE id = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, session.getIdFormation());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				formation = new Formation(
	                    resultSet.getInt("id"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("prix")
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formation;
	}
	
	
	@Override
	public boolean addTheme(Formation formation, Theme theme) {
		if(!isExiste(formation, theme)) {
			String query = "INSERT INTO formation_theme(id_formation, id_theme) VALUE(?, ?)";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				statement.setInt(1, formation.getId());
				statement.setInt(2, theme.getId());
				if (statement.executeUpdate() > 0) {
					System.out.println("add theme to formation successfully!");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		} else {
			return true;
		}
		return false;
	}
	
	
	public boolean removeTheme(Formation formation, Theme theme) {
		if(isExiste(formation, theme)) {
			String query = "DELETE FROM formation_theme WHERE id_formation = ? and id_theme = ?";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				statement.setInt(1, formation.getId());
				statement.setInt(2, theme.getId());
				if (statement.executeUpdate() > 0) {
					System.out.println("formation_theme relationship is removed");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isExiste(Formation formation, Theme theme) {
		String query = "SELECT * FROM formation_theme WHERE id_formation = ? AND id_theme = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, formation.getId());
			statement.setInt(2, theme.getId());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				System.out.println("Relation existe! (" + formation.getId() + ", " + theme.getId() + ")");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
