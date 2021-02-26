package persistence.dao;

import java.util.List;
import model.PagamentoCarta;

public interface PagamentoCartaDao {
	
	public void saveP(PagamentoCarta pagamentoCarta);  // Create
	public void saveU(PagamentoCarta pagamentoCarta);  // Create
	public PagamentoCarta findByPrimaryKey(long id);     // Retrieve
	public List<PagamentoCarta> findAll();
}
