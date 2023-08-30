package app.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import app.model.dao.UserDao;
import app.model.dao.UserDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.User;

@Service
public class UserServiceImplementation implements UserService {
	Connection connection;
	UserDao userDao;
	
	public UserServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		userDao = new UserDaoImplementation(connection);
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public Integer addGetId(User user) {
		return userDao.addGetId(user);
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean delete(User user) {
		return userDao.delete(user);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean authenticate(User user, String password) {
		if(BCrypt.checkpw(password, user.getPasswordDigest())) {	
			user.setPasswordDigest(null);
			return true;
		}		
		return false;
	}
	
//	public boolean authenticate(User user, String password) {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(16);
//		if(user != null && bcrypt.matches(password, user.getPasswordDigest())) {
//			return true;
//		}		
//		return false;
//	}

}
