package presentation;

import business.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

public class ChefGraphicalUserInterface implements Observer {
    JFrame mainFrame = new JFrame();
    JTable table = new JTable(10,5);
    private ArrayList<Order> order = new ArrayList<>();
    public ChefGraphicalUserInterface()
    {
        fillTable(order);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(200,100));
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        mainFrame.add(panel);
        mainFrame.setTitle("Chef Frame");
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(mainFrame.getX()+420,mainFrame.getY());
    }
    void fillTable(ArrayList<Order> orders)
    {
        fillTable(orders, this.table);
    }

    static void fillTable(ArrayList<Order> orders, JTable table) {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[][] data = new String[10][10];
        String[] columns = {"Id", "Table", "Date"};
        tableModel.setColumnIdentifiers(columns);
        for(int i = 0; i < orders.size(); i++)
        {
            data[i][0] = orders.get(i).getOrderID() + "";
            data[i][1] = orders.get(i).getTable() + "";
            data[i][2] = orders.get(i).getDate().toString();
            tableModel.addRow(data[i]);
        }
        table.setModel(tableModel);
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(this.mainFrame,"New order!");
        ArrayList<Order> orders = (ArrayList<Order>)arg;
         fillTable(orders);
    }
}
