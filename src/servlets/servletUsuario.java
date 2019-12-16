package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UsuarioDAO;
import dominio.Usuario;

@WebServlet("/servletUsuario")
public class servletUsuario extends baseServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Gson().fromJson(request.getReader(), Usuario.class);
		
		setJson(dao.Insert(usuario) >= 0);
		
		setResponse(response);
	}

}
