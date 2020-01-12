package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Admin/*")
public class AdminServlet extends baseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	manejarFuncion(request, response, () -> {
    		mustBeAdmin(request, response);
    			    	
	    	String view = getView(request);
	    	String path = adminPath + view + ".jsp";
	    	
	    	request.getRequestDispatcher(path).forward(request, response);
	    	return null;
    	});
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
