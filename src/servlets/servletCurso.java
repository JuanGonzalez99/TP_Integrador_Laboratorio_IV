package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CursoDAO;
import dominio.Curso;

@SuppressWarnings("serial")
@WebServlet("/servletCurso")
public class servletCurso extends baseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	mustBeLogged(request, response);
		
    	CursoDAO dao = new CursoDAO();
        List<Curso> list;
        
        String parameter = request.getParameter("idProfesor");
        if (parameter != null && !parameter.trim().isEmpty()) {
        	mustBeProfesor(request, response);        	
        	int idProfesor = Integer.parseInt(parameter);
        	list = dao.GetAllByProfesorId(idProfesor);
        } else {
        	mustBeAdmin(request, response);
        	list = dao.GetAll();
        }
        
        setResponseJson(response, list);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CursoDAO dao = new CursoDAO();
			Curso curso = new Gson().fromJson(request.getReader(), Curso.class);
			
			String validation = validate(curso);
			if (validation != null && !validation.isEmpty()) {
				setJson(false, validation, false);
			}
			else {
		        if (curso.getId() == 0) {
		        	int id = dao.Insert(curso);
		        	setJson(id > 0, id);
		        } else {
		        	setJson(dao.Update(curso));
		        }
			}

	        setResponse(response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al guardar el curso, intente nuevamente en unos minutos", true);

	        setResponse(response);
		}
	}


	protected String validate(Curso entidad) {
		
		if(entidad.getIdMateria() == 0) {
			return "Debe indicar la materia del curso";
		}
		if (entidad.getAnio() == 0) {
			return "Debe ingresar el año del curso";
		}
		if (entidad.getIdSemestre() == 0) {
			return "Debe indicar el semestre del curso";
		}
		if (entidad.getIdProfesor() == 0) {
			return "Debe indicar el profesor del curso";
		}
		
		return null;
	}

}
