package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportDAO;
import dominio.ReportViewModel;

@SuppressWarnings("serial")
@WebServlet("/servletReporte")
public class servletReporte extends baseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Integer materiaId = null;
    	Integer desde = null;
    	Integer hasta = null;
    	
    	String param1 = request.getParameter("materiaId");
    	String param2 = request.getParameter("desde");
    	String param3 = request.getParameter("hasta");
    	
    	if (param1 != null && !param1.isEmpty())
    		materiaId = Integer.parseInt(param1);
    	if (param2 != null && !param2.isEmpty())
    		desde = Integer.parseInt(param2);
    	if (param3 != null && !param3.isEmpty())
    		hasta = Integer.parseInt(param3);
    	
    	ReportDAO dao = new ReportDAO();
    	List<ReportViewModel> reports = dao.GetReport(materiaId, desde, hasta);

    	setResponseJson(response, reports);
    	
    }
	
}
