package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Materia;

public class MateriaDAO implements IDao<Materia> {

	private static final String table_name = "materias";
	private static final String getall = "SELECT * FROM " + table_name;
	private static final String getbynombre = "SELECT * FROM " + table_name + " WHERE nombre LIKE ?";
	private static final String getbyid = "SELECT * FROM " + table_name + " WHERE id = ?";

	@Override
	public int Insert(Materia inserted) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean Update(Materia updated) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Materia> GetAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Materia> list = new ArrayList<Materia>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getall);
			resultSet = statement.executeQuery();
			while (resultSet.next())
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
	public List<Materia> GetAllEnabled() {
		return GetAll();
	}

	@Override
	public List<Materia> GetAllByName(String name) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Materia> list = new ArrayList<Materia>();
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
	public Materia GetById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Materia entidad = new Materia();
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
	public Materia Make(ResultSet resultSet) throws SQLException {
		Materia entidad = new Materia();
		
		entidad.setId(resultSet.getInt("id"));
		entidad.setNombre(resultSet.getString("nombre"));
		entidad.setIdCarrera(resultSet.getInt("idCarrera"));
		
		return entidad;
	}

}
