package entity;

public class Product {
    private int productID;
    private String name;
    private String brand;
    private double price;

    public Product() {
        this(0, "", "", 0.0);
    }

    public Product(int productID, String name, String brand, double price) {
        if (productID <= 0) throw new IllegalArgumentException("Product ID must be positive.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (brand == null || brand.isEmpty()) throw new IllegalArgumentException("Brand cannot be empty.");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.productID = productID;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isEmpty()) throw new IllegalArgumentException("Brand cannot be empty.");
        this.brand = brand;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        if (productID <= 0) throw new IllegalArgumentException("Product ID must be positive.");
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Discount percent invalid.");
        price -= price * percent / 100;
    }

    public void increasePrice(double percent) {
        if (percent < 0) throw new IllegalArgumentException("Percent cannot be negative.");
        price += price * percent / 100;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}