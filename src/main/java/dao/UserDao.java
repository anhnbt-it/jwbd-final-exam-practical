package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IDao<User> {
    private final Connection conn;

    public UserDao() {
        conn = DBConnection.getConnection();
    }

    @Override
    public boolean add(User obj) throws SQLException {
        int result = 0;
        try {
            String query = "INSERT INTO `users` (name) VALUES (?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, obj.getName());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean edit(User obj) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(int id) throws SQLException {
        return false;
    }

    @Override
    public User findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public List<User> getRecords() {
        return null;
    }
}
