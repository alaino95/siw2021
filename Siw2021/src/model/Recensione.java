package model;

public class Recensione {
	private Long id;
	private String testo;
	private int id_ebook;
	private User user;
	private Publisher publisher;

	public Long getId() {
		return id;
	}

	public void setId(Long id2) {
		this.id = id2;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public int getId_ebook() {
		return id_ebook;
	}

	public void setId_ebook(int id_ebook) {
		this.id_ebook = id_ebook;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
