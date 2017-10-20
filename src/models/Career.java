package models;

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
    String fillable[] = new String[]{"id","name","year"};

    public Career(int id, String name, Date year) 
    {
        this.id = id;
        this.name = name;
        this.year = year;
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
