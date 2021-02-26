package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ebook;
import persistence.DBManager;

public class DammiEbook extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {			
		String casaEditrice = req.getParameter("caseeditrici");
		String genere = req.getParameter("generi");
		List<Ebook> ebooks = DBManager.getInstance().dammiEbookDaGenereECasaEditrice(casaEditrice,genere);
		req.setAttribute("ebooks", ebooks);
		
		if (ebooks.isEmpty()) {
			RequestDispatcher rd = req.getRequestDispatcher("nessunR.html");
			rd.forward(req, resp);
		}
		else {
		RequestDispatcher rd = req.getRequestDispatcher("downloadFile.jsp");
		rd.forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}