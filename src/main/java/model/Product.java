package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int qty;
    private String color;
    private String desc;
    private int categoryId;
    private String categoryName;

    public Product() {
    }

    public Product(String name, double price, int qty, String color, String desc, int categoryId) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.color = color;
        this.desc = desc;
        this.categoryId = categoryId;
    }

    public Product(int id, String name, double price, int qty, String color, String desc, int categoryId) {
        this(name, price, qty, color, desc, categoryId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", color='" + color + '\'' +
                ", desc='" + desc + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
