package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.AlumnoDAO;

/**
 * Servlet implementation class deleteAlumno
 */
@WebServlet("/deleteAlumno")
public class deleteAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> dict = new HashMap<String, Object>();
		
		try {
			AlumnoDAO dao = new AlumnoDAO();
			int id = new Gson().fromJson(request.getReader(), int.class);
			
	        dict.put("Result", dao.Delete(id));

	        String json = new Gson().toJson(dict);
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			
			if (dict.get("Result") == null) {
				dict.put("Result", false);
			}
			dict.put("Error", "Ha ocurrido un error al guardar el alumno, intente nuevamente en unos minutos");

			String json = new Gson().toJson(dict);
		    response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);
		}
	}

}
