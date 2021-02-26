package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadToPayment extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("publisher") == null && req.getSession().getAttribute("user") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("pagamentoNegato.html");
			rd.forward(req, resp);

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("payment.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
