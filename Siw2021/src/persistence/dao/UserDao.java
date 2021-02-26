package persistence.dao;

import java.util.List;

import model.User;


public interface UserDao {
	
	public void save(User user);  // Create
	public User findByPrimaryKey(String email);     // Retrieve
	public List<User> findAll();       
	public void update(User studente); //Update
	public void delete(User studente); //Delete	
}
