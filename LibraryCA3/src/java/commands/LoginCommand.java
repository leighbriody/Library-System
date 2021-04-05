/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.User;
import daos.UserDao;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.LibraryServlet;
import utilities.Pbkdf2;

/**
 *
 * @author User
 */
public class LoginCommand implements Command{
    
    public static void main(String[] args) {
        
    }
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response){
        String forwardToJsp = null;
        
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        
        
         //If the fields are not null or empty we can proceed
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            UserDao uDao = new UserDao("librarydb");

            //We get the user object that matches the username passed
            User u = uDao.getUserByUsername(username);

            // If a user was found, then there is a user with this username    
            if (u != null) {

                boolean validUser = false;

                try {

                    //now we need to validate the password
                    validUser = Pbkdf2.validatePassword(password, u.getPassword());

                    if (validUser) {
                        // Put the user in the session
                        // (we can use this to track if the user has logged in -
                        // if it's there, they they have completed this process
                        // if it's not, then they haven't)
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInUser", u);

                        forwardToJsp = "home.jsp";

                    } else {

                    }

                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(LibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                // The username and/or password didn't match someone in the database
                // Send the user to the error page and inform them of this
                String errorMessage = "No user found matching those details."
                        + "Please <a href='login.jsp'>go back</a> and try again." + username;
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            // The username and/or password was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username and/or password was missing. Please <a href='login.jsp'>go back</a> and try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }
        
        
        
        
}
