package persistence.dao;

import java.util.List;
import model.Genere;

public interface GenereDao {

	public void save(Genere genere);  // Create
	public Genere findByPrimaryKey(String tipo_genere);     // Retrieve
	public List<Genere> findAll();       
	public void update(Genere genere); //Update
	public void delete(Genere genere); //Delete
	
}
