package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SemestreDAO;
import dominio.Semestre;

@SuppressWarnings("serial")
@WebServlet("/servletSemestre")
public class servletSemestre extends baseServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String busqueda = request.getParameter("term");

		SemestreDAO dao = new SemestreDAO();
		List<Semestre> list = dao.GetAll();
		
        setResponseJson(response, list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
