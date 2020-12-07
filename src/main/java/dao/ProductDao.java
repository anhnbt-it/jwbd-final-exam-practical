package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDao implements IDao<Product> {
    private final Connection conn;

    public ProductDao() {
        conn = DBConnection.getConnection();
    }

    @Override
    public boolean add(Product obj) throws SQLException {
        int result = 0;
        try {
            String query = "INSERT INTO `products` (`name`, `price`, `qty`, `color`, `desc`, `category_id`) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, obj.getName());
            pstmt.setDouble(2, obj.getPrice());
            pstmt.setInt(3, obj.getQty());
            pstmt.setString(4, obj.getColor());
            pstmt.setString(5, obj.getDesc());
            pstmt.setInt(6, obj.getCategoryId());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean edit(Product obj) throws SQLException {
        int result = 0;
        try {
            String query = "UPDATE `products` SET `name` = ?, `price` = ?, `qty` = ?, `color` = ?" +
                    ", `desc` = ?, `category_id` = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, obj.getName());
            pstmt.setDouble(2, obj.getPrice());
            pstmt.setInt(3, obj.getQty());
            pstmt.setString(4, obj.getColor());
            pstmt.setString(5, obj.getDesc());
            pstmt.setInt(6, obj.getCategoryId());
            pstmt.setInt(7, obj.getId());
            System.out.println(obj.toString());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean remove(int id) throws SQLException {
        int result = 0;
        try {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        try {
            String query = "SELECT * FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = extractProductResultset(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM `products` WHERE `name` LIKE ? ORDER BY `name` DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, '%' + name + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product customer = extractProductResultset(rs);
                products.add(customer);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getRecords() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT p.*, c.name as category_name FROM products p " +
                    "INNER JOIN categories c on p.category_id = c.id ORDER BY id DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = extractProductResultset(rs);
                product.setCategoryName(rs.getString("category_name"));
                products.add(product);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product extractProductResultset(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(Double.parseDouble(rs.getString("price")));
        product.setQty(Integer.parseInt(rs.getString("qty")));
        product.setColor(rs.getString("color"));
        product.setDesc(rs.getString("desc"));
        product.setCategoryId(rs.getInt("category_id"));
        return product;
    }
}
