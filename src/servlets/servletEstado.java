package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import dominio.Estado;

@SuppressWarnings("serial")
@WebServlet("/servletEstado")
public class servletEstado extends baseServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String busqueda = request.getParameter("term");

		EstadoDAO dao = new EstadoDAO();
		List<Estado> list = dao.GetAll();
		
        setResponseJson(response, list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
