package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProfesorDAO;
import dominio.Profesor;

@WebServlet("/servletProfesor")
public class servletProfesor extends baseServlet {
	private static final long serialVersionUID = 1L;

	public servletProfesor() {
		super();
	}
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProfesorDAO dao = new ProfesorDAO();
    	List<Profesor> list;
    	
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
			ProfesorDAO dao = new ProfesorDAO();
			Profesor profesor = new Gson().fromJson(request.getReader(), Profesor.class);
			
			String validation = validate(profesor);
			if (validation != null && !validation.isEmpty()) {
				setJson(false, validation, false);
			}
			else {
		        if (profesor.getId() == 0) {
		        	int id = dao.Insert(profesor);
		        	setJson(id > 0, id);
		        } else {
		        	setJson(dao.Update(profesor));
		        }
			}

	        setResponse(response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			setJson(false, "Ha ocurrido un error al guardar el profesor, intente nuevamente en unos minutos", true);

	        setResponse(response);
		}
	}
	
	protected String validate(Profesor entidad) {

		if (entidad.getNombre() == null || entidad.getNombre().trim().isEmpty()) {
			return "Debe ingresar el nombre del profesor";
		}
		if (entidad.getApellido() == null || entidad.getApellido().trim().isEmpty()) {
			return "Debe ingresar el apellido del profesor";
		}
		if (entidad.getFechaNacimiento() == null) {
			return "Debe ingresar la fecha de nacimiento del profesor";
		}
		if (entidad.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
			return "No se pueden registrar menores de 18 años como profesores";
		}
		if (entidad.getDni() == 0) {
			return "Debe ingresar el DNI del alumno";
		}
		if (entidad.getDireccion() == null || entidad.getDireccion().trim().isEmpty()) {
			return "Debe ingresar la direccion del profesor";
		}
		if (entidad.getIdLocalidad() == 0) {
			return "Debe ingresar la localidad del profesor";
		}
		if (entidad.getEmail() == null || entidad.getEmail().trim().isEmpty()) {
			return "Debe ingresar el mail del profesor";
		}
		if (entidad.getTelefono() == 0) {
			return "Debe ingresar el telefono del profesor";
		}
		
		return null;
	}

}
