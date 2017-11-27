package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class StudentB extends Student
{

    public StudentB()
    {
        fillable = new ArrayList<String>()
        {{
            add("id");
            add("carrera_id");
            add("semestre");
            add("promedio");
            add("titulo");
        }};
    }
    
    public StudentB(String col)
    {
        fillable = new ArrayList<String>()
        {{
            add("id");
            add("carrera_id");
            add("semestre");
            add("promedio");
            add("titulo");
            add(col);
        }};
    }

    public StudentB(int id, int career_id, int nivel, float promedy, String academic_degree)
    {
        this.id = id;
        this.career_id = career_id;
        this.nivel = nivel;
        this.promedy = promedy;
        this.academic_degree = academic_degree;
    }
    
    
}
