package models;

/**
 *
 * @author guss
 */
public class Subject 
{
    int id;
    int credits;
    String name;
    String fillable[] = new String[]{"id","credits","name"};
    
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
