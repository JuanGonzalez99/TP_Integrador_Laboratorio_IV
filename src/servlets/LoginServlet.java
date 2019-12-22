package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dominio.Usuario;


@SuppressWarnings("serial")
@WebServlet("/Login")
public class LoginServlet extends baseServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		redirect(request, response, loginPath);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("btnIngresar") != null) {
			UsuarioDAO dao = new UsuarioDAO();
			String email = request.getParameter("txtEmail");
			String password = request.getParameter("txtPassword");
			
			Usuario usuario = dao.GetByEmailByPass(email, password);
			if (usuario != null) {
	            request.getSession().setAttribute("email", email);
	            request.getSession().setAttribute("idTipoUsuario", usuario.getIdTipoUsuario());
//	            request.getSession().setAttribute("nombre", usuario.getNombre());
//	            request.getSession().setAttribute("apellido", usuario.getApellido());
	            
	            String username = email.split("\\@")[0];
	            if (!usuario.getApellido().isEmpty() && !usuario.getNombre().isEmpty()) {
	            	username = usuario.getNombre() + " " + usuario.getApellido();
	            }
	            request.getSession().setAttribute("userName", username);
	            
	            String location = loginPath;
	            int tipoUsuario = (int) usuario.getIdTipoUsuario();
	            if (tipoUsuario == 1) {
	            	location = "/Admin/" + "Index";
	            } else if (tipoUsuario == 2) {
	            	location = "/Profesor/" + "Cursos";
	            }
	            redirect(request, response, location);
			} else {
//				request.getRequestDispatcher(loginPath).forward(request, response);
				response.sendRedirect(mainPath + loginPath+"?loginError=1");
			}
		}
	}

}
