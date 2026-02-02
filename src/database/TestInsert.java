package database;

import entity.Product;

public class TestInsert {
    public static void main(String[] args) {
        Product p = new Product(1, "T-Shirt", "Nike", 9990.00);

        ProductDAO dao = new ProductDAO();
        dao.insertProduct(p);
    }
}
