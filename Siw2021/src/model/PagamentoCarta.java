package model;

import java.util.List;

public class PagamentoCarta {
	private long id;
	private int nCarta;
	private String dataScadenza;
	private int cvv;
	private String intestatario;
	private int totale;
	private Publisher username_publisher;
	private User email_user;
	private List<Ebook> ebook_da_scaricare;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getnCarta() {
		return nCarta;
	}
	public void setnCarta(int nCarta) {
		this.nCarta = nCarta;
	}
	public String getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public int getTotale() {
		return totale;
	}
	public void setTotale(int totale) {
		this.totale = totale;
	}
	public Publisher getUsername_publisher() {
		return username_publisher;
	}
	public void setUsername_publisher(Publisher username_publisher) {
		this.username_publisher = username_publisher;
	}
	public User getEmail_user() {
		return email_user;
	}
	public void setEmail_user(User email_user) {
		this.email_user = email_user;
	}
	public List<Ebook> getEbook_da_scaricare() {
		return ebook_da_scaricare;
	}
	public void setEbook_da_scaricare(List<Ebook> ebook_da_scaricare) {
		this.ebook_da_scaricare = ebook_da_scaricare;
	}
}
