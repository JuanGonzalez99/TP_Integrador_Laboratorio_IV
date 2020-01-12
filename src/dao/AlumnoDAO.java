package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dominio.Alumno;

public class AlumnoDAO implements IDao<Alumno> {

	private static final String table_name = "alumnos";
	private static final String insert = "INSERT INTO " + table_name + "(nombre, apellido, fechaNacimiento, dni, direccion, idLocalidad, email, telefono) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE " + table_name + " SET nombre = ?, apellido = ?, fechaNacimiento = ?, dni = ?, direccion = ?, idLocalidad = ?, email = ?, telefono = ? WHERE id = ?";
	private static final String delete = "UPDATE " + table_name + " SET deshabilitado = 1 WHERE id = ?";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getallenabled = "SELECT * FROM " + table_name + " WHERE deshabilitado = 0";
	private static final String getbynombre = "SELECT * FROM " + table_name + " WHERE deshabilitado = 0 AND ( nombre LIKE ? OR apellido LIKE ? )";
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";

	@Override
	public int Insert(Alumno inserted) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int result = -1;
		try
		{
			statement = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, inserted.getNombre());
			statement.setString(2, inserted.getApellido());
			statement.setDate(3, Date.valueOf(inserted.getFechaNacimiento()));
			statement.setInt(4, inserted.getDni());
			statement.setString(5, inserted.getDireccion());
			statement.setInt(6, inserted.getIdLocalidad());
			statement.setString(7, inserted.getEmail());
			statement.setInt(8, inserted.getTelefono());
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
	public boolean Update(Alumno updated) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean result = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, updated.getNombre());
			statement.setString(2, updated.getApellido());
			statement.setDate(3, Date.valueOf(updated.getFechaNacimiento()));
			statement.setInt(4, updated.getDni());
			statement.setString(5, updated.getDireccion());
			statement.setInt(6, updated.getIdLocalidad());
			statement.setString(7, updated.getEmail());
			statement.setInt(8, updated.getTelefono());
			statement.setInt(9, updated.getId());
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
	public List<Alumno> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Alumno> list = new ArrayList<Alumno>();
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
	public List<Alumno> GetAllEnabled() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Alumno> list = new ArrayList<Alumno>();
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
	
	@Override
	public List<Alumno> GetAllByName(String name) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Alumno> list = new ArrayList<Alumno>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getbynombre);
			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");
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
	public Alumno GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Alumno entidad = new Alumno();
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
	public Alumno Make(ResultSet resultSet) throws SQLException {
		Alumno entidad = new Alumno();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setNombre(resultSet.getString("nombre"));
		entidad.setApellido(resultSet.getString("apellido"));
		entidad.setFechaNacimiento(resultSet.getDate("fechaNacimiento").toLocalDate());
		entidad.setDni(resultSet.getInt("dni"));
		entidad.setDireccion(resultSet.getString("direccion"));
		entidad.setEmail(resultSet.getString("email"));
		entidad.setTelefono(resultSet.getInt("telefono"));
		entidad.setDeshabilitado(resultSet.getBoolean("deshabilitado"));

		LocalidadDAO locDAO = new LocalidadDAO();		
		int idLocalidad = resultSet.getInt("idLocalidad");

		entidad.setIdLocalidad(idLocalidad);
		entidad.setLocalidad(locDAO.GetById(idLocalidad));
		
		return entidad;
	}

}
