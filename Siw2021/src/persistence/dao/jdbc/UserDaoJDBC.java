package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.User;
import persistence.DataSource;
import persistence.dao.UserDao;

public class UserDaoJDBC implements UserDao {
	private DataSource dataSource;

	public UserDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(User user) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "insert into utente(email,password) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
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

	public User findByPrimaryKey(String email) {
		Connection connection = null;
		User user = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from utente where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				user = new User();
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
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
		return user;
	}

	public List<User> findAll() {
		Connection connection = null;
		List<User> userr = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			User user;
			PreparedStatement statement;
			String query = "select * from utente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user = new User();
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				
				userr.add(user);
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
		return userr;
	}

	public void update(User user) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update utente SET email = ?, password = ? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
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

	public void delete(User user) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM utente WHERE email = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, user.getEmail());
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
