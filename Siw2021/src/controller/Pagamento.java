package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ebook;
import model.PagamentoCarta;
import model.Publisher;
import model.User;
import persistence.DBManager;

public class Pagamento extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String intest = req.getParameter("intestatario");
		String numero = req.getParameter("numero");
		String scadenza = req.getParameter("scadenza");
		String cvv = req.getParameter("cvv");
		int totaleP= Integer.parseInt((String) req.getSession().getAttribute("prezzo"));
		
		
		PagamentoCarta pagamento = new PagamentoCarta();
		pagamento.setIntestatario(intest);
		pagamento.setnCarta(Integer.parseInt(numero));
		pagamento.setDataScadenza(scadenza);
		pagamento.setCvv(Integer.parseInt(cvv));
		pagamento.setTotale(totaleP);

		// scelta tra publisher o user
		if (req.getSession().getAttribute("publisher") != null) {
			pagamento.setEbook_da_scaricare((List<Ebook>) req.getSession().getAttribute("lista_ebook"));
			Publisher p = new Publisher();
			p.setUsername((String)req.getSession().getAttribute("publisher"));
			pagamento.setUsername_publisher(p);
			DBManager.getInstance().inserisciPagamentoP(pagamento);
		} else if (req.getSession().getAttribute("user") != null) {
			pagamento.setEbook_da_scaricare((List<Ebook>) req.getSession().getAttribute("lista_ebook"));
			User u = new User();
			u.setEmail((String)req.getSession().getAttribute("user"));
			pagamento.setEmail_user(u);
			DBManager.getInstance().inserisciPagamentoU(pagamento);
		}

		//svuota carrello
		req.getSession().setAttribute("prezzo", Integer.toString(0));
		req.getSession().setAttribute("credito", Integer.toString(0));
		String source = "C:\\Users\\alexf\\WebComputing\\Siw2021\\WebContent\\loadedFiles\\";
		List<Ebook> daScaricare = (List<Ebook>) req.getSession().getAttribute("lista_ebook");

		List<File> files = new ArrayList<File>();

		for (Ebook ebook : daScaricare) {
			File file = new File(source + ebook.getNome());
			files.add(file);
		}

		resp.setContentType("application/zip");
		resp.setHeader("Content-Disposition", "attachment; filename=\"allfiles.zip\"");
		ZipOutputStream output = null;

		byte[] buffer = new byte[4096];

		output = new ZipOutputStream(new BufferedOutputStream(resp.getOutputStream(), 4096));
		for (File file : files) {
			InputStream input = new FileInputStream(file);
			output.putNextEntry(new ZipEntry(file.getName()));
			for (int bytesRead = -1; (bytesRead = input.read(buffer)) != -1;) {
				output.write(buffer, 0, bytesRead);
			}

			output.closeEntry();
			input.close();
		}
		output.close();
		daScaricare.clear();
		req.getSession().setAttribute("lista_ebook", daScaricare);
	}

}
