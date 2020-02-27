package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dominio.ReportViewModel;

public class ReportDAO {

	private static final String getreport = "CALL SP_GetReport(?,?,?)";

	public List<ReportViewModel> GetReport(Integer materiaId, Integer desde, Integer hasta) {
				
		CallableStatement statement;
		ResultSet resultSet;
		ArrayList<ReportViewModel> list = new ArrayList<ReportViewModel>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareCall(getreport);
			
			if (materiaId != null)
				statement.setInt(1, materiaId);
			else
				statement.setNull(1, Types.INTEGER);
			if (desde != null)
				statement.setInt(2, desde);
			else
				statement.setNull(2, Types.INTEGER);
			if (hasta != null)
				statement.setInt(3, hasta);
			else
				statement.setNull(3, Types.INTEGER);
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				list.add(Make(resultSet));
			}
			
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private ReportViewModel Make(ResultSet resultSet) throws SQLException {
		ReportViewModel result = new ReportViewModel();

		result.setCurso(resultSet.getString("curso"));
		result.setAlumnosRegular(resultSet.getInt("alumnosRegular"));
		result.setAlumnosLibres(resultSet.getInt("alumnosLibres"));
		result.setAlumnosTotales(resultSet.getInt("alumnosTotales"));
		result.setPorcAlumRegular(resultSet.getFloat("porcAlumRegular"));
		result.setPorcAlumLibres(resultSet.getFloat("porcAlumLibres"));
		
		return result;
	}
	
}
