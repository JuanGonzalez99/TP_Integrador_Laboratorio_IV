package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProfesorDAO;

@SuppressWarnings("serial")
@WebServlet("/deleteProfesor")
public class deleteProfesor extends baseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProfesorDAO dao = new ProfesorDAO();
			int id = new Gson().fromJson(request.getReader(), int.class);
			
			setJson(dao.Delete(id));

			setResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al eliminar el profesor, intente nuevamente en unos minutos", true);

			setResponse(response);
		}
	}
	
}
