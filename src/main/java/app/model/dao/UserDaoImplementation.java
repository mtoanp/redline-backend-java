package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.User;

public class UserDaoImplementation implements UserDao {

	private Connection connection;
	
	public UserDaoImplementation(Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public int add(User user) {
		String query = "INSERT INTO user(name, email, password_digest) value(?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.hashedPassword());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public Integer addGetId(User user) {
		
		String query = "INSERT INTO user(name, email, password_digest) value(?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.hashedPassword());
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
	public boolean update(User user) {
		String query = "UPDATE user SET name = ?, email = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getId());
			if(statement.executeUpdate() > 0)	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	@Override
	public boolean delete(User user) {
		String query = "DELETE FROM user WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, user.getId());
			if(statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User get(int id) {
		String query = "SELECT * FROM user WHERE id = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            User user = new User(
	                    resultSet.getInt("id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("email")
	            );
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM user";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            User user = new User(
	                    resultSet.getInt("id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("email")
	            );
	            users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	
	public List<User> getByIdSession(int id) {
		List<User> users = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
//			String query = "SELECT * FROM user WHERE id_session = " + id;
			String query = "SELECT * FROM user e "
							+ " LEFT JOIN session_user es"
							+ " ON e.id = es.id_user"
							+ " WHERE es.id_session = " + id;
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
	            User user = new User(
	                    resultSet.getInt("id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("email")
	            );
	            users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}


	@Override
	public User getByEmail(String email) {
		String query = "SELECT * FROM user WHERE email = ?";
		try {
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setString(1, email);
		    ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {	
	            User user = new User(
	                    resultSet.getInt("id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("email"),
	                    resultSet.getString("password_digest"),
	                    resultSet.getBoolean("admin")
	            );
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
