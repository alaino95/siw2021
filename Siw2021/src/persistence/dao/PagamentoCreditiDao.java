package persistence.dao;

import java.util.List;
import model.PagamentoCrediti;;

public interface PagamentoCreditiDao {
	
	public void save(PagamentoCrediti PagamentoCrediti);  // Create
	public PagamentoCrediti findByPrimaryKey(int id);     // Retrieve
	public List<PagamentoCrediti> findAll();
}
