package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dominio.Usuario;

public class UsuarioDAO implements IDao<Usuario>{

	private static final String table_name = "usuarios";
	private static final String insert = "INSERT INTO " + table_name + "(email, contrasenia, idTipoUsuario, idProfesor, nombre, apellido) VALUES (?, MD5( ? ), ?, ?, ?, ?)";
	private static final String deletebyprof = "UPDATE " + table_name + " SET deshabilitado = 1 WHERE idProfesor = ?";
	private static final String getbyemailbypass = "SELECT * FROM " + table_name + " WHERE email = ? AND contrasenia = MD5( ? )";
	private static final String getprofesoridbyid = "SELECT idProfesor FROM " + table_name + " WHERE id = ?";

	@Override
	public int Insert(Usuario inserted) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int result = -1;
		try
		{
			statement = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, inserted.getEmail());
			statement.setString(2, inserted.getContrasenia());
			statement.setInt(3, inserted.getIdTipoUsuario());
			statement.setInt(4, inserted.getIdProfesor());
			statement.setString(5, inserted.getNombre());
			statement.setString(6, inserted.getApellido());
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
	public boolean Update(Usuario updated) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {		
		// TODO Auto-generated method stub
		return false;
	}

	public boolean DeleteByProfesorId(int idProf) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean result = false;
		try 
		{
			statement = conexion.prepareStatement(deletebyprof);
			statement.setInt(1, idProf);
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
	public List<Usuario> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> GetAllEnabled() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario GetByEmailByPass(String email, String password) {
		PreparedStatement statement;
		ResultSet resultSet;
		Usuario usuario = null;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getbyemailbypass);
			statement.setString(1, email);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuario = Make(resultSet);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return usuario;
	}

	public int GetProfesorIdById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Usuario usuario = null;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getprofesoridbyid);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuario = Make(resultSet);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return usuario.getIdProfesor();
	}

	@Override
	public List<Usuario> GetAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario Make(ResultSet resultSet) throws SQLException {
		Usuario entidad = new Usuario();

		entidad.setId(resultSet.getInt("id"));
		entidad.setEmail(resultSet.getString("email"));
		entidad.setContrasenia(resultSet.getString("contrasenia"));
		entidad.setIdTipoUsuario(resultSet.getInt("idTipoUsuario"));
		entidad.setIdProfesor((Integer)resultSet.getObject("idProfesor"));
		entidad.setNombre(resultSet.getString("nombre"));
		entidad.setApellido(resultSet.getString("apellido"));
		entidad.setDeshabilitado(resultSet.getBoolean("deshabilitado"));
		
		return entidad;
	}
}
