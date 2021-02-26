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

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object utente = req.getSession().getAttribute("user");
		Object publisher = req.getSession().getAttribute("publisher");

		if (utente != null) {
			String isLogout = req.getParameter("logout");
			if (isLogout != null && isLogout.equals("true")) {
				req.getSession().invalidate();
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
			return;
		}

		if (publisher != null) {
			String isLogout = req.getParameter("logout");
			if (isLogout != null && isLogout.equals("true")) {
				req.getSession().invalidate();
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		if (username == null) {
			username = req.getParameter("email");

			User user = DBManager.getInstance().login(username, password);

			if (user == null) {
				RequestDispatcher rd = req.getRequestDispatcher("loginError.html");
				rd.forward(req, resp);
			}

			if (user != null) {
				req.getSession().setAttribute("user", username);
				RequestDispatcher rd = req.getRequestDispatcher("");
				rd.forward(req, resp);
				//System.out.println("username " + username);
			}
			return;
		}

		Publisher publisher = DBManager.getInstance().loginP(username, password);

		if (publisher == null) {
			RequestDispatcher rd = req.getRequestDispatcher("loginError.html");
			rd.forward(req, resp);
		}
		if (publisher != null) {
			req.getSession().setAttribute("publisher", username);
			RequestDispatcher rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		}

	}
}
