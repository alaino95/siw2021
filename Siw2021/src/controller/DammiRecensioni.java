package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Recensione;
import persistence.DBManager;

public class DammiRecensioni extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id_ebook"));
		req.getSession().setAttribute("id_ebook", id);

		List<Recensione> recensioni = DBManager.getInstance().dammiRecensioneDiEbook(id);
		req.setAttribute("recensioni", recensioni);
		resp.getOutputStream().println("<form action=\"salvaRecensioni\" method=\"post\">");
		resp.getOutputStream().println(
				"<textarea class=\"recensione\" name=\"recensione\" placeholder=\"Inserisci una recensione\" required></textarea><br>");
		resp.getOutputStream().println("<input type=\"submit\" value=\"Aggiungi recensione\" class=\"rec\" >");
		resp.getOutputStream().println("</form>");
		if (recensioni.isEmpty())
			resp.getOutputStream().println("<p>Nessuna recensione esistente.</p>");
		for (Recensione r : recensioni) {
			resp.getOutputStream().println("<p>" + r.getTesto() + "</p>");
		}
	}
}
