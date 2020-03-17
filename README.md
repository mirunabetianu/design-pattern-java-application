## Project specifications

1. Define the interface Restaurant Processing containing the main operations that can be executed by the waiter or the administrator, as follows:
* Administrator: create new menu item, delete menu item, edit menu item ✓
* Waiter: create new order; compute price for an order; generate bill in .txt
format. ✓


2. Define and implement the classes from the class diagram shown above:
* Use the Composite Design Pattern for defining the classes MenuItem, BaseProduct and CompositeProduct. ✓
* Use the Observer Design Pattern to notify the chef each time a new order containing a composite product is added. ✓


3. Implement the class Restaurant using a predefined JCF collection which uses a hashtable data structure. The hashtable key will be generated based on the class Order, which can have associated several MenuItems. Use JTable to display Restaurant related information. ✓
* Define a structure of type Map<Order, Collection<MenuItem>> for storing the order related information in the Restaurant class. The key of the Map will be formed of objects of type Order, for which the hashCode() method will be overwritten to compute the hash value within the Map from the attributes of the Order (OrderID, date, etc.) ✓
* Define a structure of type Collection<MenuItem> which will save the menu of the restaurant. Choose the appropriate collection type for your implementation. ✓
* Define a method of type “well formed” for the class Restaurant. ✓
* Implement the class using Design by Contract method (involving pre, post
conditions, invariants, and assertions). ✓


4. The menu items for populating the Restaurant object will be loaded/saved from/to a file using Serialization. ✓
