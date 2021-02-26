package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CasaEditrice;
import persistence.DataSource;
import persistence.dao.CasaEditriceDao;

public class CasaEditriceDaoJDBC implements CasaEditriceDao {
	
	private DataSource dataSource;

	public CasaEditriceDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CasaEditrice casaEditrice) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "insert into casaeditrice(nome) values (?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, casaEditrice.getNome());

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
	public CasaEditrice findByPrimaryKey(String nome_casaEditrice) {
		Connection connection = null;
		CasaEditrice casaEditrice = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select *" + "from casaeditrice " + "where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome_casaEditrice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				casaEditrice = new CasaEditrice();
				casaEditrice.setNome(result.getString("nome"));
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
		return casaEditrice;
	}

	@Override
	public List<CasaEditrice> findAll() {
		Connection connection = null;
		List<CasaEditrice> caseEditrici = new ArrayList<>();
		try {
			connection = this.dataSource.getConnection();
			CasaEditrice casaEditrice;
			PreparedStatement statement;
			String query = "select * from casaeditrice";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				casaEditrice = new CasaEditrice();
				casaEditrice.setNome(result.getString("nome"));
				caseEditrici.add(casaEditrice);
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
		return caseEditrici;
	}

	@Override
	public void delete(CasaEditrice casaEditrice) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM casaeditrice WHERE nome = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, casaEditrice.getNome());
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
