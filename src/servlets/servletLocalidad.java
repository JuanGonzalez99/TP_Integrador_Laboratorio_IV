package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.LocalidadDAO;
import dominio.Localidad;

@WebServlet("/servletLocalidad")
public class servletLocalidad extends baseServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String busqueda = request.getParameter("term");		

		LocalidadDAO dao = new LocalidadDAO();
		List<Localidad> list = dao.GetAllByName(busqueda);
		
        setResponseJson(response, list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
