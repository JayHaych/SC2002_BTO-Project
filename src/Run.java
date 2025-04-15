import UI.*;

public class Run 
{
    public static void main(String[] args) 
    {



        int logged_in_user = Login_UI.login();
        switch(logged_in_user)
        {
            case 1:
                Applicant_UI.display();
                break;
            case 2:
                HDB_Officer_UI.display();
                break;
            case 3:
                HDB_Manager_UI.display();
                break;
        }


    }

    
}