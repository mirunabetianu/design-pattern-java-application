package presentation;

import business.BaseProduct;
import business.MenuItem;
import business.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WaiterGraphicalUserInterface{
    JFrame mainFrame = new JFrame();
    JFrame secondFrame = new JFrame();
    private JButton addButton = new JButton("Add order");
    private JButton computeBill = new JButton("Compute bill");
    private JTextField idOrder = new JTextField(3);
    private JTextField tableOrder = new JTextField(3);
    private JTextField dateOrder = new JTextField(7);
    JTable table = new JTable(10,5);
    JButton okButton = new JButton("Place order");
    JCheckBox[] checkBoxes;
    ArrayList<Order> orders = new ArrayList<>();
    public WaiterGraphicalUserInterface()
    {
        fillTable(orders);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),100));
        panel.add(scrollPane);
        JPanel columnPanel = new JPanel();
        columnPanel.add(new JLabel("Id"));
        columnPanel.add(idOrder);
        columnPanel.add(new JLabel("Table"));
        columnPanel.add(tableOrder);
        columnPanel.add(new JLabel("Date"));
        columnPanel.add(dateOrder);
        panel.add(columnPanel);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addButton);
        computeBill.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(computeBill);

        mainFrame.add(panel);
        mainFrame.setTitle("Waiter Frame");
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    void newFrame(HashSet<MenuItem> menuItems)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        checkBoxes = new JCheckBox[menuItems.size()];
        int i = 0;
        for(business.MenuItem m:menuItems)
        {
            checkBoxes[i] = new JCheckBox(m.getName());
            panel.add(checkBoxes[i]);
            i++;
        }
        panel.add(okButton);

        secondFrame.add(panel);
        secondFrame.setTitle("Waiter Frame");
        secondFrame.pack();
        secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        secondFrame.setVisible(true);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setLocation(secondFrame.getX(),secondFrame.getY());
    }

    void fillTable(ArrayList<Order> orders)
    {
        ChefGraphicalUserInterface.fillTable(orders, this.table);
    }

    void addOrderInsertListener(ActionListener a){addButton.addActionListener(a);}
    void addOrderBillListener(ActionListener a){computeBill.addActionListener(a);}
    void addPlaceOrderListener(ActionListener a){okButton.addActionListener(a);}

    Integer getId()
    {
        if(!idOrder.getText().equals(""))
            return Integer.parseInt(idOrder.getText());
        return 0;
    }
    Integer getTable()
    {
        if(!tableOrder.getText().equals(""))
            return Integer.parseInt(tableOrder.getText());
        return 0;
    }
    Date getDate()
    {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(dateOrder.getText());
            return date;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(mainFrame,"Wrong date. Please try again!");
        }
        return null;
    }

}
