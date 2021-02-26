package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Ebook;
import model.PagamentoCarta;
import model.Publisher;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.PagamentoCartaDao;

public class PagamentoCartaDaoJDBC implements PagamentoCartaDao {
	private DataSource dataSource;

	public PagamentoCartaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void saveU(PagamentoCarta pagamentoCarta) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			pagamentoCarta.setId(id);
			String insert = "insert into pagamentocarta(id,num_carta,cvv,intestatario,totale,email_u,data_scadenza,username_p) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, pagamentoCarta.getId());
			statement.setInt(2, pagamentoCarta.getnCarta());
			statement.setString(7, pagamentoCarta.getDataScadenza());
			statement.setInt(3, pagamentoCarta.getCvv());
			statement.setString(4, pagamentoCarta.getIntestatario());
			statement.setInt(5, pagamentoCarta.getTotale());
			statement.setString(6, pagamentoCarta.getEmail_user().getEmail());
			statement.setString(8, null);
			statement.executeUpdate();
			updateReference(pagamentoCarta, connection);
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void saveP(PagamentoCarta pagamentoCarta) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			pagamentoCarta.setId(id);
			String insert = "insert into pagamentocarta(id,num_carta,cvv,intestatario,totale,email_u,data_scadenza,username_p) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, pagamentoCarta.getId());
			statement.setInt(2, pagamentoCarta.getnCarta());
			statement.setString(7, pagamentoCarta.getDataScadenza());
			statement.setInt(3, pagamentoCarta.getCvv());
			statement.setString(4, pagamentoCarta.getIntestatario());
			statement.setInt(5, pagamentoCarta.getTotale());
			statement.setString(8, pagamentoCarta.getUsername_publisher().getUsername());
			statement.setString(6, null);
			statement.executeUpdate();
			updateReference(pagamentoCarta, connection);
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public List<PagamentoCarta> findAll() {
		Connection connection = null;
		List<PagamentoCarta> pagamentoCarta = new ArrayList<>();
		try {
			connection = this.dataSource.getConnection();
			PagamentoCarta pagamentoc;
			PreparedStatement statement;
			String query = "select * from pagamentocarta";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pagamentoc = findByPrimaryKey(result.getLong("id"));
				pagamentoCarta.add(pagamentoc);
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
		return pagamentoCarta;
	}

	@Override
	public PagamentoCarta findByPrimaryKey(long id) {
		PagamentoCarta pagamentoCarta = null;
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from pagamentocarta where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				pagamentoCarta = new PagamentoCarta();
				pagamentoCarta.setId(result.getLong("id"));
				pagamentoCarta.setnCarta(result.getInt("num_carta"));

				pagamentoCarta.setCvv(result.getInt("cvv"));
				pagamentoCarta.setIntestatario(result.getString("intestatario"));
				pagamentoCarta.setTotale(result.getInt("totale"));
				pagamentoCarta.setDataScadenza(result.getString("data_scadenza"));

				Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey(result.getString("username_p"));
				if (p != null) {
					pagamentoCarta.setUsername_publisher(p);
				}

				User u = DBManager.getInstance().getUserDAO().findByPrimaryKey(result.getString("email_u"));

				if (u != null) {
					pagamentoCarta.setEmail_user(u);
				}
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
		return pagamentoCarta;
	}

	private void updateReference(PagamentoCarta pagamentoCarta, Connection connection) throws SQLException {
		for (Ebook ebook : pagamentoCarta.getEbook_da_scaricare()) {
			String esiste = "select id from pagamentocartaeseguito where ebook=? AND pagamento_carta=?";
			PreparedStatement statement = connection.prepareStatement(esiste);
			statement.setLong(1, pagamentoCarta.getId());
			statement.setLong(2, ebook.getId());
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				String inserisci = "insert into pagamentocartaeseguito (id, ebook, pagamento_carta) values (?,?,?)";
				PreparedStatement st = connection.prepareStatement(inserisci);
				Long id = IdBroker.getId(connection);
				st.setLong(1, id);
				st.setLong(2, ebook.getId());
				st.setLong(3, pagamentoCarta.getId());
				st.executeUpdate();
			}
		}
	}

}
