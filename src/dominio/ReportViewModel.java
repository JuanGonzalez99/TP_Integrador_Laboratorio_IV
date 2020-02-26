package dominio;

public class ReportViewModel {
	
	private String curso;
	private int alumnosRegular;
	private int alumnosLibres;
	private int alumnosTotales;
	private float porcAlumRegular;
	private float porcAlumLibres;
	
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getAlumnosRegular() {
		return alumnosRegular;
	}
	public void setAlumnosRegular(int alumnosRegular) {
		this.alumnosRegular = alumnosRegular;
	}
	public int getAlumnosLibres() {
		return alumnosLibres;
	}
	public void setAlumnosLibres(int alumnosLibres) {
		this.alumnosLibres = alumnosLibres;
	}
	public int getAlumnosTotales() {
		return alumnosTotales;
	}
	public void setAlumnosTotales(int alumnosTotales) {
		this.alumnosTotales = alumnosTotales;
	}
	public float getPorcAlumRegular() {
		return porcAlumRegular;
	}
	public void setPorcAlumRegular(float porcAlumRegular) {
		this.porcAlumRegular = porcAlumRegular;
	}
	public float getPorcAlumLibres() {
		return porcAlumLibres;
	}
	public void setPorcAlumLibres(float porcAlumLibres) {
		this.porcAlumLibres = porcAlumLibres;
	}
	
}
