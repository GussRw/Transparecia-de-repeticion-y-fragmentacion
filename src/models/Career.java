package models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author guss
 */
public class Career 
{
    int id;
    String name;
    Date year;
    ArrayList<String> fillable = new ArrayList<String>()
    {{
        add("id");
        add("nombre");
        add("año");
    }};

    public ArrayList<String> getFillable()
    {
        return fillable;
    }

    public void setFillable(ArrayList<String> fillable)
    {
        this.fillable = fillable;
    }

    public Career(int id, String name, Date year) 
    {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Career()
    {
        this.id = -1;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Date getYear() 
    {
        return year;
    }

    public void setYear(Date year) 
    {
        this.year = year;
    }
    
}
