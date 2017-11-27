package models;
import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class Location
{
    String name;
    ArrayList<Table> tables;
    String locationsConnected[];
    boolean status;
    

    public Location(String name,boolean status, ArrayList<Table> tables,String locationConnected[])
    {
        this.name = name;
        this.tables = tables;
        this.status = status;
        this.locationsConnected = locationConnected;
    }

    public String[] getLocationsConnected()
    {
        return locationsConnected;
    }

    public void setLocationsConnected(String[] locationsConnected)
    {
        this.locationsConnected = locationsConnected;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Table> getTables()
    {
        return tables;
    }

    public void setTables(ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
    @Override
    public String toString()
    {
        return tables.toString();
    }
}
