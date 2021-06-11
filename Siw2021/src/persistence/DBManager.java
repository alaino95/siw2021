package persistence;

import java.util.List;
import model.CasaEditrice;
import model.Ebook;
import model.Genere;
import model.PagamentoCarta;
import model.Publisher;
import model.Recensione;
import model.User;
import persistence.dao.CasaEditriceDao;
import persistence.dao.EbookDao;
import persistence.dao.GenereDao;
import persistence.dao.jdbc.CasaEditriceDaoJDBC;
import persistence.dao.jdbc.EbookDaoJDBC;
import persistence.dao.jdbc.GenereDaoJDBC;
import persistence.dao.RecensioneDao;
import persistence.dao.jdbc.RecensioneDaoJDBC;
import persistence.dao.jdbc.UserDaoJDBC;
import persistence.dao.PagamentoCreditiDao;
import persistence.dao.jdbc.PagamentoCartaDaoJDBC;
import persistence.dao.jdbc.PagamentoCreditiDaoJDBC;
import persistence.dao.jdbc.PublisherDaoJDBC;
import persistence.dao.PagamentoCartaDao;
import persistence.dao.PublisherDao;
import persistence.dao.UserDao;

public class DBManager {
	
	private static DataSource dataSource;

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource = new DataSource("jdbc:postgresql://localhost:5432/EbookExchange", "postgres", "root");
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	public static DBManager instance = null;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}
	
	public void inserisciPublisher(Publisher p) {
		getPublisherDAO().save(p);
	}

	public void inserisciUser(User u) {
		getUserDAO().save(u);
	}

	public void inserisciPagamentoU(PagamentoCarta p) {
		getPagamentoCartaDAO().saveU(p);
	}

	public void inserisciPagamentoP(PagamentoCarta p) {
		getPagamentoCartaDAO().saveP(p);
	}
	
	public void inserisciEbook(Ebook ebook) {
		getEbookDAO().save(ebook);
	}
	
	public List<Ebook> dammiEbookDaGenereECasaEditrice(String casaEditrice, String genere) {
		return getEbookDAO().trovaTramiteGenereAndCasaEditrice(casaEditrice, genere);
	}
	
	public List<Recensione> dammiRecensioneDiEbook(int idEbook) {
		return getRecensioneDAO().findByNote(idEbook);
	}
	
	public User login(String email, String password) {
		User u = getUserDAO().findByPrimaryKey(email);
		if (u != null && u.getPassword().equals(password))
			return u;
		return null;
	}

	public Publisher loginP(String username, String password) {
		Publisher p = getPublisherDAO().findByPrimaryKey(username);
		if (p != null && p.getPassword().equals(password))
			return p;
		return null;
	}
	
	public List<Publisher> dammiTuttiPublisher() {
		return getPublisherDAO().findAll();
	}

	public List<User> dammiTuttiUser() {
		return getUserDAO().findAll();
	}
	
	public List<CasaEditrice> dammiCaseEditrici() {
		return getCasaEditriceDAO().findAll();
	}
	
	public List<Genere> dammiGeneri() {
		return getGenereDAO().findAll();
	}
	
	public PagamentoCartaDao getPagamentoCartaDAO() {
		return new PagamentoCartaDaoJDBC(dataSource);
	}

	public PagamentoCreditiDao getPagamentoCreditiDAO() {
		return new PagamentoCreditiDaoJDBC(dataSource);
	}

	public PublisherDao getPublisherDAO() {
		return new PublisherDaoJDBC(dataSource);
	}
	
	public UserDao getUserDAO() {
		return new UserDaoJDBC(dataSource);
	}

	public RecensioneDao getRecensioneDAO() {
		return new RecensioneDaoJDBC(dataSource);
	}
	
	public EbookDao getEbookDAO() {
		return new EbookDaoJDBC(dataSource);
	}
	
	public CasaEditriceDao getCasaEditriceDAO() {
		return new CasaEditriceDaoJDBC(dataSource);
	}
	
	public GenereDao getGenereDAO() {
		return new GenereDaoJDBC(dataSource);
	}
}
