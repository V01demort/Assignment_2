import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        boolean running = true;

        products.add(new Product(1, "Futbolka", "Abibas", 1999.99));
        products.add(new Product(2, "Noski", "Puma", 299.50));
        products.add(new Product(3, "Trusy", "CalvinClain", 8999.00));

        customers.add(new Customer(1, "Dimachka", 120, false));
        customers.add(new Customer(2, "Madiyarchik", 500, true));
        customers.add(new Customer(3, "Kotik", 0, false));

        employees.add(new Employee(1, "Dabrynya Nikitich", 40000));
        employees.add(new SalesAssistant(2, "Galya", 35000, 15));
        employees.add(new Manager(5, "Tokaev", 90000, 12));

        orders.add(new Order(1, customers.get(0), 2, true));
        orders.add(new Order(2, customers.get(1), 5, false));
        orders.add(new Order(3, customers.get(2), 1, true));

        while (running) {
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
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    int pid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String pname = scanner.nextLine();
                    System.out.print("Brand: ");
                    String pbrand = scanner.nextLine();
                    System.out.print("Price: ");
                    double pprice = scanner.nextDouble();
                    try {
                        Product p = new Product(pid, pname, pbrand, pprice);
                        products.add(p);
                        System.out.println("Product added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Customer ID: ");
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Bonuses: ");
                    int cbonuses = scanner.nextInt();
                    System.out.print("VIP (true/false): ");
                    boolean cvip = scanner.nextBoolean();
                    try {
                        Customer c = new Customer(cid, cname, cbonuses, cvip);
                        customers.add(c);
                        System.out.println("Customer added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:

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
                        System.out.println("Customer not found.");
                        break;
                    }
                    System.out.print("Products Count: ");
                    int ocount = scanner.nextInt();
                    System.out.print("Is Paid (true/false): ");
                    boolean opaid = scanner.nextBoolean();
                    try {
                        Order o = new Order(oid, ocustomer, ocount, opaid);
                        orders.add(o);
                        System.out.println("Order added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Employee ID: ");
                    int eid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String ename = scanner.nextLine();
                    System.out.print("Salary: ");
                    double esalary = scanner.nextDouble();
                    try {
                        Employee e = new Employee(eid, ename, esalary);
                        employees.add(e);
                        System.out.println("Employee added.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Sales Assistant ID: ");
                    int said = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String saname = scanner.nextLine();
                    System.out.print("Salary: ");
                    double sasalary = scanner.nextDouble();
                    System.out.print("Sales Count: ");
                    int sacount = scanner.nextInt();
                    try {
                        Employee sa = new SalesAssistant(said, saname, sasalary, sacount);
                        employees.add(sa);
                        System.out.println("Sales Assistant added.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("Manager ID: ");
                    int mid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String mname = scanner.nextLine();
                    System.out.print("Salary: ");
                    double msalary = scanner.nextDouble();
                    System.out.print("Team Size: ");
                    int msize = scanner.nextInt();
                    try {
                        Employee m = new Manager(mid, mname, msalary, msize);
                        employees.add(m);
                        System.out.println("Manager added.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("\nAll Products:");
                    for (Product pr : products) {
                        System.out.println(pr);
                    }
                    break;
                case 8:
                    System.out.println("\nAll Customers:");
                    for (Customer cu : customers) {
                        System.out.println(cu);
                    }
                    break;
                case 9:
                    System.out.println("\nAll Orders:");
                    for (Order or : orders) {
                        System.out.println(or);
                    }
                    break;
                case 10:
                    System.out.println("\nAll Employees (Polymorphic):");
                    for (Employee em : employees) {
                        System.out.println(em);
                    }
                    break;
                case 11:
                    System.out.println("\nPolymorphism Demonstration (Work):");
                    for (Employee em : employees) {
                        em.work();
                    }
                    System.out.println("Notice: Same method, different behaviors!");
                    break;
                case 12:
                    System.out.println("\nSales Assistants Only:");
                    for (Employee em : employees) {
                        if (em instanceof SalesAssistant) {
                            System.out.println(em);
                            ((SalesAssistant) em).makeSale();
                        }
                    }
                    break;
                case 13:
                    System.out.println("\nManagers Only:");
                    for (Employee em : employees) {
                        if (em instanceof Manager) {
                            System.out.println(em);
                            ((Manager) em).assignTask();
                        }
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}