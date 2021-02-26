package controller;

import java.util.List;

import model.Ebook;
import persistence.DBManager;
import persistence.DataSource;

public class MainClass {

	public static void main(String[] args) {
		
		DataSource dataSource;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource = new DataSource("jdbc:postgresql://localhost:5432/EbookExchange", "postgres", "pgAdmin4");
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}

		DBManager instance = null;
		
		List<Ebook> Ebook = DBManager.getInstance().getEbookDAO().findAll();
		for (Ebook e : Ebook) {
			System.out.println(e.getPublisher().getUsername());
		}
	}
}
