package entity;

public abstract class Employee {
    protected int id;
    protected String name;
    protected double salary;

    public Employee() {
        this(0, "", 0.0);
    }

    public Employee(int id, String name, double salary) {
        setId(id);
        setName(name);
        setSalary(salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (!name.matches("[a-zA-Z]+")) throw new exception.InvalidInputException("Name must contain only letters.");
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.salary = salary;
    }

    public void work() {
        System.out.println(name + " is performing general employee duties.");
    }

    public double calculateBonus() {
        return salary * 0.1;
    }

    // Abstract method - children must implement
    public abstract String getRole();

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}