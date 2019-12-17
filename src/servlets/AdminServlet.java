package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/admin/*")
public class AdminServlet extends baseServlet {
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	mustBeAdmin(request, response);
    	
    	int x = 0, y = x;
    	x = y;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
