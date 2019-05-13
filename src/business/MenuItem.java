package business;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {

    public int computePrice()
    {
        throw new UnsupportedOperationException();
    }

    public String getName()
    {
        throw new UnsupportedOperationException();
    }

    public String setName()
    {
        throw new UnsupportedOperationException();
    }

    public ArrayList<MenuItem> getMenuItems()
    {
        throw  new UnsupportedOperationException();
    }

    public ArrayList<MenuItem> setMenuItems()
    {
        throw new UnsupportedOperationException();
    }

    public int getPrice()
    {
        throw new UnsupportedOperationException();
    }

    public int setPrice()
    {
        throw new UnsupportedOperationException();
    }


}
