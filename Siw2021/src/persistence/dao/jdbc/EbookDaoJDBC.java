package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CasaEditrice;
import model.Ebook;
import model.Genere;
import model.Publisher;
import persistence.DBManager;
import persistence.dao.jdbc.IdBroker;
import persistence.DataSource;
import persistence.dao.EbookDao;

public class EbookDaoJDBC implements EbookDao {
	
	private DataSource dataSource;

	public EbookDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Ebook ebook) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			ebook.setId(id);
			String insert = "insert into ebook(id,nome,n_pagine,prezzo,prezzo_crediti,trama,"
					+ "id_nome_casa_editrice,id_tipo_genere,publisher) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, ebook.getId());
			statement.setString(2, ebook.getNome());
			statement.setInt(3, ebook.getN_pagine());
			statement.setInt(4, ebook.getPrezzo());
			statement.setInt(5, ebook.getPrezzo_crediti());
			statement.setString(6, ebook.getTrama());
			statement.setString(7, ebook.getCasaEditrice().getNome());
			statement.setString(8, ebook.getGenere().getTipo());
			statement.setString(9, ebook.getPublisher().getUsername());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public Ebook findByPrimaryKey(long id) {
		Connection connection = null;
		Ebook ebook = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from ebook where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ebook = new Ebook();
				ebook.setId(result.getLong("id"));
				ebook.setNome(result.getString("nome"));
				ebook.setN_pagine(result.getInt("n_pagine"));
				ebook.setPrezzo(result.getInt("prezzo"));
				ebook.setPrezzo_crediti(result.getInt("prezzo_crediti"));
				ebook.setTrama(result.getString("trama"));

				CasaEditrice ce = DBManager.getInstance().getCasaEditriceDAO()
						.findByPrimaryKey(result.getString("id_nome_casa_editrice"));
				ebook.setCasaEditrice(ce);

				Genere gen = DBManager.getInstance().getGenereDAO()
						.findByPrimaryKey(result.getString("id_tipo_genere"));
				ebook.setGenere(gen);
				
				Publisher p = DBManager.getInstance().getPublisherDAO()
						.findByPrimaryKey(result.getString("publisher"));
				
				ebook.setPublisher(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return ebook;
	}

	@Override
	public List<Ebook> findAll() {
		List<Ebook> ebooks = new ArrayList<Ebook>();
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Ebook ebook = null;
			PreparedStatement statement;
			String query = "select * from ebook ";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ebook = new Ebook();
				ebook.setId(result.getLong("id"));
				ebook.setNome(result.getString("nome"));
				ebook.setN_pagine(result.getInt("n_pagine"));
				ebook.setPrezzo(result.getInt("prezzo"));
				ebook.setPrezzo_crediti(result.getInt("prezzo_crediti"));
				ebook.setTrama(result.getString("trama"));
				
				CasaEditrice ce = DBManager.getInstance().getCasaEditriceDAO()
						.findByPrimaryKey(result.getString("id_nome_casa_editrice"));
				ebook.setCasaEditrice(ce);

				Genere gen = DBManager.getInstance().getGenereDAO()
						.findByPrimaryKey(result.getString("id_tipo_genere"));
				ebook.setGenere(gen);
				
				Publisher p = DBManager.getInstance().getPublisherDAO()
						.findByPrimaryKey(result.getString("publisher"));
				
				ebook.setPublisher(p);
				
				ebooks.add(ebook);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return ebooks;
	}

	@Override
	public void update(Ebook ebook) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update ebook SET nome = ?, n_pagine = ?, prezzo = ?, prezzo_crediti = ?, trama = ?, "
					+ "id_nome_casa_editrice = ?,id_tipo_genere = ?,publisher = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ebook.getNome());
			statement.setInt(2, ebook.getN_pagine());
			statement.setInt(3, ebook.getPrezzo());			
			statement.setLong(4, ebook.getPrezzo_crediti());
			statement.setString(5, ebook.getTrama());
			statement.setString(6, ebook.getCasaEditrice().getNome());
			statement.setString(8, ebook.getGenere().getTipo());
			statement.setString(9, ebook.getPublisher().getUsername());
			statement.setLong(10, ebook.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Ebook ebook) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String deleteReference = "delete FROM pagamentocartaeseguito WHERE ebook = ?";
			PreparedStatement st = connection.prepareStatement(deleteReference);
			st.setLong(1, ebook.getId());
			st.executeUpdate();
			String deleteReference2 = "delete FROM pagamentocreditieseguito WHERE ebook = ?";
			PreparedStatement st2 = connection.prepareStatement(deleteReference2);
			st2.setLong(1, ebook.getId());
			st2.executeUpdate();
			String deleteRec = "delete FROM recensione WHERE id_ebook = ?";
			PreparedStatement st3 = connection.prepareStatement(deleteRec);
			st3.setLong(1, ebook.getId());
			st3.executeUpdate();
			String delete = "delete FROM ebook WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, ebook.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public List<Ebook> trovaTramiteGenereAndCasaEditrice(String casaEditrice, String genere) {
		List<Ebook> ebooks = new ArrayList<Ebook>();
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Ebook ebook;
			PreparedStatement statement;
			String query = "select * from ebook where id_nome_casa_editrice = ? and id_tipo_genere = ? ";
			statement = connection.prepareStatement(query);
			statement.setString(1, casaEditrice);
			statement.setString(2, genere);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ebook = new Ebook();
				ebook.setId(result.getLong("id"));
				ebook.setNome(result.getString("nome"));
				ebook.setN_pagine(result.getInt("n_pagine"));
				ebook.setPrezzo(result.getInt("prezzo"));
				ebook.setPrezzo_crediti(result.getInt("prezzo_crediti"));
				ebook.setTrama(result.getString("trama"));
				
				CasaEditrice ce = DBManager.getInstance().getCasaEditriceDAO()
						.findByPrimaryKey(result.getString("id_nome_casa_editrice"));
				ebook.setCasaEditrice(ce);

				Genere gen = DBManager.getInstance().getGenereDAO()
						.findByPrimaryKey(result.getString("id_tipo_genere"));
				ebook.setGenere(gen);
				
				Publisher p = DBManager.getInstance().getPublisherDAO()
						.findByPrimaryKey(result.getString("publisher"));
				
				ebook.setPublisher(p);
				
				ebooks.add(ebook);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return ebooks;
	}

}
