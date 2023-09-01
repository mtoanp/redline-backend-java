package app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.entity.*;

public class SessionDaoImplementation implements SessionDao {
	
	private Connection connection;
	
	public SessionDaoImplementation(Connection connection) {
		this.connection = connection;
	}

	
	@Override
	public int add(Session session) {
		
		String query = "INSERT INTO session(date_de_debut, date_de_fin, capacite, id_formation) VALUE(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, session.getDateDeDebut());
			statement.setString(2, session.getDateDeFin());
			statement.setInt(3, session.getCapacite());
			statement.setInt(4, session.getIdFormation());
			return statement.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public Integer addGetId(Session session) {
		String query = "INSERT INTO session(date_de_debut, date_de_fin, capacite, id_formation) VALUE(?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, session.getDateDeDebut());
			statement.setString(2, session.getDateDeFin());
			statement.setInt(3, session.getCapacite());
			statement.setInt(4, session.getIdFormation());
			if (statement.executeUpdate() > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public boolean update(Session session) {
		String query = "UPDATE session SET date_de_debut = ?, date_de_fin = ?, capacite = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, session.getDateDeDebut());
			statement.setString(2, session.getDateDeFin());
			statement.setInt(3, session.getCapacite());
			statement.setInt(4, session.getId());
			
			if(statement.executeUpdate() > 0) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	
	@Override
	public boolean delete(Session session) {
		String query = "DELETE FROM session WHERE id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, session.getId());
			if(statement.executeUpdate() > 0)
				return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	
	@Override
	public Session get(int id) {
		String query = "SELECT * FROM session WHERE id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Session session = new Session(
						resultSet.getInt("id"),
						resultSet.getString("date_de_debut"),
						resultSet.getString("date_de_fin"),
						resultSet.getInt("capacite"),
						resultSet.getInt("id_formation")
				);
				return session;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Session> getAll() {
		List<Session> sessions = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM session";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Session session = new Session(
						resultSet.getInt("id"),
						resultSet.getString("date_de_debut"),
						resultSet.getString("date_de_fin"),
						resultSet.getInt("capacite"),
						resultSet.getInt("id_formation")
				);
				sessions.add(session);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sessions;
	}

	
	@Override
	public List<Session> getByFormation(Formation formation) {
	List<Session> sessions = new ArrayList<>();
	
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM session WHERE id_formation = " + formation.getId();
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Session session = new Session(
						resultSet.getInt("id"),
						resultSet.getString("date_de_debut"),
						resultSet.getString("date_de_fin"),
						resultSet.getInt("capacite"),
						resultSet.getInt("id_formation")
					);
				sessions.add(session);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessions;
	}
	
	
	@Override
	public boolean addCandidat(Session session, Etudiant etudiant) {
		String query = "INSERT INTO session_etudiant(id_session, id_etudiant) VALUE(?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, session.getId());
			statement.setInt(2, etudiant.getId());
			if (statement.executeUpdate() > 0) {
				System.out.println("add etudiant to session");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

	@Override
	public boolean removeCandidat(Session session, Etudiant etudiant) {
		String query = "DELETE FROM session_etudiant WHERE id_session = ? and id_etudiant = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, session.getId());
			statement.setInt(2, etudiant.getId());
			if (statement.executeUpdate() > 0) {
				System.out.println("etudiant est effacé");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}


	@Override
	public Candidature getCandidature(Session session, Etudiant etudiant) {
		String query = "SELECT * FROM session_etudiant" +
				" WHERE id_session = ? AND id_etudiant = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, session.getId());
			statement.setInt(2, etudiant.getId());
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				Candidature candidature = new Candidature(
						resultSet.getInt("id_session"),
						resultSet.getInt("id_etudiant")
				);
				return candidature;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addEtudiant(Candidature candidature) {
		String query = "UPDATE session_etudiant SET valide = ? WHERE id_session = ? AND id_etudiant = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setInt(2, candidature.getIdSession());
			statement.setInt(3, candidature.getIdEtudiant());
			if(statement.executeUpdate() > 0) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeEtudiant(Candidature candidature) {
		String query = "UPDATE session_etudiant SET valide = ? WHERE id_session = ? AND id_etudiant = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setInt(2, candidature.getIdSession());
			statement.setInt(3, candidature.getIdEtudiant());
			if(statement.executeUpdate() > 0) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Session getByCours(Cours cours) {
		String query = "SELECT * FROM session WHERE id = " + cours.getIdSession();
		Session session = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				session = new Session(
						resultSet.getInt("id"),
						resultSet.getString("date_de_debut"),
						resultSet.getString("date_de_fin"),
						resultSet.getInt("capacite"),
						resultSet.getInt("id_formation")
				);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return session;
	}
}
