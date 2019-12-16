package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public abstract class baseServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
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
//		response.setHeader("Cache-Control", "no-cache"); // Para que no redireccione siempre
//		response.setStatus(302);
	}

}
