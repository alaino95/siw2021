package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ebook;
import model.PagamentoCarta;
import model.Publisher;
import persistence.DBManager;

public class EliminaEbook extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("publisher")!=null) {
			String nomeEbook = req.getParameter("nomeebook");
			Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey((String)req.getSession().getAttribute("publisher"));

			List<Ebook> Ebook = DBManager.getInstance().getEbookDAO().findAll();
			List<Ebook> daEliminare = new ArrayList<Ebook>();
			for (Ebook e : Ebook) {
				if (e.getPublisher().getUsername().equals(p.getUsername())) {
					daEliminare.add(e);
					File f= new File("C:\\Users\\Alessandro Laino\\git\\siw2021\\Siw2021\\WebContent\\loadedFiles\\"+e.getNome());
					f.delete();
					DBManager.getInstance().getEbookDAO().delete(e);
				}
			}
			if (daEliminare.isEmpty()) {
				RequestDispatcher rd = req.getRequestDispatcher("nessunEbook.html");
				rd.forward(req, resp);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("eliminaEbook.html");
				rd.forward(req, resp);
			}

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("noUser.html");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}