package database;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // CREATE
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO public.product (name, brand, price) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());

            int rows = statement.executeUpdate();
            statement.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // READ
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM public.product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, brand, price));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    // UPDATE
    public boolean updateProduct(Product product) {
        String sql = "UPDATE public.product SET name = ?, brand = ?, price = ? WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductID()); // <-- твой id

            int rows = statement.executeUpdate();
            statement.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // DELETE
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM public.product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            int rows = statement.executeUpdate();
            statement.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // SEARCH name
    public List<Product> searchByName(String namePart) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM public.product WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + namePart + "%");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, brand, price));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    // SEARCH price range
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM public.product WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, brand, price));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    // SEARCH min price
    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM public.product WHERE price >= ? ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, brand, price));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }
}
