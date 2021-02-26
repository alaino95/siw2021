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
import model.Publisher;
import persistence.DBManager;

public class VisualizzaProfiloPublisher extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey((String) req.getSession().getAttribute("publisher"));
		List<Ebook> Ebook = DBManager.getInstance().getEbookDAO().findAll();

		List<Ebook> daVisualizzare = new ArrayList<Ebook>();

		for (Ebook e : Ebook) {
			if (e.getPublisher().getUsername().equals(p.getUsername())) {
				System.out.println(e.getPublisher().getUsername());
				daVisualizzare.add(e);
			}
		}

		req.setAttribute("ebookProfilo", daVisualizzare);

		RequestDispatcher rd = req.getRequestDispatcher("profiloPublisher.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
