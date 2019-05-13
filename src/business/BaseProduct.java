package business;

public class BaseProduct extends MenuItem {
    private String name;
    private int price;

    public BaseProduct(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int computePrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
