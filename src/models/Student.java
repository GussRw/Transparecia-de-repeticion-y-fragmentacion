package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class Student
{
    int id;
    String name;
    String first_lastname;
    String second_lastname;
    String address;
    String phone;
    int career_id;
    int nivel;
    float promedy;
    String academic_degree;
    ArrayList<String> fillable = new ArrayList<String>()
    {{
        add("id");
        add("nombre");
        add("apellido_paterno");
        add("apellido_materno");
        add("direccion");
        add("telefono");
        add("carrera_id");
        add("semestre");
        add("promedio");
        add("titulo");
    }};

    public Student()
    {
        this.id = -1;
    }

    public Student(int id, String name, String first_lastname, String second_lastname, String address, String phone, int career_id, int nivel, float promedy, String academic_degree)
    {
        this.id = id;
        this.name = name;
        this.first_lastname = first_lastname;
        this.second_lastname = second_lastname;
        this.address = address;
        this.phone = phone;
        this.career_id = career_id;
        this.nivel = nivel;
        this.promedy = promedy;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public int getCareer_id()
    {
        return career_id;
    }

    public void setCareer_id(int career_id)
    {
        this.career_id = career_id;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    public float getPromedy()
    {
        return promedy;
    }

    public void setPromedy(float promedy)
    {
        this.promedy = promedy;
    }

    public String getAcademic_degree()
    {
        return academic_degree;
    }

    public void setAcademic_degree(String academic_degree)
    {
        this.academic_degree = academic_degree;
    }

    public ArrayList<String> getFillable()
    {
        return fillable;
    }

    public void setFillable(ArrayList<String> fillable)
    {
        this.fillable = fillable;
    }
    
}
