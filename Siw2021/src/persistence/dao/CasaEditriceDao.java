package persistence.dao;

import java.util.List;
import model.CasaEditrice;

public interface CasaEditriceDao {

	public void save(CasaEditrice casaEditrice);  // Create
	public CasaEditrice findByPrimaryKey(String nome_casaEditrice);     // Retrieve
	public List<CasaEditrice> findAll();       
	public void delete(CasaEditrice casaEditrice); //Delete
	
}
