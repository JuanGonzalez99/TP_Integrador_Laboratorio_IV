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

    	Integer materiaId = Integer.parseInt(request.getParameter("materiaId"));
    	Integer desde = Integer.parseInt(request.getParameter("desde"));
    	Integer hasta = Integer.parseInt(request.getParameter("hasta"));
    	
    	ReportDAO dao = new ReportDAO();
    	List<ReportViewModel> reports = dao.GetReport(materiaId, desde, hasta);

    	setResponseJson(response, reports);
    	
    }
	
}
