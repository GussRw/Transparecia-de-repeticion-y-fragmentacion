package models;
import java.util.ArrayList;
/**
 *
 * @author guss
 */
public class Table
{
    String name;
    ArrayList<String> fillable;
    ArrayList items;
    public Table(String name, ArrayList<String> fillable)
    {
        this.name = name;
        this.fillable = fillable;
        this.items = new ArrayList();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<String> getFillable()
    {
        return fillable;
    }

    public void setFillable(ArrayList<String> fillable)
    {
        this.fillable = fillable;
    }

    public ArrayList getItems()
    {
        return items;
    }

    public void setItems(ArrayList items)
    {
        this.items = items;
    }
    
    public void add(Object object)
    {
        items.add(object);
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
