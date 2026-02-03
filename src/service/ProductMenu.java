package service;

import database.ProductDAO;
import entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductMenu implements Menu {
    private Scanner scanner;
    private ProductDAO productDAO;

    public ProductMenu() {
        scanner = new Scanner(System.in);
        productDAO = new ProductDAO();

        // Initial test data (insert to DB)
        productDAO.insertProduct(new Product(1, "Futbolka", "Abibas", 1999.99));
        productDAO.insertProduct(new Product(2, "Noski", "Puma", 299.50));
        productDAO.insertProduct(new Product(3, "Trusy", "CalvinClain", 8999.00));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Product Menu (DB) ===");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Search by Name");
        System.out.println("6. Search by Price Range");
        System.out.println("7. Search by Min Price");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewAllProducts();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        searchByName();
                        break;
                    case 6:
                        searchByPriceRange();
                        break;
                    case 7:
                        searchByMinPrice();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void addProduct() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Brand: ");
        String brand = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        Product product = new Product(0, name, brand, price);
        productDAO.insertProduct(product);
        System.out.println("Product added.");
    }

    private void viewAllProducts() {
        System.out.println("\nAll Products:");
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Product existing = productDAO.getProductById(id);
        if (existing == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Current Info: " + existing);

        System.out.print("New Name [" + existing.getName() + "]: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) newName = existing.getName();

        System.out.print("New Brand [" + existing.getBrand() + "]: ");
        String newBrand = scanner.nextLine().trim();
        if (newBrand.isEmpty()) newBrand = existing.getBrand();

        System.out.print("New Price [" + existing.getPrice() + "]: ");
        String priceInput = scanner.nextLine().trim();
        double newPrice = priceInput.isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

        existing.setName(newName);
        existing.setBrand(newBrand);
        existing.setPrice(newPrice);

        boolean success = productDAO.updateProduct(existing);
        if (success) {
            System.out.println("Product updated.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Product product = productDAO.getProductById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Product to delete: " + product);

        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if ("y".equalsIgnoreCase(confirm)) {
            boolean success = productDAO.deleteProduct(id);
            if (success) {
                System.out.println("Product deleted.");
            } else {
                System.out.println("Delete failed.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        List<Product> results = productDAO.searchByName(name);
        System.out.println("\nSearch Results:");
        for (Product p : results) {
            System.out.println(p);
        }
    }

    private void searchByPriceRange() {
        System.out.print("Enter min price: ");
        double min = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter max price: ");
        double max = Double.parseDouble(scanner.nextLine().trim());
        List<Product> results = productDAO.searchByPriceRange(min, max);
        System.out.println("\nSearch Results:");
        for (Product p : results) {
            System.out.println(p);
        }
    }

    private void searchByMinPrice() {
        System.out.print("Enter min price: ");
        double min = Double.parseDouble(scanner.nextLine().trim());
        List<Product> results = productDAO.searchByMinPrice(min);
        System.out.println("\nSearch Results:");
        for (Product p : results) {
            System.out.println(p);
        }
    }
}