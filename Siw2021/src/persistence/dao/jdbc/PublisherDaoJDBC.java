package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Publisher;
import persistence.DataSource;
import persistence.dao.PublisherDao;

public class PublisherDaoJDBC implements PublisherDao {
	private DataSource dataSource;

	public PublisherDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Publisher publisher) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "insert into publisher(username, password, num_crediti) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, publisher.getUsername());
			statement.setString(2, publisher.getPassword());
			statement.setInt(3, publisher.getnCrediti());
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

	public Publisher findByPrimaryKey(String username) {
		Connection connection = null;
		Publisher publisher = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from publisher where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				publisher = new Publisher();
				publisher.setUsername(result.getString("username"));
				publisher.setPassword(result.getString("password"));
				publisher.setnCrediti(result.getInt("num_crediti"));
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
		return publisher;
	}

	public List<Publisher> findAll() {
		Connection connection = null;
		List<Publisher> publishers = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Publisher Publisher;
			PreparedStatement statement;
			String query = "select * from Publisher";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Publisher = new Publisher();
				Publisher.setUsername(result.getString("username"));
				Publisher.setPassword(result.getString("password"));
				Publisher.setnCrediti(result.getInt("num_crediti"));
				publishers.add(Publisher);
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
		return publishers;
	}

	public void update(Publisher Publisher) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update Publisher SET password = ?, num_crediti = ? WHERE username=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, Publisher.getPassword());
			statement.setInt(2, Publisher.getnCrediti());
			statement.setString(3, Publisher.getUsername());
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

	public void delete(Publisher Publisher) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM Publisher WHERE username = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, Publisher.getUsername());
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
