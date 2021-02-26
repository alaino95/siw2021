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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ebook;
import model.PagamentoCrediti;
import model.Publisher;
import persistence.DBManager;

public class PagaCrediti extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("publisher")==null) {
			RequestDispatcher rd = req.getRequestDispatcher("pagamentoNegato.html");
			rd.forward(req, resp);
			System.out.println();
		}

		else {
			// String totali = req.getParameter("crediti");

			int totaleCrediti = Integer.parseInt((String) req.getSession().getAttribute("credito"));
			Publisher p = DBManager.getInstance().getPublisherDAO().findByPrimaryKey((String)req.getSession().getAttribute("publisher"));

			if (p.getnCrediti() >= totaleCrediti) {
				p.setnCrediti(p.getnCrediti() - totaleCrediti);

				DBManager.getInstance().getPublisherDAO().update(p);

				PagamentoCrediti pagamento = new PagamentoCrediti();
				pagamento.setUsername_publisher(p);
				pagamento.setnCrediti(totaleCrediti);
				pagamento.setEbook_da_scaricare((List<Ebook>) req.getSession().getAttribute("lista_ebook"));
				DBManager.getInstance().getPagamentoCreditiDAO().save(pagamento);

				String source = "C:\\Users\\alexf\\WebComputing\\Siw2021\\WebContent\\loadedFiles\\";
				List<Ebook> daScaricare = (List<Ebook>) req.getSession().getAttribute("lista_ebook");

				List<File> files = new ArrayList<File>();
				// files.add(downloadFile);
				for (Ebook ebook : daScaricare) {
					File file = new File(source + ebook.getNome());
					files.add(file);
				}

				req.getSession().setAttribute("prezzo", Integer.toString(0));
				req.getSession().setAttribute("credito", Integer.toString(0));
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

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("creditiNonSuff.html");
				rd.forward(req, resp);
			}

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
