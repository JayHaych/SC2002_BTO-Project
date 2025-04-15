package Entity;
import java.util.ArrayList;

public class Applicant_list
{
    private ArrayList<Applicant> applicantList;
    private int count;

    public ArrayList<Applicant> getList() 
    {
        return applicantList;
    }

    public Applicant_list()
    {
        applicantList = new ArrayList<Applicant>();
        count = 0;
    }

    public void addApplicant(Applicant applicant)
    {
        applicantList.add(applicant);
        count++;
    }

    public void removeApplicant(Applicant applicant)
    {
        applicantList.remove(applicant);
        count--;
    }

    public User getApplicant(int index)
    {
        if (index >= 0 && index < count) {
            return applicantList.get(index);
        } else {
            return null;
        }
    }

    public int getCount()
    {
        return count;
    }

}

