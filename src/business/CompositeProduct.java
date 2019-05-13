package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private String name;
    private int price;
    private ArrayList<MenuItem> menuItems;

    public CompositeProduct(String name, ArrayList<MenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
        this.price = this.computePrice();
    }

    public int computePrice() {
        int price = 0;
        for(MenuItem m: menuItems)
        {
            price += m.computePrice();
        }
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

}
