package UI;

import Controller.Login_Controller;

public class Login_UI
{
    public static int login()
    {
        System.out.println("___________________________________________________________________________________________");
        System.out.println("Welcome to the HDB Application System!");
        System.out.println("___________________________________________________________________________________________");
        int logged_in = Login_Controller.login();
        return logged_in;
    }
}
