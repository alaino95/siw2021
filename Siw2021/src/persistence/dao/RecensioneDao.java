package persistence.dao;

import java.util.List;

import model.Recensione;

public interface RecensioneDao {

	public void save(Recensione recensione);  // Create
	public Recensione findByPrimaryKey(Long id);     // Retrieve
	public List<Recensione> findByNote(int id_ebook);       
	public void update(Recensione recensione); //Update
	public void delete(Recensione recensione); //Delete	
}
