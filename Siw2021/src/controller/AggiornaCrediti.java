package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Publisher;
import persistence.DBManager;

public class AggiornaCrediti extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey((String)req.getSession().getAttribute("publisher"));
		req.setAttribute("crediti", p.getnCrediti());
		RequestDispatcher rd = req.getRequestDispatcher("profiloPublisher.jsp");
		rd.forward(req, resp);
	}
}