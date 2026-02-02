package database;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ---------- CREATE ----------
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO public.product (name, brand, price) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // ---------- READ (ALL) ----------
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM public.product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(extractProduct(resultSet));
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

    // ---------- READ (BY ID) ----------
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM public.product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            Product p = null;
            if (rs.next()) p = extractProduct(rs);

            rs.close();
            statement.close();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    // ---------- UPDATE ----------
    public boolean updateProduct(Product product) {
        String sql = "UPDATE public.product SET name = ?, brand = ?, price = ? WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductID()); // важно: id в WHERE

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

    // ---------- DELETE ----------
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM public.product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

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

    // ---------- SEARCH: NAME (ILIKE) ----------
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
                products.add(extractProduct(rs));
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

    // ---------- SEARCH: PRICE BETWEEN ----------
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
                products.add(extractProduct(rs));
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

    // ---------- SEARCH: MIN PRICE ----------
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
                products.add(extractProduct(rs));
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

    // ---------- helper ----------
    private Product extractProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        double price = resultSet.getDouble("price");

        // под твой конструктор Product(id, name, brand, price)
        return new Product(id, name, brand, price);
    }
}
