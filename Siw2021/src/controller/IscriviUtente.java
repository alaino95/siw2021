package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Publisher;
import model.User;
import persistence.DBManager;

public class IscriviUtente extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String password1 = req.getParameter("password1");
		String username = req.getParameter("username");

		if (password.equals(password1)) {
			if (username == null) {
				username = req.getParameter("email");
				User user = DBManager.getInstance().getUserDAO().findByPrimaryKey(username);

				if (user == null) {
					User userr = new User();
					userr.setEmail(username);
					userr.setPassword(password);

					DBManager.getInstance().inserisciUser(userr);
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("IscrizioneError.html");
					rd.forward(req, resp);
					return;
				}
			} else {
				Publisher publisher = DBManager.getInstance().getPublisherDAO().findByPrimaryKey(username);
				if (publisher == null) {
					Publisher pub = new Publisher();
					pub.setUsername(username);
					pub.setPassword(password);
					pub.setnCrediti(0);

					DBManager.getInstance().inserisciPublisher(pub);
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("IscrizioneError.html");
					rd.forward(req, resp);
					return;
				}
			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("noMatchPassword.html");
			rd.forward(req, resp);
			return;
		}
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
	}
}
