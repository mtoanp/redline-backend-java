package app.model.dao;

import java.util.List;

import app.model.entity.User;

public interface UserDao {
	public int add(User user);
	public Integer addGetId(User user);
	public User get(int id);
	public User getByEmail(String email);
	public List<User> getAll();
	public boolean delete(User user);
	public boolean update(User user);
}
