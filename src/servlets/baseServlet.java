package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.NotAuthorizedException;

@SuppressWarnings("serial")
public abstract class baseServlet extends HttpServlet {	
	
	protected static final String mainPath = "/Juan_Gonzalez_TP_Integrador1";
	protected static final String loginPath = "/Views/Account/Login.jsp";
	protected static final String adminPath = "/Views/Admin/";
	protected static final String teacherPath = "/Views/Profesor/";
	
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
		response.setHeader("Location", request.getContextPath() + location);
		response.setStatus(302);
	}	

	protected final String getAction(HttpServletRequest request) {
		String baseURL = request.getRequestURL().toString();
		int start = baseURL.lastIndexOf(mainPath) + mainPath.length();
		String cleanPath = request.getRequestURL().substring(start);
//		req.setAttribute("currentLink", cleanPath);
		cleanPath = cleanPath.substring(cleanPath.lastIndexOf("/") + 1);

		return cleanPath;
	}

	protected final void mustBeLogged(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario == null || tipoUsuario <= 0) {
				redirect(request, response, mainPath + loginPath);
			}
			
		} catch(Exception e) {
			redirect(request, response, mainPath + loginPath);
		}		
	}
	
	protected final void mustBeAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			mustBeLogged(request, response);
			
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario != 1) {
				redirect(request, response, mainPath + teacherPath + "Index.jsp");
			}

		} catch (Exception e) {
			redirect(request, response, mainPath + loginPath);
		}
	}
	
	protected final void mustBeProfesor(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			mustBeLogged(request, response);
			
			Integer tipoUsuario = (Integer) request.getSession().getAttribute("idTipoUsuario");
			
			if (tipoUsuario != 2) {
				redirect(request, response, mainPath + adminPath + "Index.jsp");
			}
	
		} catch (Exception e) {
			redirect(request, response, mainPath + loginPath);
		}
	}
	
	protected final void cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
	}


	@SuppressWarnings("rawtypes")
	protected final void manejarFuncion(HttpServletRequest req, HttpServletResponse resp, Callable func) {
		try {	
			func.call();
		}  catch (NullPointerException | NotAuthorizedException e) {
			redirect(req, resp, mainPath + loginPath);
		} catch (Exception e) {
			resp.setStatus(402); // Unprocessable entity
		}
	}
	
}
