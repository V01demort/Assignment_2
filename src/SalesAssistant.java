public class SalesAssistant extends Employee {
    private int salesCount;

    public SalesAssistant() {
        super();
        this.salesCount = 0;
    }

    public SalesAssistant(int id, String name, double salary, int salesCount) {
        super(id, name, salary);
        if (salesCount < 0) throw new IllegalArgumentException("Sales count cannot be negative.");
        this.salesCount = salesCount;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        if (salesCount < 0) throw new IllegalArgumentException("Sales count cannot be negative.");
        this.salesCount = salesCount;
    }

    @Override
    public void work() {
        System.out.println(getName() + " is assisting customers with clothing selections.");
    }

    @Override
    public double calculateBonus() {
        return super.calculateBonus() + salesCount * 10;
    }

    public void makeSale() {
        salesCount++;
        System.out.println(getName() + " made a sale. Total sales: " + salesCount);
    }

    @Override
    public String toString() {
        return "SalesAssistant{" + super.toString() + ", salesCount=" + salesCount + "}";
    }
}