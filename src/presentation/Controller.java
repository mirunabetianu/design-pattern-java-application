package presentation;

import business.BaseProduct;
import business.MenuItem;
import business.Order;
import business.Restaurant;
import data.FileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Controller {
    private AdministratorGraphicalUserInterface administratorGraphicalUserInterface;
    private ChefGraphicalUserInterface chefGraphicalUserInterface;
    private WaiterGraphicalUserInterface waiterGraphicalUserInterface;
    private Restaurant restaurant;

    public Controller(AdministratorGraphicalUserInterface administratorGraphicalUserInterface, ChefGraphicalUserInterface chefGraphicalUserInterface, WaiterGraphicalUserInterface waiterGraphicalUserInterface, Restaurant restaurant) {
        this.administratorGraphicalUserInterface = administratorGraphicalUserInterface;
        this.chefGraphicalUserInterface = chefGraphicalUserInterface;
        this.waiterGraphicalUserInterface = waiterGraphicalUserInterface;
        this.restaurant = restaurant;
        administratorGraphicalUserInterface.addMenuItemInsertListener(new addInsertItemListener());
        administratorGraphicalUserInterface.addMenuItemDeleteListener(new addDeleteItemListener());
        administratorGraphicalUserInterface.addMenuItemEditListener(new addEditItemListener());
        administratorGraphicalUserInterface.addMenuItemListListener(new addOkEditItemListener());
        administratorGraphicalUserInterface.addOkEditListener(new addOkEEditItemListener());
        waiterGraphicalUserInterface.addOrderInsertListener(new addAddOrderListener());
        waiterGraphicalUserInterface.addPlaceOrderListener(new addPlaceOrderListener());
        waiterGraphicalUserInterface.addOrderBillListener(new addComputeBillListener());
    }

    public class addInsertItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (administratorGraphicalUserInterface.getName() != null) {
                if (administratorGraphicalUserInterface.getPrice() == 0) {
                    ArrayList<MenuItem> menuItems = new ArrayList<>();
                    MenuItem menuItem;
                    for (MenuItem m : restaurant.getMenuItems()) {
                        if (m.getClass() == BaseProduct.class)
                            menuItems.add(m);
                        else
                        {
                            menuItem = new BaseProduct(m.getName(),m.getPrice());
                            menuItems.add(menuItem);
                        }
                    }
                    administratorGraphicalUserInterface.newFrame(menuItems);
                    System.out.println("Composite product detected");
                } else {
                    restaurant.addNewBaseProduct(administratorGraphicalUserInterface.getName(), administratorGraphicalUserInterface.getPrice());
                    administratorGraphicalUserInterface.fillInTable(restaurant);
                    System.out.println("Base product added");
                }
            } else
                JOptionPane.showMessageDialog(administratorGraphicalUserInterface.mainFrame, "Please introduce a name for the product!");
        }
    }

    public class addDeleteItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MenuItem menuItem;
                String name = administratorGraphicalUserInterface.getName();
                if (restaurant.getItem(name) != null) {
                    menuItem = restaurant.getItem(name);
                    restaurant.removeProduct(menuItem);
                    administratorGraphicalUserInterface.fillInTable(restaurant);
                } else
                    JOptionPane.showMessageDialog(administratorGraphicalUserInterface.mainFrame, "Please introduce a valid name");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(administratorGraphicalUserInterface.mainFrame, "Something is wrong");
            }
        }
    }

    public class addEditItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = administratorGraphicalUserInterface.getName();
                if (restaurant.getItem(name) != null) {
                    MenuItem menuItem;
                    menuItem = restaurant.getItem(name);
                    if (administratorGraphicalUserInterface.getPrice() != 0) {
                        restaurant.editProduct(menuItem, administratorGraphicalUserInterface.getPrice(), null);
                    } else {
                        if (administratorGraphicalUserInterface.getSecondaryName() == null) {
                            ArrayList<MenuItem> menuItems = new ArrayList<>();
                            for (MenuItem m : restaurant.getMenuItems()) {
                                if (m.getClass() == BaseProduct.class)
                                    menuItems.add(m);
                            }
                            administratorGraphicalUserInterface.newFrame(menuItems);
                        } else
                            restaurant.editProduct(menuItem, administratorGraphicalUserInterface.getSecondaryName(), null);
                    }
                    administratorGraphicalUserInterface.fillInTable(restaurant);
                } else
                    JOptionPane.showMessageDialog(administratorGraphicalUserInterface.mainFrame, "Please introduce a valid name");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(administratorGraphicalUserInterface.mainFrame, "Something is wrong");
            }
        }
    }
    public class addOkEditItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<MenuItem> menuItems = new ArrayList<>();
                for (JCheckBox checkBox : administratorGraphicalUserInterface.checkBoxes) {
                    if (checkBox.isSelected()) {
                        MenuItem menuItem = restaurant.getItem(checkBox.getText());
                        menuItems.add(menuItem);
                    }
                }
                restaurant.addNewCompositeProduct(administratorGraphicalUserInterface.getName(), menuItems);
                administratorGraphicalUserInterface.fillInTable(restaurant);
                System.out.println("Composite product added");
                administratorGraphicalUserInterface.secondFrame.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something is wrong");
            }
        }
    }

    public class addOkEEditItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<MenuItem> menuItems = new ArrayList<>();
                String name = administratorGraphicalUserInterface.getName();
                for (JCheckBox checkBox : administratorGraphicalUserInterface.checkBoxes) {
                    if (checkBox.isSelected()) {
                        MenuItem menuItem = restaurant.getItem(checkBox.getText());
                        menuItems.add(menuItem);
                    }
                }
                MenuItem menuItem = restaurant.getItem(name);
                restaurant.editProduct(menuItem, 0, menuItems);
                administratorGraphicalUserInterface.fillInTable(restaurant);
                System.out.println("Composite product added");
                administratorGraphicalUserInterface.secondFrame.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something is wrong");
            }
        }
    }

    public class addAddOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = waiterGraphicalUserInterface.getId();
            int table = waiterGraphicalUserInterface.getTable();
            Date date = waiterGraphicalUserInterface.getDate();
            if (id != 0 && table != 0 && date != null) {
                waiterGraphicalUserInterface.newFrame(restaurant.getMenuItems());
            } else
                JOptionPane.showMessageDialog(waiterGraphicalUserInterface.mainFrame, "Please complete all the fields carefully!");
        }
    }

    public class addPlaceOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(chefGraphicalUserInterface.mainFrame, "New order!");
            int id = waiterGraphicalUserInterface.getId();
            int table = waiterGraphicalUserInterface.getTable();
            Date date = waiterGraphicalUserInterface.getDate();
            Order order = new Order(id, date, table);
            HashSet<MenuItem> menuItems = new HashSet<>();
            for (JCheckBox checkBox : waiterGraphicalUserInterface.checkBoxes) {
                if (checkBox.isSelected())
                    menuItems.add(restaurant.getItem(checkBox.getText()));
            }
            restaurant.addOrder(order, menuItems);
            waiterGraphicalUserInterface.orders.add(order);
            chefGraphicalUserInterface.fillTable(waiterGraphicalUserInterface.orders);
            waiterGraphicalUserInterface.fillTable(waiterGraphicalUserInterface.orders);
            for (Order o : waiterGraphicalUserInterface.orders) System.out.println(o.getOrderID());
            waiterGraphicalUserInterface.secondFrame.setVisible(false);

        }
    }

    public class addComputeBillListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = waiterGraphicalUserInterface.getId();
            Order order = restaurant.getOrder(id);
            FileWriter fileWriter = new FileWriter(restaurant,order);
        }

    }
}
