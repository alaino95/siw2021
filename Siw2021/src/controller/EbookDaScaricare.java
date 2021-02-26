package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ebook;
import persistence.DBManager;

public class EbookDaScaricare extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id_ebook"));
		int prezzo = Integer.parseInt(req.getParameter("prezzo"));
		int crediti = Integer.parseInt(req.getParameter("crediti"));
		boolean aggiungi = Boolean.parseBoolean(req.getParameter("aggiungi"));
		
		List<Ebook> ebookDaScaricare = (List<Ebook>) req.getSession().getAttribute("lista_ebook");
		if (ebookDaScaricare == null)
			ebookDaScaricare = new ArrayList<Ebook>();

		int prezzoParziale = 0;
		if (req.getSession().getAttribute("prezzo") == null)
			prezzoParziale = 0;
		else {
			prezzoParziale = Integer.parseInt((String) req.getSession().getAttribute("prezzo"));
		}

		int creditiParziali = 0;
		if (req.getSession().getAttribute("credito") == null)
			creditiParziali = 0;
		else {
			creditiParziali = Integer.parseInt((String) req.getSession().getAttribute("credito"));
		}

		if (aggiungi) {
			Ebook e = DBManager.getInstance().getEbookDAO().findByPrimaryKey(id);
			ebookDaScaricare.add(e);
			prezzoParziale += prezzo;
			creditiParziali += crediti;
		} else {
			List<Ebook> ebookDaEliminare = new ArrayList<Ebook>();
			for (Ebook e : ebookDaScaricare) {
				if (e.getId() == id)
					ebookDaEliminare.add(e);
			}
			for (Ebook e : ebookDaEliminare) {
				ebookDaScaricare.remove(e);
			}
			prezzoParziale -= prezzo;
			creditiParziali -= crediti;
		}

		req.getSession().setAttribute("lista_ebook", ebookDaScaricare);
		req.getSession().setAttribute("prezzo", Integer.toString(prezzoParziale));
		req.getSession().setAttribute("credito", Integer.toString(creditiParziali));
	
		resp.getOutputStream().println("<textarea name=\"paragrafo\" class=\"paragrafo\" readonly>Totale in crediti: "
				+ creditiParziali + ",totale: " + prezzoParziale + " &euro;</textarea>");

	}
}
