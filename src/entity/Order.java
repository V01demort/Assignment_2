package entity;

public class Order {
    private int orderID;
    private Customer customer;
    private int productsCount;
    private boolean isPaid;

    public Order() {
        this(0, null, 0, false);
    }

    public Order(int orderID, Customer customer, int productsCount, boolean isPaid) {
        if (orderID <= 0) throw new IllegalArgumentException("Order ID must be positive.");
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null.");
        if (productsCount <= 0) throw new IllegalArgumentException("Products count must be positive.");
        this.orderID = orderID;
        this.customer = customer;
        this.productsCount = productsCount;
        this.isPaid = isPaid;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        if (orderID <= 0) throw new IllegalArgumentException("Order ID must be positive.");
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null.");
        this.customer = customer;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        if (productsCount <= 0) throw new IllegalArgumentException("Products count must be positive.");
        this.productsCount = productsCount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double calculateTotal(double productPrice) {
        if (productPrice < 0) throw new IllegalArgumentException("Product price cannot be negative.");
        double total = productPrice * productsCount;
        if (customer.isVip()) {
            total *= 0.9;
        }
        return total;
    }

    public void confirmPayment() {
        if (!isPaid) {
            isPaid = true;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customer=" + customer +
                ", productsCount=" + productsCount +
                ", isPaid=" + isPaid +
                '}';
    }
}