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
import utilities.Pbkdf2;

/**
 *
 * @author Osama Kheireddine
 */
public class ChangeUserDetailsCommand implements Command {

    /**
     * This method will be used for when a user wants to change their details
     *
     * @param request
     * @param response
     * @return Returned to the viewUserDetails page after details have been
     * changed
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "error.jsp";

        //get the fields from the form
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        //if the above parameters are not filled in yet (i.e the user is jsut coming to the page)
        //we want to direct them
        if (fName == null && lName == null && email == null && phone == null) {
            forwardToJsp = "editUserDetails.jsp";
            //We need to have at least the username and password field entered to 
            //know the user is comitted to changing their details. 
            //They will be used as a confirmation
        } else {
            if (username != null && !username.equals("") && password != null && !password.equals("")) {
                //create a connection to the db to use functions on it
                UserDao uDao = new UserDao("librarydb");
                //somewhere in here we will use the hashing to get the password

                //now we want to validate the user is in the db
                User u = uDao.getUserByUsername(username);
                //now validate their password
                boolean validUser = false;
                try {
                    validUser = Pbkdf2.validatePassword(password, u.getPassword());
                    if (validUser) {

                        //here we will check if they have their firstname, username and password in db 
                        //things that should be already there
                        if (u.getFirstName() != null && !u.getFirstName().equals("")
                                && u.getUsername() != null && !u.getUsername().equals("")
                                && u.getPassword() != null && !u.getPassword().equals("")) {
                            //now we need to get the user's id and add that into the param for changing details
                            //I am storing these in varaibles for ease of use
                            int userId = u.getUserId();
                            
                            //As it's not null (the result) then we can continue and now change the user details
                            //this will work as if the user hasn't entered a value for any field, 
                            //it will still be "updated" as we have set the value of it to their current info
                            if (uDao.editUserDetails(userId, fName, lName, email, phone) == true) {
                                //we want to validate it has worked and send the user on to view their new details
                                //we want to now set the session to contain the new user details and forward them on
                                u = uDao.getUserByUsername(username);
                                HttpSession session = request.getSession();
                                session.setAttribute("loggedInUser", u);
                                forwardToJsp = "viewUserDetails.jsp";
                            } else {
                                //we couldn't successfully update the user's details
                                String errorMessage = "Something went wrong in updating your details"
                                        + "Please <a href='editUserDetails.jsp'>go back</a> and try again." + userId + " " + username + " " + password + " " + fName;
                                HttpSession session = request.getSession();
                                session.setAttribute("errorMessage", errorMessage);
                            }
                        } else {//User couldn't be found
                            String errorMessage = "User cannot be found (missing some information)"
                                    + "Please <a href='editUserDetails.jsp'>go back</a> and try again.";
                            HttpSession session = request.getSession();
                            session.setAttribute("errorMessage", errorMessage);
                        }
                    } else {
                        //User's password isn't matchin... Be vague as we don't want them to know that
                        String errorMessage = "Invalid username or password"
                                + "Please <a href='editUserDetails.jsp'>go back</a> and try again.";
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    String errorMessage = "Invalid argument supplied";
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                }
            } else {
                //Username and password not entered
                String errorMessage = "You must enter your username and password..."
                        + "Please <a href='editUserDetails.jsp'>go back</a> and try again.<br/>";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
            }
        }

        return forwardToJsp;
    }
}
