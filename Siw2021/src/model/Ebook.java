package model;

public class Ebook {
	private Long id;
	private String nome;
	private int n_pagine;
	private int prezzo;
	private int prezzo_crediti;
	private Publisher publisher;
	private String trama;
	private Genere genere;
	private CasaEditrice casaEditrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getN_pagine() {
		return n_pagine;
	}
	public void setN_pagine(int n_pagine) {
		this.n_pagine = n_pagine;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public int getPrezzo_crediti() {
		return prezzo_crediti;
	}
	public void setPrezzo_crediti(int prezzo_crediti) {
		this.prezzo_crediti = prezzo_crediti;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public String getTrama() {
		return trama;
	}
	public void setTrama(String trama) {
		this.trama = trama;
	}
	public Genere getGenere() {
		return genere;
	}
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	public CasaEditrice getCasaEditrice() {
		return casaEditrice;
	}
	public void setCasaEditrice(CasaEditrice casaEditrice) {
		this.casaEditrice = casaEditrice;
	}
}
