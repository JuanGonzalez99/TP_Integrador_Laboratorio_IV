package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProfesorDAO;
import dao.UsuarioDAO;


@SuppressWarnings("serial")
@WebServlet("/deleteProfesor")
public class deleteProfesor extends baseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = new Gson().fromJson(request.getReader(), int.class);
			ProfesorDAO dao = new ProfesorDAO();
			UsuarioDAO daoUsu = new UsuarioDAO();
			boolean resultProf = dao.Delete(id);
			boolean resultUsu = daoUsu.DeleteByProfesorId(id);
			setJson(resultProf && resultUsu);

			setResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al eliminar el profesor, intente nuevamente en unos minutos", true);

			setResponse(response);
		}
	}
	
}
