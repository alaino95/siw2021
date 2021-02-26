package model;

import java.util.List;

public class PagamentoCrediti {
	Long id;
	int nCrediti;
	Publisher username_publisher;
	List<Ebook> ebook_da_scaricare;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getnCrediti() {
		return nCrediti;
	}

	public void setnCrediti(int nCrediti) {
		this.nCrediti = nCrediti;
	}

	public Publisher getUsername_publisher() {
		return username_publisher;
	}

	public void setUsername_publisher(Publisher username_publisher) {
		this.username_publisher = username_publisher;
	}

	public List<Ebook> getEbook_da_scaricare() {
		return ebook_da_scaricare;
	}

	public void setEbook_da_scaricare(List<Ebook> ebook_da_scaricare) {
		this.ebook_da_scaricare = ebook_da_scaricare;
	}
}
