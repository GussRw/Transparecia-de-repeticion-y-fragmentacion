package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class Subject 
{
    int id;
    int credits;
    String name;
    ArrayList<String> fillable = new ArrayList<String>()
    {{
        add("id");
        add("nombre");
        add("creditos");
    }};

    public ArrayList<String> getFillable()
    {
        return fillable;
    }

    public void setFillable(ArrayList<String> fillable)
    {
        this.fillable = fillable;
    }

    public Subject()
    {
        this.id = -1;
        this.credits = -1;
        this.name = "no name";
    }
    
    public Subject(int id, int credits, String name) 
    {
        this.id = id;
        this.credits = credits;
        this.name = name;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public int getCredits() 
    {
        return credits;
    }

    public void setCredits(int credits) 
    {
        this.credits = credits;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    
}
