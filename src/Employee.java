public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee() {
        this(0, "", 0.0);
    }

    public Employee(int id, String name, double salary) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}