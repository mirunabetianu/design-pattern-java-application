package data;

import business.IRestaurantProcessing;
import business.MenuItem;
import business.Restaurant;

import java.io.*;
import java.util.HashSet;

public class RestaurantSerializator {
    private File file;

    public RestaurantSerializator(String fileName) {
        this.file = new File(fileName);
    }

    public void writeData(IRestaurantProcessing restaurant) {

        FileOutputStream fileOutputStream;
        try {

            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(restaurant.getMenuItems());
            outputStream.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void readData(Restaurant restaurant) {

        FileInputStream fileInputStream;
        try {

            fileInputStream = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            restaurant.setMenuItems( (HashSet<MenuItem>) inputStream.readObject() );

            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}

