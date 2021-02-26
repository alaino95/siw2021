package persistence.dao;

import java.util.List;

import model.Ebook;

public interface EbookDao {
	
	public void save(Ebook ebook);  // Create
	public Ebook findByPrimaryKey(long l);     // Retrieve
	public List<Ebook> findAll();     
	public void update(Ebook ebook);	//Update
	public void delete(Ebook ebook); //Delete
	
	public List<Ebook> trovaTramiteGenereAndCasaEditrice(String casaEditrice, String genere);
}
