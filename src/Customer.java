public class Customer {
    private int customerID;
    private String name;
    private int bonuses;
    private boolean vip;

    public Customer() {
        this(0, "", 0, false);
    }

    public Customer(int customerID, String name, int bonuses, boolean vip) {
        if (customerID <= 0) throw new IllegalArgumentException("Customer ID must be positive.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (bonuses < 0) throw new IllegalArgumentException("Bonuses cannot be negative.");
        this.customerID = customerID;
        this.name = name;
        this.bonuses = bonuses;
        this.vip = vip;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        if (customerID <= 0) throw new IllegalArgumentException("Customer ID must be positive.");
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        if (bonuses < 0) throw new IllegalArgumentException("Bonuses cannot be negative.");
        this.bonuses = bonuses;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void addBonuses(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative.");
        if (vip) {
            bonuses += amount * 2;
        } else {
            bonuses += amount;
        }
    }

    public boolean hasEnoughBonuses(int amount) {
        return bonuses >= amount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", bonuses=" + bonuses +
                ", vip=" + vip +
                '}';
    }
}