package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CursoAlumnoDAO;
import dominio.CursoAlumno;

@SuppressWarnings("serial")
@WebServlet("/servletCursoAlumno")
public class servletCursoAlumno extends baseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursoAlumnoDAO dao = new CursoAlumnoDAO();
	    List<CursoAlumno> list;        
	
		String parameter = request.getParameter("idCurso");
		if (parameter != null && !parameter.trim().isEmpty()) {
			int idCurso = Integer.parseInt(parameter);
			list = dao.GetAllByCursoId(idCurso);
		} else {    		
			list = dao.GetAllEnabled();
		}
	    
	    setResponseJson(response, list);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CursoAlumnoDAO dao = new CursoAlumnoDAO();
			CursoAlumno cursoAlumno = new Gson().fromJson(request.getReader(), CursoAlumno.class);
			
			String validation = validate(cursoAlumno);
			if (validation != null && !validation.isEmpty()) {
				setJson(false, validation, false);
			}
			else {
		        if (cursoAlumno.getId() == 0) {
		        	int id = dao.Insert(cursoAlumno);
		        	setJson(id > 0, id);
		        } else {
		        	setJson(dao.Update(cursoAlumno));
		        }
			}

	        setResponse(response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al guardar el curso, intente nuevamente en unos minutos", true);

	        setResponse(response);
		}
	}


	protected String validate(CursoAlumno entidad) {
		
		if(entidad.getIdCurso() == 0) {
			return "Debe indicar el curso";
		}
		if (entidad.getIdAlumno() == 0) {
			return "Debe indicar el alumno";
		}
		if (entidad.getParcial1() < 0 || entidad.getParcial1() > 10
			|| entidad.getParcial2() < 0 || entidad.getParcial2() > 10
			|| entidad.getRecuperatorio1() < 0 || entidad.getRecuperatorio1() > 10
			|| entidad.getRecuperatorio2() < 0 || entidad.getRecuperatorio2() > 10) {
			return "Las notas deben estar entre 1 y 10";
		}
		if (entidad.getId() == 0 && new CursoAlumnoDAO().GetCountByAlumnoAndCursoId(entidad.getIdAlumno(), entidad.getIdCurso()) > 0) {
			return "El alumno ya se encuentra inscripto en este curso";
		}
		
		return null;
	}

}
