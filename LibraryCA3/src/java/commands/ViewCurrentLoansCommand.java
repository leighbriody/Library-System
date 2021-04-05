/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Loan;
import business.User;
import daos.BookDao;
import daos.LoanDao;
import daos.UserDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class ViewCurrentLoansCommand implements Command {
    
    /**
     * Here the user will be able to view their current loans & if any are overdue 
     * @param request
     * @param response
     * @return Returns the user to their current loan page
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //we need to get the user's details to make sure the page is tailored to their information
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("loggedInUser");
        String username = userSession.getUsername();
        //see if there is an active user session (this is done by verifying a username is supplied)
        if (username != null && !username.equals("")) {
            //now forward the user to the viewCurrentLoans webpage
            forwardToJsp = "viewCurrentLoans.jsp";
        } else {
            // Send the user to the error page and inform them
            String errorMessage = "There seems to be no username... Please <a href='login.jsp'>Login</a> and try again.";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }

        return forwardToJsp;
    }

}
