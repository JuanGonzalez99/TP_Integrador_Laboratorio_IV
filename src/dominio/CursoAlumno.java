package dominio;

public class CursoAlumno {
	
	private int id;
	private int idCurso;
	private Curso curso;
	private int idAlumno;
	private Alumno alumno;
	private Integer parcial1;
	private Integer parcial2;
	private Integer recuperatorio1;
	private Integer recuperatorio2;
	private Integer idEstado;
	private Estado estado;
	private boolean deshabilitado;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Integer getParcial1() {
		return parcial1;
	}
	public void setParcial1(Integer parcial1) {
		this.parcial1 = parcial1;
	}
	public Integer getParcial2() {
		return parcial2;
	}
	public void setParcial2(Integer parcial2) {
		this.parcial2 = parcial2;
	}
	public Integer getRecuperatorio1() {
		return recuperatorio1;
	}
	public void setRecuperatorio1(Integer recuperatorio1) {
		this.recuperatorio1 = recuperatorio1;
	}
	public Integer getRecuperatorio2() {
		return recuperatorio2;
	}
	public void setRecuperatorio2(Integer recuperatorio2) {
		this.recuperatorio2 = recuperatorio2;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public boolean isDeshabilitado() {
		return deshabilitado;
	}
	public void setDeshabilitado(boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}
	
	
}
