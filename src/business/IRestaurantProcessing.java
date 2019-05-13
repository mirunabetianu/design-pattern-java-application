package business;

import java.util.ArrayList;
import java.util.HashSet;

public interface IRestaurantProcessing {

    void addNewCompositeProduct(String name, ArrayList<MenuItem> menuItems);

    void addNewBaseProduct(String name, int price);

    void removeProduct(MenuItem menuItem);

    void editProduct(MenuItem menuItem,Object object,ArrayList<MenuItem> list);

    void addOrder(Order order, HashSet<MenuItem> menuItems);

    int computePriceOrder(Order order);

    HashSet<MenuItem> getMenuItems();

    void setMenuItems(HashSet<MenuItem> readObject);
}

