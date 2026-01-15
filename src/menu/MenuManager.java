package menu;

import entity.*;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public MenuManager() {
        // Test data
        products.add(new Product(1, "Futbolka", "Abibas", 1999.99));
        products.add(new Product(2, "Noski", "Puma", 299.50));
        products.add(new Product(3, "Trusy", "CalvinClain", 8999.00));
        customers.add(new Customer(1, "Dimachka", 120, false));
        customers.add(new Customer(2, "Madiyarchik", 500, true));
        customers.add(new Customer(3, "Kotik", 0, false));
        employees.add(new SalesAssistant(1, "DabrynyaNikitich", 40000, 0));  // Changed to SalesAssistant since Employee is abstract
        employees.add(new SalesAssistant(2, "Galya", 35000, 15));
        employees.add(new Manager(5, "Tokaev", 90000, 12));
        orders.add(new Order(1, customers.get(0), 2, true));
        orders.add(new Order(2, customers.get(1), 5, false));
        orders.add(new Order(3, customers.get(2), 1, true));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Clothing Store Management System ===");
        System.out.println("1. Add Product");
        System.out.println("2. Add Customer");
        System.out.println("3. Add Order");
        System.out.println("4. Add Sales Assistant");
        System.out.println("5. Add Manager");
        System.out.println("6. View All Products");
        System.out.println("7. View All Customers");
        System.out.println("8. View All Orders");
        System.out.println("9. View All Employees (Polymorphic)");
        System.out.println("10. Demonstrate Polymorphism (Work)");
        System.out.println("11. View Sales Assistants Only");
        System.out.println("12. View Managers Only");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: addCustomer(); break;
                    case 3: addOrder(); break;
                    case 4: addSalesAssistant(); break;
                    case 5: addManager(); break;
                    case 6: viewAllProducts(); break;
                    case 7: viewAllCustomers(); break;
                    case 8: viewAllOrders(); break;
                    case 9: viewAllEmployees(); break;
                    case 10: demonstratePolymorphism(); break;
                    case 11: viewSalesAssistants(); break;
                    case 12: viewManagers(); break;
                    case 0: running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Error: Invalid input format. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void addProduct() {
        try {
            System.out.print("Product ID: ");
            int pid = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String pname = scanner.nextLine();
            System.out.print("Brand: ");
            String pbrand = scanner.nextLine();
            System.out.print("Price: ");
            double pprice = scanner.nextDouble();
            Product p = new Product(pid, pname, pbrand, pprice);
            products.add(p);
            System.out.println("Product added.");
        } catch (IllegalArgumentException | InvalidInputException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addCustomer() {
        try {
            System.out.print("Customer ID: ");
            int cid = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String cname = scanner.nextLine();
            System.out.print("Bonuses: ");
            int cbonuses = scanner.nextInt();
            System.out.print("VIP (true/false): ");
            boolean cvip = scanner.nextBoolean();
            Customer c = new Customer(cid, cname, cbonuses, cvip);
            customers.add(c);
            System.out.println("Customer added.");
        } catch (IllegalArgumentException | InvalidInputException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addOrder() {
        try {
            System.out.print("Order ID: ");
            int oid = scanner.nextInt();
            System.out.print("Customer ID: ");
            int ocid = scanner.nextInt();
            Customer ocustomer = null;
            for (Customer cust : customers) {
                if (cust.getCustomerID() == ocid) {
                    ocustomer = cust;
                    break;
                }
            }
            if (ocustomer == null) {
                throw new IllegalArgumentException("Customer not found.");
            }
            System.out.print("Products Count: ");
            int ocount = scanner.nextInt();
            System.out.print("Is Paid (true/false): ");
            boolean opaid = scanner.nextBoolean();
            Order o = new Order(oid, ocustomer, ocount, opaid);
            orders.add(o);
            System.out.println("Order added.");
        } catch (IllegalArgumentException | InvalidInputException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addSalesAssistant() {
        try {
            System.out.print("Sales Assistant ID: ");
            int said = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String saname = scanner.nextLine();
            System.out.print("Salary: ");
            double sasalary = scanner.nextDouble();
            System.out.print("Sales Count: ");
            int sacount = scanner.nextInt();
            SalesAssistant sa = new SalesAssistant(said, saname, sasalary, sacount);
            employees.add(sa);
            System.out.println("Sales Assistant added.");
        } catch (IllegalArgumentException | InvalidInputException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addManager() {
        try {
            System.out.print("Manager ID: ");
            int mid = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String mname = scanner.nextLine();
            System.out.print("Salary: ");
            double msalary = scanner.nextDouble();
            System.out.print("Team Size: ");
            int msize = scanner.nextInt();
            Manager m = new Manager(mid, mname, msalary, msize);
            employees.add(m);
            System.out.println("Manager added.");
        } catch (IllegalArgumentException | InvalidInputException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        System.out.println("\nAll Products:");
        for (Product pr : products) {
            System.out.println(pr);
        }
    }

    private void viewAllCustomers() {
        System.out.println("\nAll Customers:");
        for (Customer cu : customers) {
            System.out.println(cu);
        }
    }

    private void viewAllOrders() {
        System.out.println("\nAll Orders:");
        for (Order or : orders) {
            System.out.println(or);
        }
    }

    private void viewAllEmployees() {
        System.out.println("\nAll Employees (Polymorphic):");
        for (Employee em : employees) {
            System.out.println(em);
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\nPolymorphism Demonstration (Work):");
        for (Employee em : employees) {
            em.work();
        }
        System.out.println("Notice: Same method, different behaviors!");
    }

    private void viewSalesAssistants() {
        System.out.println("\nSales Assistants Only:");
        for (Employee em : employees) {
            if (em instanceof SalesAssistant) {
                System.out.println(em);
                ((SalesAssistant) em).makeSale();
            }
        }
    }

    private void viewManagers() {
        System.out.println("\nManagers Only:");
        for (Employee em : employees) {
            if (em instanceof Manager) {
                System.out.println(em);
                ((Manager) em).assignTask();
            }
        }
    }
}