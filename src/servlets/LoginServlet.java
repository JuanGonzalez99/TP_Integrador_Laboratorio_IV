package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dominio.Usuario;


@SuppressWarnings("serial")
@WebServlet("/Login")
public class LoginServlet extends baseServlet {

	private final String loginPath = "/Views/Account/Login.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(loginPath).forward(request, response);
//		redirect(request, response, loginPath);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("btnIngresar") != null) {
			UsuarioDAO dao = new UsuarioDAO();
			String email = request.getParameter("txtEmail");
			String password = request.getParameter("txtPassword");
			
			Usuario usuario = dao.GetByEmailByPass(email, password);
			if (usuario != null) {
	            request.getSession().setAttribute("id", usuario.getId());
	            request.getSession().setAttribute("email", email);
	            request.getSession().setAttribute("idTipoUsuario", usuario.getIdTipoUsuario());
	            
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
		            request.getSession().setAttribute("idProfesor", usuario.getIdProfesor());
	            	location = "/Profesor/" + "Index";
	            }
	            redirect(request, response, location);
			} else {
				request.setAttribute("loginError", 1);
				request.getRequestDispatcher(loginPath).forward(request, response);
			}
		}
	}

}
