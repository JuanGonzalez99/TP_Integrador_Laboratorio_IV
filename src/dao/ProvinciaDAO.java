package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Provincia;

public class ProvinciaDAO implements IDao<Provincia> {
	
	private static final String table_name = "provincia";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";

	@Override
	public int Insert(Provincia inserted) {
		return 0;
	}

	@Override
	public boolean Update(Provincia updated) {
		return false;
	}

	@Override
	public boolean Delete(int id) {
		return false;
	}

	@Override
	public List<Provincia> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> list = new ArrayList<Provincia>();
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
	public Provincia GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Provincia entidad = new Provincia();
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
	public Provincia Make(ResultSet resultSet) throws SQLException {
		Provincia entidad = new Provincia();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setNombre(resultSet.getString("nombre"));
		entidad.setCodigo31662(resultSet.getString("codigo31662"));
		
		return entidad;
	}

	@Override
	public List<Provincia> GetAllEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> GetAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
