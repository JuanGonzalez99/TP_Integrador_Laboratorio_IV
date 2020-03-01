package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dominio.CursoAlumno;

public class CursoAlumnoDAO implements IDao<CursoAlumno> {

	private static final String table_name = "cursos_alumnos";
	private static final String insert = "INSERT INTO " + table_name + "(idCurso, idAlumno) VALUES(?, ?)";
	private static final String update = "UPDATE " + table_name + " SET parcial1 = ?, parcial2 = ?, recuperatorio1 = ?, recuperatorio2 = ?, idEstado = ? WHERE id = ?";
	private static final String delete = "UPDATE " + table_name + " SET deshabilitado = 1 WHERE id = ?";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getallenabled = "SELECT * FROM " + table_name + " WHERE deshabilitado = 0";
	private static final String getallbycursoid = "SELECT * FROM " + table_name + " WHERE idCurso = ? AND deshabilitado = 0";
	private static final String getcountbyalumnoandcursoid = "SELECT COUNT(*) FROM " + table_name + " WHERE idAlumno = ? AND idCurso = ? AND deshabilitado = 0";

	@Override
	public int Insert(CursoAlumno inserted) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int result = -1;
		try
		{
			statement = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, inserted.getIdCurso());
			statement.setInt(2, inserted.getIdAlumno());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                result = generatedKeys.getInt(1);
		            } else {
		                throw new SQLException("Creating failed, no ID obtained.");
		            }
		        }
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public boolean Update(CursoAlumno updated) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean result = false;
		try
		{
			statement = conexion.prepareStatement(update);

			if (updated.getParcial1() != null)
				statement.setInt(1, updated.getParcial1());
			else
				statement.setNull(1, Types.INTEGER);

			if (updated.getParcial2() != null)
				statement.setInt(2, updated.getParcial2());
			else
				statement.setNull(2, Types.INTEGER);
			
			if (updated.getRecuperatorio1() != null)
				statement.setInt(3, updated.getRecuperatorio1());
			else
				statement.setNull(3, Types.INTEGER);
			
			if (updated.getRecuperatorio2() != null)
				statement.setInt(4, updated.getRecuperatorio2());
			else
				statement.setNull(4, Types.INTEGER);
			
			if (updated.getIdEstado() != null)
				statement.setInt(5, updated.getIdEstado());
			else
				statement.setNull(5, Types.INTEGER);
				
			statement.setInt(6, updated.getId());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				result = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public boolean Delete(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean result = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, id);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				result = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CursoAlumno> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CursoAlumno> list = new ArrayList<CursoAlumno>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				list.add(Make(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CursoAlumno> GetAllEnabled() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CursoAlumno> list = new ArrayList<CursoAlumno>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getallenabled);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				list.add(Make(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}

	public List<CursoAlumno> GetAllByCursoId(int idCurso) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CursoAlumno> list = new ArrayList<CursoAlumno>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getallbycursoid);
			statement.setInt(1, idCurso);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				list.add(Make(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public int GetCountByAlumnoAndCursoId(int alumnoId, int cursoId) {
		int result = -1;

		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getcountbyalumnoandcursoid);
			statement.setInt(1, alumnoId);
			statement.setInt(2, cursoId);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				result = resultSet.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		
		return result;
	}

	@Override
	public List<CursoAlumno> GetAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CursoAlumno GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CursoAlumno Make(ResultSet resultSet) throws SQLException {
		CursoAlumno entidad = new CursoAlumno();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setParcial1((Integer)resultSet.getObject("parcial1"));
		entidad.setParcial2((Integer)resultSet.getObject("parcial2"));
		entidad.setRecuperatorio1((Integer)resultSet.getObject("recuperatorio1"));
		entidad.setRecuperatorio2((Integer)resultSet.getObject("recuperatorio2"));
		entidad.setDeshabilitado(resultSet.getBoolean("deshabilitado"));
		
		int idCurso = resultSet.getInt("idCurso");
		int idAlumno = resultSet.getInt("idAlumno");
		Integer idEstado = (Integer)resultSet.getObject("idEstado");
		CursoDAO daoCurso = new CursoDAO();
		AlumnoDAO daoAlumno = new AlumnoDAO();
		EstadoDAO daoEstado = new EstadoDAO();
		
		entidad.setIdCurso(idCurso);
		entidad.setCurso(daoCurso.GetById(idCurso));
		entidad.setIdAlumno(idAlumno);
		entidad.setAlumno(daoAlumno.GetById(idAlumno));
		entidad.setIdEstado(idEstado);
		entidad.setEstado(daoEstado.GetById(idEstado != null ? idEstado : 0));
		
		return entidad;
	}

}
