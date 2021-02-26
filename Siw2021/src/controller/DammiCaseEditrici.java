package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CasaEditrice;
import persistence.DBManager;

public class DammiCaseEditrici extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CasaEditrice> caseEditrici = DBManager.getInstance().dammiCaseEditrici();
		resp.getOutputStream().println("<option disabled selected value=\"\"name=\"obbligatorio\"></option>");	
		for (CasaEditrice e: caseEditrici) {
			
			resp.getOutputStream().println("<option value=\"" + e.getNome() + "\">"+e.getNome()+"</option>");	
		}
	}
}
