package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Genere;
import persistence.DataSource;
import persistence.dao.GenereDao;

public class GenereDaoJDBC implements GenereDao {
	
	private DataSource dataSource;

	public GenereDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Genere genere) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "insert into genere(tipo,prezzo,prezzo_crediti) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, genere.getTipo());
			statement.setInt(2, genere.getPrezzo());
			statement.setInt(3, genere.getPrezzo_crediti());
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
	public Genere findByPrimaryKey(String tipo_genere) {
		Connection connection = null;
		Genere genere = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select *" + "from genere " + "where tipo = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, tipo_genere);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				genere = new Genere();
				genere.setTipo(result.getString("tipo"));
				genere.setPrezzo(result.getInt("prezzo"));
				genere.setPrezzo_crediti(result.getInt("prezzo_crediti"));
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
		return genere;
	}

	@Override
	public List<Genere> findAll() {
		Connection connection = null;
		List<Genere> generi = new ArrayList<>();
		try {
			connection = this.dataSource.getConnection();
			Genere genere;
			PreparedStatement statement;
			String query = "select * from genere";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				genere = new Genere();
				genere.setTipo(result.getString("tipo"));
				genere.setPrezzo(result.getInt("prezzo"));
				genere.setPrezzo_crediti(result.getInt("prezzo_crediti"));
				generi.add(genere);
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
		return generi;
	}

	@Override
	public void update(Genere genere) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update genere SET prezzo = ?, prezzo_crediti = ? WHERE tipo=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, genere.getPrezzo());
			statement.setInt(2, genere.getPrezzo_crediti());
			statement.setString(3, genere.getTipo());
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
	public void delete(Genere genere) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM genere WHERE tipo = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, genere.getTipo());
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
