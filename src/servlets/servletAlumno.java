package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.AlumnoDAO;
import dominio.Alumno;

/**
 * Servlet implementation class someservlet
 */
@WebServlet("/servletAlumno")
public class servletAlumno extends baseServlet {
	private static final long serialVersionUID = 1L;

    public servletAlumno() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlumnoDAO dao = new AlumnoDAO();
        List<Alumno> list;
    	
    	String busqueda = request.getParameter("term");
    	if (busqueda != null && !busqueda.trim().isEmpty()) {
    		list = dao.GetAllByName(busqueda);
    	} else {
    		list = dao.GetAllEnabled();
    	}
        
        setResponseJson(response, list);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AlumnoDAO dao = new AlumnoDAO();
			Alumno alumno = new Gson().fromJson(request.getReader(), Alumno.class);
			
			String validation = validate(alumno);
			if (validation != null && !validation.isEmpty()) {
				setJson(false, validation, false);
			}
			else {
		        if (alumno.getId() == 0) {
		        	int id = dao.Insert(alumno);
		        	setJson(id > 0, id);
		        } else {
		        	setJson(dao.Update(alumno));
		        }
			}

	        setResponse(response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al guardar el alumno, intente nuevamente en unos minutos", true);

	        setResponse(response);
		}
	}
	
	protected String validate(Alumno entidad) {

		if (entidad.getNombre() == null || entidad.getNombre().trim().isEmpty()) {
			return "Debe ingresar el nombre del alumno";
		}
		if (entidad.getApellido() == null || entidad.getApellido().trim().isEmpty()) {
			return "Debe ingresar el apellido del alumno";
		}
		if (entidad.getFechaNacimiento() == null) {
			return "Debe ingresar la fecha de nacimiento del alumno";
		}
		if (entidad.getFechaNacimiento().isAfter(LocalDate.now().minusYears(16))) {
			return "No se pueden registrar menores de 16 años como alumnos";
		}
		if (entidad.getDni() == 0) {
			return "Debe ingresar el DNI del alumno";
		}
		if (entidad.getDireccion() == null || entidad.getDireccion().trim().isEmpty()) {
			return "Debe ingresar la direccion del alumno";
		}
		if (entidad.getIdLocalidad() == 0) {
			return "Debe ingresar la localidad del alumno";
		}
		if (entidad.getEmail() == null || entidad.getEmail().trim().isEmpty()) {
			return "Debe ingresar el mail del alumno";
		}
		if (entidad.getTelefono() == 0) {
			return "Debe ingresar el telefono del alumno";
		}
		
		return null;
	}
} 
