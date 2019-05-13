package start;

import business.*;
import data.RestaurantSerializator;
import presentation.AdministratorGraphicalUserInterface;
import presentation.ChefGraphicalUserInterface;
import presentation.Controller;
import presentation.WaiterGraphicalUserInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        final Restaurant restaurant = new Restaurant();
        final  RestaurantSerializator restaurantSerializator = new RestaurantSerializator("data.txt");
        restaurantSerializator.readData(restaurant);

        AdministratorGraphicalUserInterface administratorGraphicalUserInterface = new AdministratorGraphicalUserInterface(restaurant);
        WaiterGraphicalUserInterface waiterGraphicalUserInterface = new WaiterGraphicalUserInterface();
        ChefGraphicalUserInterface chefGraphicalUserInterface = new ChefGraphicalUserInterface();
        restaurant.addObserver(chefGraphicalUserInterface);

        Controller c = new Controller(administratorGraphicalUserInterface,chefGraphicalUserInterface,waiterGraphicalUserInterface,restaurant);
        administratorGraphicalUserInterface.mainFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                restaurantSerializator.writeData(restaurant);
                e.getWindow().dispose();
            }
        });
    }
}
