package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Profesor/*")
public class TeacherServlet extends baseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	manejarFuncion(request, response, () -> {
    		mustBeProfesor(request, response);
    		
	    	String view = getView(request);
	    	String path = teacherPath + view + ".jsp";

	    	request.getRequestDispatcher(path).forward(request, response);
	    	return null;
    	});
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
