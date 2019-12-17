package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public abstract class baseServlet extends HttpServlet {	
	
	protected static final String mainPath = "/Juan_Gonzalez_TP_Integrador1";
	protected static final String loginPath = "Views/Account/Login.jsp";
	protected static final String indexAdminPath = "Views/Admin/Index.jsp";
	protected static final String indexProfesorPath = "Views/Profesor/Index.jsp";
	
	protected Map<String, Object> jsonObj;
	
	public baseServlet() {
		jsonObj = new HashMap<String, Object>();
	}
	
	
	protected void setJson(boolean result) {
		jsonObj.put("Result", result);
	}
	
	protected void setJson(boolean result, String info, boolean isError) {
		setJson(result);
		if (!isError)
		{
			jsonObj.put("Info", info);
			jsonObj.remove("Error");
		}
		else
		{
			jsonObj.put("Error", info);
			jsonObj.remove("Info");
		}
	}
	
	protected void setJson(boolean result, int id) {
		setJson(result);
		jsonObj.put("Id", id);
	}
	
	protected void setJson(boolean result, int id, String info, boolean isError) {
		setJson(result, info, isError);
		jsonObj.put("Id", id);
	}
	
	
	protected void setResponse(HttpServletResponse response) throws IOException {
        setResponseJson(response, jsonObj);
	}
	
	protected void setResponseJson(HttpServletResponse response, Object json) throws IOException {
		String jsonString = new Gson().toJson(json);
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
	}
	
	
	protected final void redirect(HttpServletRequest request, HttpServletResponse response, String location) {
		response.setHeader("Location", request.getContextPath() + "/" + location);
		response.setStatus(302);
	}

	protected final void mustBeLogged(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario == null || tipoUsuario <= 0) {
				redirect(request, response, loginPath);
			}
			
		} catch(Exception e) {
			redirect(request, response, loginPath);
		}		
	}
	
	protected final void mustBeAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			mustBeLogged(request, response);
			
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario != 1) {
				redirect(request, response, indexProfesorPath);
			}

		} catch (Exception e) {
			redirect(request, response, loginPath);
		}
	}
	
	protected final void mustBeProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			mustBeLogged(request, response);
			
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario != 2) {
				redirect(request, response, indexAdminPath);
			}
	
		} catch (Exception e) {
			redirect(request, response, loginPath);
		}
	}
	
	protected final void cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
}
