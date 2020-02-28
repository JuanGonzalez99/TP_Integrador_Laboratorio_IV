package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Curso;

public class CursoDAO implements IDao<Curso> {

	private static final String table_name = "cursos";
	private static final String insert = "INSERT INTO " + table_name + "(idMateria, anio, idSemestre, idProfesor) VALUES(?, ?, ?, ?)";
	private static final String update = "UPDATE " + table_name + " SET idMateria = ?, anio = ?, idSemestre = ?, idProfesor = ? WHERE id = ?";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getallbyprofesorid = "SELECT * FROM " + table_name + " WHERE idProfesor = ?";	
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";
	
	@Override
	public int Insert(Curso inserted) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int result = -1;
		try
		{
			statement = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, inserted.getIdMateria());
			statement.setInt(2, inserted.getAnio());
			statement.setInt(3, inserted.getIdSemestre());
			statement.setInt(4, inserted.getIdProfesor());
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
	public boolean Update(Curso updated) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean result = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setInt(1, updated.getIdMateria());
			statement.setInt(2, updated.getAnio());
			statement.setInt(3, updated.getIdSemestre());
			statement.setInt(4, updated.getIdProfesor());
			statement.setInt(5, updated.getId());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Curso> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Curso> list = new ArrayList<Curso>();
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

	public List<Curso> GetAllByProfesorId(int idProfesor) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Curso> list = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getallbyprofesorid);
			statement.setInt(1, idProfesor);
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
	public List<Curso> GetAllEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> GetAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Curso entidad = new Curso();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getbyid);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				entidad = Make(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return entidad;
	}

	@Override
	public Curso Make(ResultSet resultSet) throws SQLException {
		Curso entidad = new Curso();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setAnio(resultSet.getInt("anio"));
		
		MateriaDAO daoMateria = new MateriaDAO();
		ProfesorDAO daoProfesor = new ProfesorDAO();
		SemestreDAO daoSemestre = new SemestreDAO();
		int idMateria = resultSet.getInt("idMateria");
		int idProfesor = resultSet.getInt("idProfesor");
		int idSemestre = resultSet.getInt("idSemestre");
		
		entidad.setIdMateria(idMateria);
		entidad.setMateria(daoMateria.GetById(idMateria));
		entidad.setIdSemestre(idSemestre);
		entidad.setSemestre(daoSemestre.GetById(idSemestre));
		entidad.setIdProfesor(idProfesor);
		entidad.setProfesor(daoProfesor.GetById(idProfesor));
		
		return entidad;
	}

}
