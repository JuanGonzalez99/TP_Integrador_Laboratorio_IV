package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
	
	int Insert(T inserted);
	boolean Update(T updated);
	boolean Delete(int id);
	List<T> GetAll();
	List<T> GetAllEnabled();
	List<T> GetAllByName(String name);
	T GetById(int id);
	T Make(ResultSet resultSet) throws SQLException;
}
