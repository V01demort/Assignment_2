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

            if (choice == 0) {
                System.out.println("Bye!");
                return;
            }

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewAllProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> searchByName();
                case 6 -> searchByPriceRange();
                case 7 -> searchByMinPrice();
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

    // --------- 1) INSERT ----------
    private void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");
        String name = readLine("Name: ");
        String brand = readLine("Brand: ");
        double price = readDouble("Price: ");

        Product p = new Product(0, name, brand, price);
        boolean ok = dao.insertProduct(p);

        System.out.println(ok ? "Inserted ✅" : "Insert failed ❌");
    }

    // --------- 2) SELECT ----------
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

    // --------- 3) UPDATE ----------
    private void updateProduct() {
        System.out.println("\n--- UPDATE PRODUCT ---");
        int id = readInt("Enter product_id: ");

        Product old = dao.getProductById(id);
        if (old == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Current values:");
        printProduct(old);

        System.out.println("Enter new values (press Enter to keep old).");

        String newName = readLineAllowEmpty("New name: ");
        String newBrand = readLineAllowEmpty("New brand: ");
        String newPriceStr = readLineAllowEmpty("New price: ");

        String name = newName.isEmpty() ? old.getName() : newName;
        String brand = newBrand.isEmpty() ? old.getBrand() : newBrand;
        double price = old.getPrice();
        if (!newPriceStr.isEmpty()) price = parseDoubleSafe(newPriceStr, old.getPrice());

        Product updated = new Product(id, name, brand, price);
        boolean ok = dao.updateProduct(updated);

        System.out.println(ok ? "Updated ✅" : "Update failed ❌");
    }

    // --------- 4) DELETE ----------
    private void deleteProduct() {
        System.out.println("\n--- DELETE PRODUCT ---");
        int id = readInt("Enter product_id: ");

        Product p = dao.getProductById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("You are going to delete:");
        printProduct(p);

        String confirm = readLine("Type 'yes' to confirm: ").trim().toLowerCase();
        if (!confirm.equals("yes")) {
            System.out.println("Cancelled.");
            return;
        }

        boolean ok = dao.deleteProduct(id);
        System.out.println(ok ? "Deleted ✅" : "Delete failed ❌");
    }

    // --------- 5) SEARCH NAME ----------
    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME ---");
        String q = readLine("Enter name part: ");
        List<Product> products = dao.searchByName(q);

        if (products.isEmpty()) {
            System.out.println("No matches.");
            return;
        }

        for (Product p : products) {
            printProduct(p);
        }
    }

    // --------- 6) SEARCH RANGE ----------
    private void searchByPriceRange() {
        System.out.println("\n--- SEARCH BY PRICE RANGE ---");
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");

        List<Product> products = dao.searchByPriceRange(min, max);
        if (products.isEmpty()) {
            System.out.println("No matches.");
            return;
        }

        for (Product p : products) {
            printProduct(p);
        }
    }

    // --------- 7) SEARCH MIN ----------
    private void searchByMinPrice() {
        System.out.println("\n--- SEARCH BY MIN PRICE ---");
        double min = readDouble("Min price: ");

        List<Product> products = dao.searchByMinPrice(min);
        if (products.isEmpty()) {
            System.out.println("No matches.");
            return;
        }

        for (Product p : products) {
            printProduct(p);
        }
    }

    // --------- helpers ----------
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

    private double parseDoubleSafe(String s, double fallback) {
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            System.out.println("Bad number, keeping old value.");
            return fallback;
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private String readLineAllowEmpty(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
