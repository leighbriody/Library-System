/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Book;
import business.Loan;
import business.User;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import daos.BookDao;
import daos.LoanDao;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class BorrowBookCommand implements Command {

    /**
     * The purpose of this method is to allow a user to make a loan. We will
     * take parameters entered and do the appropriate work needed
     *
     * @param request
     * @param response
     * @return To the user's current loans page if successful or error.jsp if
     * unsuccessful.
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //get the session
        HttpSession session = request.getSession();
        //get the user(for getting id)
        User u = (User) session.getAttribute("loggedInUser");
        int userId = u.getUserId();

        //if the user is logged in...
        if (u.getUsername() != null && !u.getUsername().equals("")) {
            //get the fields needed to make a loan.
            //bookId
            String bookIdAsStr = request.getParameter("bookId");
            //if the loan parameters are empty send the user to the borrow book page
            if (bookIdAsStr == null) {
                forwardToJsp = "borrowBook.jsp";
            } else {
                //convert to int
                int bookId = Integer.parseInt(bookIdAsStr);
                //to get the due date and taken date we need to add 2 weeks on to the current date
                //this means we need to do some work and make a calendar instance
                //date now..
                LocalDate date = java.time.LocalDate.now();
                //date in two weeks
                LocalDate dateInTwoWeeks = date.plusWeeks(2);
                //now parse them to string
                String currentDate = date.toString();
                String twoWeeksDate = dateInTwoWeeks.toString();

                //create the loan and insert it into the db
                LoanDao lDao = new LoanDao("librarydb");
                //decrement the book in the db based off the id
                BookDao bDao = new BookDao("librarydb");
                //cannot borrow the same book twice
                boolean bookOnLoan = lDao.isBookOnLoan(bookId, userId);
                if (!bookOnLoan) {
                    //so we don't loan a minus amount of books (i.e they don't exist)
                    if (bDao.decreaseBookQty(bookId, 1) == true && bDao.getBookById(bookId).getQuantity() > -1) {
                        if (lDao.createLoan(bookId, userId, currentDate, twoWeeksDate) == true) {
                            forwardToJsp = "viewCurrentLoans.jsp";
                        } else {
                            String errorMessage = "An error occurred while creating your loan... Please<a href='FactoryServlet?action=borrowBook'>Try Again</a>";
                            session = request.getSession();
                            session.setAttribute("errorMessage", errorMessage);
                        }
                    } else {
                        String errorMessage = "Could not loan book Please... <a href='FactoryServlet?action=borrowBook'>Try Again</a>";
                        session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                    }
                } 
            else {
                    String errorMessage = "This book is already on loan... <a href='FactoryServlet?action=borrowBook'>Go back</a>";
                    session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                }
            }
        } else {
            String errorMessage = "An error occurred, you have been logged out... Please<a href='FactoryServlet?action=login'>Login</a>";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }
        return forwardToJsp;
    }

}
