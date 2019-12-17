package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dominio.Usuario;


@WebServlet("/LoginServlet")
public class LoginServlet extends baseServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.getRequestDispatcher("/Views/Account/Login.jsp").forward(request, response);
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
	            request.getSession().setAttribute("nombre", usuario.getNombre());
	            request.getSession().setAttribute("apellido", usuario.getApellido());
	            
	            String location = loginPath;
	            Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
	            if (tipoUsuario == 1) {
	            	location = indexAdminPath;
	            } else if (tipoUsuario == 2) {
	            	location = indexProfesorPath;
	            }
	            request.setAttribute("Loggear", true);
	            redirect(request, response, location);
			} else {
				request.setAttribute("Loggear", false);
				request.setAttribute("error", "Usuario o contraseña incorrectos");
//				RequestDispatcher rd = request.getRequestDispatcher("/Views/Account/Login.jsp");
//				rd.forward(request, response);
			}
		}
	}

}
