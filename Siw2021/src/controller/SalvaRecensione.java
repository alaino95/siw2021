package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Publisher;
import model.Recensione;
import model.User;
import persistence.DBManager;

public class SalvaRecensione extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("publisher") == null && req.getSession().getAttribute("user") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("accessoNegato.html");
			rd.forward(req, resp);
			return;
		} else {
			String testo = req.getParameter("recensione");
			int id_ebook = (int) req.getSession().getAttribute("id_ebook");
			String mail = (String) req.getSession().getAttribute("user");
			String username = (String)  req.getSession().getAttribute("publisher");
			if (mail != null) {
				User user = new User();
				user.setEmail(mail);
				Recensione recensione = new Recensione();
				recensione.setTesto(testo);
				recensione.setUser(user);
				recensione.setId_ebook(id_ebook);
				DBManager.getInstance().getRecensioneDAO().save(recensione);
			} else if (username != null) {
				Publisher publisher = new Publisher();
				publisher.setUsername(username);
				Recensione recensione = new Recensione();
				recensione.setTesto(testo);
				recensione.setPublisher(publisher);
				recensione.setId_ebook(id_ebook);
				DBManager.getInstance().getRecensioneDAO().save(recensione);
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("recensioneSalvata.html");
		rd.forward(req, resp);

	}
}
