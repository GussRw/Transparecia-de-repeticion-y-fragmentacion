/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class StudentA extends Student
{

    public StudentA()
    {
        fillable = new ArrayList<String>()
        {{
            add("id");
            add("nombre");
            add("apellido_materno");
            add("apellido_paterno");
            add("direccion");
            add("telefono");
        }};
    }
    
    public StudentA(String col)
    {
        fillable = new ArrayList<String>()
        {{
            add("id");
            add("nombre");
            add("apellido_paterno");
            add("apellido_materno");
            add("direccion");
            add("telefono");
            add(col);
        }};
    }
    
    
    
    public StudentA(int id, String name, String first_lastname, String second_lastname, String address, String phone)
    {
        this.id = id;
        this.name = name;
        this.first_lastname = first_lastname;
        this.second_lastname = second_lastname;
        this.address = address;
        this.phone = phone;
    }
    
    
}
