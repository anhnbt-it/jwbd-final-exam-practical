package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    boolean add(T obj) throws SQLException;
    boolean edit(T obj) throws SQLException;
    boolean remove(int id) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findByName(String name);
    List<T> getRecords();
}
