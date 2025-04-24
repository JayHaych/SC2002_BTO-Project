package UI;

import Controller.Login_Controller;

/**
 * The {@code Login_UI} class handles the user interface for logging into the HDB Application System.
 * It displays a welcome message and delegates the login process to the {@link Login_Controller}.
 */
public class Login_UI {

    /**
     * Prompts the user with a welcome message and initiates the login process.
     * Delegates the actual authentication logic to the {@code Login_Controller.login()} method.
     *
     * @return An integer representing the user's role after login:
     *         <ul>
     *             <li>1 - Applicant</li>
     *             <li>2 - HDB Officer</li>
     *             <li>3 - HDB Manager</li>
     *             <li>Any other value may indicate an unsuccessful login</li>
     *         </ul>
     */
    public static int login() {
        System.out.println("___________________________________________________________________________________________");
        System.out.println("Welcome to the HDB Application System!");
        System.out.println("___________________________________________________________________________________________");
        int logged_in = Login_Controller.login();
        return logged_in;
    }
}