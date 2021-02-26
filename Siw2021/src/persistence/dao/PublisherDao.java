package persistence.dao;

import java.util.List;
import model.Publisher;

public interface PublisherDao {
	
	public void save(Publisher publisher);  // Create
	public Publisher findByPrimaryKey(String username);     // Retrieve
	public List<Publisher> findAll();       
	public void update(Publisher publisher); //Update
	public void delete(Publisher publisher); //Delete	
}
