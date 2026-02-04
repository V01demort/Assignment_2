package service;

import entity.*;
import exception.InvalidFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private ArrayList<Employee> employees;
    private Scanner scanner;

    public MenuManager() {
        products = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);

        customers.add(new Customer(1, "Dimachka", 120, false));
        customers.add(new Customer(2, "Madiyarchik", 500, true));
        customers.add(new Customer(3, "Kotik", 0, false));
        employees.add(new Employee(1, "Dabrynya Nikitich", 40000) {
            @Override
            public void work() {
                System.out.println(getName() + " is performing general employee duties.");
            }

            @Override
            public String getRole() {
                return "Employee";
            }
        });
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
        System.out.println("4. Add Employee (General)");
        System.out.println("5. Add Sales Assistant");
        System.out.println("6. Add Manager");
        System.out.println("7. View All Products");
        System.out.println("8. View All Customers");
        System.out.println("9. View All Orders");
        System.out.println("10. View All Employees (Polymorphic)");
        System.out.println("11. Demonstrate Polymorphism (Work)");
        System.out.println("12. View Sales Assistants Only");
        System.out.println("13. View Managers Only");
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
                    case 1: addProduct(); break;
                    case 2: addCustomer(); break;
                    case 3: addOrder(); break;
                    case 4: addEmployee(); break;
                    case 5: addSalesAssistant(); break;
                    case 6: addManager(); break;
                    case 7: viewAllProducts(); break;
                    case 8: viewAllCustomers(); break;
                    case 9: viewAllOrders(); break;
                    case 10: viewAllEmployees(); break;
                    case 11: demonstratePolymorphism(); break;
                    case 12: viewSalesAssistants(); break;
                    case 13: viewManagers(); break;
                    case 0: running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. " + e.getMessage());
            } catch (IllegalArgumentException | InvalidFormatException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void addProduct() throws InvalidFormatException {
        try {
            System.out.print("Product ID: ");
            int pid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Name: ");
            String pname = scanner.nextLine().trim();
            System.out.print("Brand: ");
            String pbrand = scanner.nextLine().trim();
            System.out.print("Price: ");
            double pprice = Double.parseDouble(scanner.nextLine().trim());
            if (pname.length() > 50) {
                throw new InvalidFormatException("Product name too long.");
            }
            Product p = new Product(pid, pname, pbrand, pprice);
            products.add(p);
            System.out.println("Product added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addCustomer() {
        try {
            System.out.print("Customer ID: ");
            int cid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Name: ");
            String cname = scanner.nextLine().trim();
            System.out.print("Bonuses: ");
            int cbonuses = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("VIP (true/false): ");
            boolean cvip = Boolean.parseBoolean(scanner.nextLine().trim());
            Customer c = new Customer(cid, cname, cbonuses, cvip);
            customers.add(c);
            System.out.println("Customer added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addOrder() {
        try {
            System.out.print("Order ID: ");
            int oid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Customer ID: ");
            int ocid = Integer.parseInt(scanner.nextLine().trim());
            Customer ocustomer = null;
            for (Customer cust : customers) {
                if (cust.getCustomerID() == ocid) {
                    ocustomer = cust;
                    break;
                }
            }
            if (ocustomer == null) {
                System.out.println("Customer not found.");
                return;
            }
            System.out.print("Products Count: ");
            int ocount = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Is Paid (true/false): ");
            boolean opaid = Boolean.parseBoolean(scanner.nextLine().trim());
            Order o = new Order(oid, ocustomer, ocount, opaid);
            orders.add(o);
            System.out.println("Order added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addEmployee() {
        try {
            System.out.print("Employee ID: ");
            int eid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Name: ");
            String ename = scanner.nextLine().trim();
            System.out.print("Salary: ");
            double esalary = Double.parseDouble(scanner.nextLine().trim());

            Employee e = new Employee(eid, ename, esalary) {
                @Override
                public void work() {
                    System.out.println(getName() + " is performing general employee duties.");
                }

                @Override
                public String getRole() {
                    return "Employee";
                }
            };
            employees.add(e);
            System.out.println("Employee added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addSalesAssistant() {
        try {
            System.out.print("Sales Assistant ID: ");
            int said = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Name: ");
            String saname = scanner.nextLine().trim();
            System.out.print("Salary: ");
            double sasalary = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Sales Count: ");
            int sacount = Integer.parseInt(scanner.nextLine().trim());
            Employee sa = new SalesAssistant(said, saname, sasalary, sacount);
            employees.add(sa);
            System.out.println("Sales Assistant added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addManager() {
        try {
            System.out.print("Manager ID: ");
            int mid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Name: ");
            String mname = scanner.nextLine().trim();
            System.out.print("Salary: ");
            double msalary = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Team Size: ");
            int msize = Integer.parseInt(scanner.nextLine().trim());
            Employee m = new Manager(mid, mname, msalary, msize);
            employees.add(m);
            System.out.println("Manager added.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. " + e.getMessage());
        } catch (IllegalArgumentException e) {
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