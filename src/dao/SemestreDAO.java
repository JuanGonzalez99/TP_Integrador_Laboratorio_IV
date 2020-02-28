package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Semestre;

public class SemestreDAO implements IDao<Semestre> {
	
	private static final String table_name = "semestres";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";

	@Override
	public int Insert(Semestre inserted) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean Update(Semestre updated) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Semestre> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Semestre> list = new ArrayList<Semestre>();
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
	public List<Semestre> GetAllEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Semestre> GetAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Semestre GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Semestre entidad = new Semestre();
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
	public Semestre Make(ResultSet resultSet) throws SQLException {
		Semestre entidad = new Semestre();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setDescripcion(resultSet.getString("descripcion"));
		
		return entidad;
	}

}
