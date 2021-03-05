package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ebook;
import persistence.DBManager;

public class DammiEbookPerAutore extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String autore = req.getParameter("searchBar");
		
		List<Ebook> ebooks = DBManager.getInstance().getEbookDAO().findAll();
		
		List<Ebook> perRicerca = new ArrayList<Ebook>();
		for (Ebook e : ebooks) {
			String autoreEbook = e.getAutore().toLowerCase();
			autore = autore.toLowerCase();
			if(autoreEbook.contains(autore))
				perRicerca.add(e);
		}
		if (perRicerca.isEmpty()) {
			RequestDispatcher rd = req.getRequestDispatcher("nessunR.html");
			rd.forward(req, resp);
		}
		else {
			req.setAttribute("trovati", perRicerca);
			RequestDispatcher rd = req.getRequestDispatcher("downloadFile.jsp");
			rd.forward(req, resp);
		}
	}
}
