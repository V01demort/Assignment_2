package service;

import database.ProductDAO;
import entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {

    private final ProductDAO dao = new ProductDAO();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        while (true) {
            printMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                case 1 -> addProduct();            // INSERT (Week 7)
                case 2 -> viewAllProducts();       // SELECT (Week 7)
                case 3 -> updateProduct();         // UPDATE (Week 8)
                case 4 -> deleteProduct();         // DELETE (Week 8)
                case 5 -> searchByName();          // SEARCH ILIKE (Week 8)
                case 6 -> searchByPriceRange();    // SEARCH BETWEEN (Week 8)
                case 7 -> searchByMinPrice();      // SEARCH >= (Week 8)
                default -> System.out.println("Wrong option.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- PRODUCT MENU ---");
        System.out.println("1) Add product (INSERT)");
        System.out.println("2) View all products (SELECT)");
        System.out.println("3) Update product (UPDATE)");
        System.out.println("4) Delete product (DELETE)");
        System.out.println("5) Search by name (ILIKE)");
        System.out.println("6) Search by price range (BETWEEN)");
        System.out.println("7) Search by min price (>=)");
        System.out.println("0) Exit");
    }

    // ---------- INSERT ----------
    private void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");
        String name = readLine("Name: ");
        String brand = readLine("Brand: ");
        double price = readDouble("Price: ");

        Product p = new Product(0, name, brand, price);
        boolean ok = dao.insertProduct(p);

        System.out.println(ok ? "Inserted ✅" : "Insert failed ❌");
    }

    // ---------- SELECT ----------
    private void viewAllProducts() {
        System.out.println("\n--- ALL PRODUCTS ---");
        List<Product> products = dao.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products in DB.");
            return;
        }

        for (Product p : products) {
            printProduct(p);
        }
    }

    // ---------- UPDATE ----------
    // Минимальный вариант: пользователь вводит ВСЕ новые значения (без подгрузки старых)
    private void updateProduct() {
        System.out.println("\n--- UPDATE PRODUCT ---");
        int id = readInt("Enter product_id to update: ");

        String name = readLine("New name: ");
        String brand = readLine("New brand: ");
        double price = readDouble("New price: ");

        Product updated = new Product(id, name, brand, price);
        boolean ok = dao.updateProduct(updated);

        System.out.println(ok ? "Updated ✅" : "Update failed (wrong id?) ❌");
    }

    // ---------- DELETE ----------
    private void deleteProduct() {
        System.out.println("\n--- DELETE PRODUCT ---");
        int id = readInt("Enter product_id to delete: ");

        String confirm = readLine("Type 'yes' to confirm delete: ").trim().toLowerCase();
        if (!confirm.equals("yes")) {
            System.out.println("Cancelled.");
            return;
        }

        boolean ok = dao.deleteProduct(id);
        System.out.println(ok ? "Deleted ✅" : "Delete failed (wrong id?) ❌");
    }

    // ---------- SEARCH: NAME ----------
    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME ---");
        String q = readLine("Enter name part: ");

        List<Product> products = dao.searchByName(q);
        printList(products);
    }

    // ---------- SEARCH: PRICE RANGE ----------
    private void searchByPriceRange() {
        System.out.println("\n--- SEARCH BY PRICE RANGE ---");
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");

        List<Product> products = dao.searchByPriceRange(min, max);
        printList(products);
    }

    // ---------- SEARCH: MIN PRICE ----------
    private void searchByMinPrice() {
        System.out.println("\n--- SEARCH BY MIN PRICE ---");
        double min = readDouble("Min price: ");

        List<Product> products = dao.searchByMinPrice(min);
        printList(products);
    }

    // ---------- helpers ----------
    private void printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No matches.");
            return;
        }
        for (Product p : products) {
            printProduct(p);
        }
    }

    private void printProduct(Product p) {
        System.out.println("ID: " + p.getProductID());
        System.out.println("Name: " + p.getName());
        System.out.println("Brand: " + p.getBrand());
        System.out.println("Price: " + p.getPrice());
        System.out.println("---");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
