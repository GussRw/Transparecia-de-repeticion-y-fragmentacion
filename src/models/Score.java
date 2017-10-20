package models;

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
    String fillable[] = new String[]{"student_id","subject_id","teacher_id","oportunity","score"};
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
    
    
    
}
