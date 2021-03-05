package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;

import model.Ebook;
import model.Genere;
import model.CasaEditrice;
import model.Publisher;
import persistence.DBManager;

public class Caricamento extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("publisher") != null) {

			String[] files = req.getParameterValues("files");
			String casaEditrice = req.getParameter("caseeditrici");
			String genere = req.getParameter("generi");
			String trama = req.getParameter("trama");
			String autore = req.getParameter("autore");
			
			CasaEditrice ce = new CasaEditrice();
			ce.setNome(casaEditrice);

			Genere gen = DBManager.getInstance().getGenereDAO().findByPrimaryKey(genere);

			Publisher p = DBManager.getInstance().getPublisherDAO()
					.findByPrimaryKey((String)req.getSession().getAttribute("publisher"));

			for (String string : files) {
				File source = new File(string);
				String[] words = string.split(Pattern.quote("\\"));
				String name = words[words.length - 1];
				File dest = new File(
						"C:\\Users\\alexf\\git\\siw2021\\Siw2021\\WebContent\\loadedFiles\\"+ name);
				int pages = 0;
				if (name.endsWith(".pdf")) {
					try {
						PDDocument pdf = PDDocument.load(source);
						pages = pdf.getNumberOfPages();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					RequestDispatcher rd = req.getRequestDispatcher("loadError.html");
					rd.forward(req, resp);
					return;
				}
				Files.copy(source.toPath(), dest.toPath());

				Ebook ebook = new Ebook();
				ebook.setNome(name);
				ebook.setGenere(gen);
				ebook.setCasaEditrice(ce);
				ebook.setTrama(trama);
				ebook.setAutore(autore);
				ebook.setN_pagine(pages);
				ebook.setPrezzo(gen.getPrezzo());
				ebook.setPrezzo_crediti(gen.getPrezzo_crediti() * pages);
				ebook.setPublisher(p);

				DBManager.getInstance().inserisciEbook(ebook);
				p.setnCrediti(p.getnCrediti() + ebook.getPrezzo_crediti());
				DBManager.getInstance().getPublisherDAO().update(p);
			}

			RequestDispatcher rd = req.getRequestDispatcher("nuoviC.html");
			rd.forward(req, resp);

		} else if (req.getSession().getAttribute("user") == null
				&& req.getSession().getAttribute("publisher") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("accessoNegatoSeNonAutenticatoP.html");
			rd.forward(req, resp);
		} else if (req.getSession().getAttribute("user") != null) {
			RequestDispatcher rd = req.getRequestDispatcher("accessoNegatoSeNonAutenticatoP.html");
			rd.forward(req, resp);
		}
	}
}
