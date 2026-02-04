package database;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void insertProduct(Product product) {
        String sql = "INSERT INTO product (name, brand, price) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, brand = ?, price = ? WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductID());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = extractProductFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> searchByMinPrice(double min) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price >= ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, min);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        double price = resultSet.getDouble("price");
        return new Product(id, name, brand, price);
    }
}