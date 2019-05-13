package presentation;

import business.CompositeProduct;
import business.MenuItem;
import business.Restaurant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AdministratorGraphicalUserInterface {
    public JFrame mainFrame = new JFrame();
    JFrame secondFrame;
    private JButton addButton = new JButton("Add item");
    private JButton editButton = new JButton("Edit item");
    private JButton deleteButton = new JButton("Delete item");
    private JTable table = new JTable(7,4);
    private JTextField itemName = new JTextField(5);
    private JTextField itemNameSecondary = new JTextField(6);
    private JTextField itemPrice = new JTextField(3);
    private JButton okButton = new JButton("Insert");
    private JButton okButtonE = new JButton("Edit");
    JCheckBox[] checkBoxes;
    public AdministratorGraphicalUserInterface(Restaurant restaurant)
    {
        fillInTable(restaurant);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),130));
        panel.add(scrollPane);
        JPanel buttonsPanel = new JPanel();
        JPanel columnsPanel = new JPanel();
        columnsPanel.add(new JLabel("Name "));
        columnsPanel.add(itemName);
        columnsPanel.add(new JLabel("Price"));
        columnsPanel.add(itemPrice);
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Edit name"));
        panel1.add(itemNameSecondary);
        panel.add(columnsPanel);
        panel.add(panel1);
        panel.add(buttonsPanel);
        mainFrame.add(panel);
        mainFrame.setTitle("Administrator Frame");
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(mainFrame.getX()-420,mainFrame.getY());
    }

    void newFrame(ArrayList<business.MenuItem> menuItems)
    {
         secondFrame = new JFrame();
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
        panel.add(okButtonE);
        secondFrame.add(panel);
        secondFrame.pack();
        secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        secondFrame.setVisible(true);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setLocation(secondFrame.getX()-420,secondFrame.getY());
    }

    String getName()
    {
        if(!itemName.getText().equals(""))
            return itemName.getText();
        return null;
    }
    Integer getPrice()
    {
        if(!itemPrice.getText().equals(""))
            return Integer.parseInt(itemPrice.getText());
        return 0;
    }
    String getSecondaryName()
    {
        if(!itemNameSecondary.getText().equals(""))
            return itemNameSecondary.getText();
        return null;
    }

    void fillInTable(Restaurant restaurant)
    {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columns = {"Name","Price","Ingredients","Type"};
        String[][] tableData = new String[restaurant.getMenuItems().size() + 1][10];
        int i = 0;
        tableModel.setColumnCount(4);
        tableModel.setColumnIdentifiers(columns);

        for(MenuItem m:restaurant.getMenuItems())
        {
            if(m.getClass() == CompositeProduct.class) {
                tableData[i][0] = m.getName();
                tableData[i][1] = m.getPrice() + "";
                tableData[i][2]= "";
                for(MenuItem menuItem:m.getMenuItems())
                    tableData[i][2] += menuItem.getName() + ";";
                tableData[i][3] = "CompositeProduct";
                i++;
            }
            else
            {
                tableData[i][0] = m.getName();
                tableData[i][1] = m.getPrice() + "";
                tableData[i][2] = "none";
                tableData[i][3] = "BaseProduct";
                i++;
            }
        }
        tableData[i] = null;
        i = 0;
        while(tableData[i]!=null){tableModel.addRow(tableData[i]);i++;}
        this.table.setModel(tableModel);
    }

    void addMenuItemInsertListener(ActionListener a){addButton.addActionListener(a);}
    void addMenuItemDeleteListener(ActionListener a){deleteButton.addActionListener(a);}
    void addMenuItemEditListener(ActionListener a){editButton.addActionListener(a);}
    void addMenuItemListListener(ActionListener a){okButton.addActionListener(a);}
    void addOkEditListener(ActionListener a){okButtonE.addActionListener(a);}

}
