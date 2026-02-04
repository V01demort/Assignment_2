package database;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public void insertProduct(Product product) {
        String sql = "INSERT INTO product (name, brand, price) VALUES (?, ?, ?)";

            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, brand = ?, price = ? WHERE product_id = ?";

        statement.setString(1, product.getName());
        statement.setString(2, product.getBrand());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getProductID());
        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";

            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
    }

    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";

            statement.setInt(1, id);

            if (resultSet.next()) {
                product = extractProductFromResultSet(resultSet);
            }
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";

            statement.setString(1, "%" + name + "%");
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
    }

    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price DESC";

            statement.setDouble(1, min);
            statement.setDouble(2, max);
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
    }

    public List<Product> searchByMinPrice(double min) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price >= ? ORDER BY price DESC";

            statement.setDouble(1, min);

            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        double price = resultSet.getDouble("price");
        return new Product(id, name, brand, price);
    }
}