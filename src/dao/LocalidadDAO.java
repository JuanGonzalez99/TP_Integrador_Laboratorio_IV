package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Localidad;

public class LocalidadDAO implements IDao<Localidad> {

	private static final String table_name = "localidad";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";
	private static final String getbynombre = "SELECT * FROM " + table_name + " WHERE nombre LIKE ?";
	
	@Override
	public int Insert(Localidad inserted) {
		return 0;
	}

	@Override
	public boolean Update(Localidad updated) {
		return false;
	}

	@Override
	public boolean Delete(int id) {
		return false;
	}

	@Override
	public List<Localidad> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> list = new ArrayList<Localidad>();
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
	public List<Localidad> GetAllByName(String name) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> list = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getbynombre);
			statement.setString(1, "%" + name + "%");
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
	public Localidad GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Localidad entidad = new Localidad();
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
	public Localidad Make(ResultSet resultSet) throws SQLException {
		Localidad entidad = new Localidad();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setIdProvincia(resultSet.getShort("provincia_id"));
		entidad.setNombre(resultSet.getString("nombre"));
		entidad.setCodigoPostal(resultSet.getString("codigopostal"));
		
		return entidad;
	}

	@Override
	public List<Localidad> GetAllEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

}
