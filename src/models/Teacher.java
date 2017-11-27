package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class Teacher
{
    int id;
    String name;
    String first_lastname;
    String second_lastname;
    String academic_degree;

    public Teacher()
    {
        this.id = -1;
    }

    public ArrayList<String> getFillable()
    {
        return fillable;
    }

    public void setFillable(ArrayList<String> fillable)
    {
        this.fillable = fillable;
    }
    
    ArrayList<String> fillable = new ArrayList<String>()
    {{
        add("id");
        add("nombre");
        add("apellido_paterno");
        add("apellido_materno");
        add("titulo");
    }};
    public Teacher(int id, String name, String first_lastname, String second_lastname, String academic_degree)
    {
        this.id = id;
        this.name = name;
        this.first_lastname = first_lastname;
        this.second_lastname = second_lastname;
        this.academic_degree = academic_degree;
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

    public String getFirst_lastname()
    {
        return first_lastname;
    }

    public void setFirst_lastname(String first_lastname)
    {
        this.first_lastname = first_lastname;
    }

    public String getSecond_lastname()
    {
        return second_lastname;
    }

    public void setSecond_lastname(String second_lastname)
    {
        this.second_lastname = second_lastname;
    }

    public String getAcademic_degree()
    {
        return academic_degree;
    }

    public void setAcademic_degree(String academic_degree)
    {
        this.academic_degree = academic_degree;
    }
}
