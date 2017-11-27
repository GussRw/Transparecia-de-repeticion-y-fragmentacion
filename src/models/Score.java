package models;

import java.util.ArrayList;

/**
 *
 * @author guss
 */
public class Score
{
    int student_id;
    int subject_id;
    int teacher_id;
    int opportunity;
    float score;
    ArrayList<String> fillable = new ArrayList<String>()
    {{
        add("alumno_id");
        add("materia_id");
        add("maestro_id");
        add("oportunidad");
        add("calificacion");
    }};

    public Score()
    {
        this.student_id = -1;
    }

    public Score(int student_id, int subject_id, int teacher_id, int opportunity, float score)
    {
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.opportunity = opportunity;
        this.score = score;
    }

    public int getStudent_id()
    {
        return student_id;
    }

    public void setStudent_id(int student_id)
    {
        this.student_id = student_id;
    }

    public int getSubject_id()
    {
        return subject_id;
    }

    public void setSubject_id(int subject_id)
    {
        this.subject_id = subject_id;
    }

    public int getTeacher_id()
    {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id)
    {
        this.teacher_id = teacher_id;
    }

    public int getOpportunity()
    {
        return opportunity;
    }

    public void setOpportunity(int opportunity)
    {
        this.opportunity = opportunity;
    }

    public float getScore()
    {
        return score;
    }

    public void setScore(float score)
    {
        this.score = score;
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
