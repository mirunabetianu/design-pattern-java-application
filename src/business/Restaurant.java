package business;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.*;

public class Restaurant extends Observable implements IRestaurantProcessing{
    private Map<Order, HashSet<MenuItem>> map = new HashMap<>();
    private HashSet<MenuItem> menuItems = new HashSet<>();

    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * @pre name != null
     * @pre menuItems.size() > 0
     * @post this.menuItems.size() > 0
     */
    public void addNewCompositeProduct(String name, ArrayList<MenuItem> menuItems)
    {
        assert(name != null);
        assert(menuItems.size() >0);

        for(MenuItem m:menuItems)
        {
            if(!contains(m.getName()))
                addNewBaseProduct(m.getName(),m.getPrice());
        }
        CompositeProduct compositeProduct = new CompositeProduct(name,menuItems);
        this.menuItems.add(compositeProduct);
    }

    /**
     * @pre name != null
     * @pre price > 0
     * @post this.menuItems.size() > 0
     */
    public void addNewBaseProduct(String name, int price)
    {
        assert(name != null && price>0);
        BaseProduct baseProduct = new BaseProduct(name,price);
        this.menuItems.add(baseProduct);
    }

    /**
     * @pre menuItems.size() > 0
     * @post contains(menuItem.getName()) == false
     */
    public void removeProduct(MenuItem menuItem)
    {
        assert(menuItem.getName() != null);
        this.menuItems.remove(menuItem);
    }

    /**
     * @pre name != null
     * @pre menuItems.size() > 0
     * @post this.menuItems.size() > 0
     */
    public void editProduct(MenuItem menuItem,Object object, ArrayList<MenuItem> list)
    {
        assert(menuItem != null);
        if(object.getClass() == String.class)
        {
           if( contains(menuItem.getName()))
           {
               menuItems.remove(menuItem);
               if(menuItem.getClass() == BaseProduct.class)
               {
                   this.addNewBaseProduct(object.toString(),menuItem.computePrice());
               }
               if(menuItem.getClass() == CompositeProduct.class)
               {
                   this.addNewCompositeProduct(object.toString(),menuItem.getMenuItems());
               }
           }
            else System.out.println("Menu item not found");
        }
        if(object.getClass() == Integer.class)
        {
            if(contains(menuItem.getName()))
            {
                menuItems.remove(menuItem);
                if(menuItem.getClass() == BaseProduct.class)
                {
                    this.addNewBaseProduct(menuItem.getName(),Integer.parseInt(object.toString()));
                }
                else
                {
                    this.addNewCompositeProduct(menuItem.getName(),list);
                }
            }
            else System.out.println("Menu item not found");
        }
    }

    /**
     * @pre order != null
     * @pre menuItems.size() > 0
     * @post map.size() > 0
     */
    public void addOrder(Order order, HashSet<MenuItem> menuItems)
    {
        assert(order != null);
        map.put(order,menuItems);
        orders.add(order);
        notifyObservers(orders);
    }

    /**
     * @pre order != null
     * @post price > 0
     */
    public int computePriceOrder(Order order)
    {
        assert(order != null);
        int price = 0;
        if(map.containsKey(order))
        {
            for(MenuItem m:map.get(order))
            {
                price += m.computePrice();
            }
        }
        return price;
    }

    public HashSet<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * @pre item != null
     * @post m.getName().equals(item) == true
     */
    public MenuItem getItem(String item)
    {
        assert(item != null);
        for(MenuItem m:menuItems)
            if(m.getName().equals(item)) return m;
        return null;
    }

    /**
     * @pre menuItems.size() > 0
     * @post this.menuItems.size() > 0
     */
    public void setMenuItems(HashSet<MenuItem> menuItems)
    {
        assert(menuItems != null);
        this.menuItems = menuItems;
    }

    /**
     * @pre name != null
     * @post m.getName().equals(name) == true
     */
    private boolean contains(String name)
    {
        assert(name != null);
        for(MenuItem m: this.menuItems)
        {
            if(m.getName().equals(name))
                return true;
        }
        return false;
    }

    public Map<Order, HashSet<MenuItem>> getMap() {
        return map;
    }

    /**
     * @pre id > 0
     * @post (o.getOrderID() == id) == true
     */
    public Order getOrder(int id)
    {
        assert (id > 0);
        for(Order o:orders)
            if(o.getOrderID() == id)
                return o;
         return null;
    }
}
