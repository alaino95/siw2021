package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Ebook;
import model.PagamentoCrediti;
import model.Publisher;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.PagamentoCreditiDao;

public class PagamentoCreditiDaoJDBC implements PagamentoCreditiDao {
	private DataSource dataSource;

	public PagamentoCreditiDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(PagamentoCrediti pagamentoCrediti) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			pagamentoCrediti.setId(id);
			String insert = "insert into pagamentocrediti(id, num_crediti, username_p) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, pagamentoCrediti.getId());
			statement.setInt(2, pagamentoCrediti.getnCrediti());
			statement.setString(3, pagamentoCrediti.getUsername_publisher().getUsername());

			statement.executeUpdate();
			updateReference(pagamentoCrediti, connection);
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
	public List<PagamentoCrediti> findAll() {
		Connection connection = null;
		List<PagamentoCrediti> pagamentoCrediti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			PagamentoCrediti pagamentoC;
			PreparedStatement statement;
			String query = "select * from pagamentoCrediti";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pagamentoC = new PagamentoCrediti();
				pagamentoC.setId(result.getLong("id"));
				pagamentoC.setnCrediti(result.getInt("num_crediti"));
				Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey(result.getString("username_p"));
				pagamentoC.setUsername_publisher(p);
				
				pagamentoCrediti.add(pagamentoC);
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
		return pagamentoCrediti;
	}

	private void updateReference(PagamentoCrediti pagamentoCrediti, Connection connection) throws SQLException {
		for (Ebook ebook : pagamentoCrediti.getEbook_da_scaricare()) {
			String esiste = "select id from pagamentocreditieseguito where ebook=? AND pagamento_crediti=?";
			PreparedStatement statement = connection.prepareStatement(esiste);
			statement.setLong(1, pagamentoCrediti.getId());
			statement.setLong(2, ebook.getId());
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				String inserisci = "insert into pagamentocreditieseguito (ebook, pagamento_crediti, id) values (?,?,?)";
				PreparedStatement st = connection.prepareStatement(inserisci);
				Long id = IdBroker.getId(connection);
				st.setLong(3, id);
				st.setLong(1, ebook.getId());
				st.setLong(2, pagamentoCrediti.getId());
				st.executeUpdate();
			}
		}
	}

	@Override
	public PagamentoCrediti findByPrimaryKey(int id) {
		Connection connection = null;
		PagamentoCrediti pagamentoCrediti = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from pagamentocrediti where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pagamentoCrediti = new PagamentoCrediti();
				pagamentoCrediti.setId(result.getLong("id"));
				pagamentoCrediti.setnCrediti(result.getInt("num_crediti"));

				Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey(result.getString("username_p"));
				pagamentoCrediti.setUsername_publisher(p);

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
		return pagamentoCrediti;
	}
}
