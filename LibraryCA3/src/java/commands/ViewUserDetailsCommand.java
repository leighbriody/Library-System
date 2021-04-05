/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.User;
import daos.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class ViewUserDetailsCommand implements Command {

    /**
     * Brings the user to view their details in a webpage. Note the user's
     * password is not visible as is with some other details as they are
     * sensitive.
     *
     * @param request
     * @param response
     * @return The user is brought to a webpage containing their account details
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("loggedInUser");
        String username = userSession.getUsername();
        // String password = userSession.getPassword();
        //create a DAO to work with
        UserDao uDao = new UserDao("librarydb");
        //get an instance for the user and populate based off the username and password entered 
        User u = uDao.getUserByUsername(username);
        //usernames will always have to exisit so if there are none in the instance then it hasn't worked
        if (u.getUsername() != null && !u.getUsername().equals("")) {
            //forward the user to the page with their user details (send on the u instance)

            forwardToJsp = "viewUserDetails.jsp";
        } else {
            // Send the user to the error page and inform them of this
            String errorMessage = "There seemed to be no username present... Please <a href='signUp.jsp'>go back</a> and try again.";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }
        return forwardToJsp;
    }
}
