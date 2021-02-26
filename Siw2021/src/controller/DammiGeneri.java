package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Genere;
import persistence.DBManager;

public class DammiGeneri extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Genere> generi = DBManager.getInstance().dammiGeneri();
		resp.getOutputStream().println("<option disabled selected value=\"\"name=\"obbligatorio\"></option>");	
		for (Genere g: generi) {
			resp.getOutputStream().println("<option value=\"" + g.getTipo() + "\">"+g.getTipo()+"</option>");
		}
	}
}
