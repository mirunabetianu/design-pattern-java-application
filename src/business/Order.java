package business;

import java.util.Date;

public class Order {
    private int orderID;
    private Date date;
    public int table;

    public Order(int orderID, Date date, int table) {
        this.orderID = orderID;
        this.date = date;
        this.table = table;
    }

    @Override
    public int hashCode() {
        return this.orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getDate() {
        return date;
    }

    public int getTable() {
        return table;
    }
}
