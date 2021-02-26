package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recensione;
import model.User;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.RecensioneDao;

public class RecensioneDaoJDBC implements RecensioneDao {

	private DataSource dataSource;

	public RecensioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Recensione recensione) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			recensione.setId(id);
			String insert = "insert into recensione(id,testo,id_ebook,id_utente,id_publisher) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, recensione.getId());
			statement.setString(2, recensione.getTesto());
			statement.setInt(3, recensione.getId_ebook());
			if(recensione.getUser()!=null) {
				statement.setString(4, recensione.getUser().getEmail());
				statement.setString(5, null);
			} else {
				statement.setString(4, null);
				statement.setString(5, recensione.getPublisher().getUsername());
			}
			
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
	public Recensione findByPrimaryKey(Long id) {
		Connection connection = null;
		Recensione recensione = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select id,testo,id_ebook,id_utente,id_publisher" + "from recensione " + "where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				recensione = new Recensione();
				recensione.setId(result.getLong("id"));
				recensione.setTesto(result.getString("testo"));
				recensione.setId_ebook(result.getInt("id_ebook"));

				User user = DBManager.getInstance().getUserDAO().findByPrimaryKey(result.getString("id_utente"));
				recensione.setUser(user);
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
		return recensione;
	}

	@Override
	public List<Recensione> findByNote(int id_ebook) {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Recensione recensione = null;
			PreparedStatement statement;
			String query = "select *" + "from recensione " + "where id_ebook = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id_ebook);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				recensione = new Recensione();
				recensione.setId(result.getLong("id"));
				recensione.setTesto(result.getString("testo"));
				recensione.setId_ebook(result.getInt("id_ebook"));
				User user = DBManager.getInstance().getUserDAO().findByPrimaryKey(result.getString("id_utente"));
				recensione.setUser(user);
				recensioni.add(recensione);
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
		return recensioni;
	}

	@Override
	public void update(Recensione recensione) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update recensione SET testo = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, recensione.getTesto());
			statement.setLong(2, recensione.getId());
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
	public void delete(Recensione recensione) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM recensione WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, recensione.getId());
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

}
