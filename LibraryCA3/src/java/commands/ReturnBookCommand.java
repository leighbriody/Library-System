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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Osama Kheireddine
 */
public class ReturnBookCommand implements Command {

    public static void main(String[] args) {

       
    }

    /**
     * Allows the user to return a book based on their selected option in the
     * list
     *
     * @param request
     * @param response
     * @return Returns the user to the appropriate their current loans page and
     * shows what is still left to be returned
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //get the inputs
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
            //if the borrow parameters are empty send the user to the borrow book page
            if (bookIdAsStr == null) {
                forwardToJsp = "returnBook.jsp";
            } else {
                //convert to int
                int bookId = Integer.parseInt(bookIdAsStr);
                //date now..

                //now parse them to string
                //get a book & loan dao to interact with db
                LoanDao lDao = new LoanDao("librarydb");

                Loan loan = lDao.getLoan(userId, bookId);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                String currentDate = dateFormat.format(date).toString();
                String dueDate = loan.getDueDate();

                double overdueFee = 0;

                //if there date is overdue they will have a fee of 10 euro.
                try {
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    Date current = sd.parse(currentDate);
                    Date due = sd.parse(dueDate);
                    if (current.after(due)) {

                        overdueFee = 10.00;
                    }
                } catch (ParseException ex) {
                    System.out.println(ex.toString());
                }
                //if the current date < due date no overdue fee else 10
                //return book with the overdue fee 
                BookDao bDao = new BookDao("librarydb");
                //now to return the book
                boolean bookReturned = lDao.returnBook(currentDate, bookId, userId, overdueFee);
                if (bookReturned == true) {
                    //now to increase the qty of the book again
                    if (bDao.increaseBookQty(bookId, 1) == true) {
                        //take user back to their current loans
                        forwardToJsp = "viewCurrentLoans.jsp";
                    } else {
                        String errorMessage = "An error occurred while returning your book... Please <a href='FactoryServlet?action=returnBook'>Try Again</a>";
                        session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                    }
                } else {
                    String errorMessage = "An error occurred while we were returning your book... " + currentDate + " " + bookId + " " + userId + " " + overdueFee + " Please <a href='FactoryServlet?action=returnBook'>Try Again</a>";
                    session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                }
            }
        } else {
            String errorMessage = "An error occurred, you have been logged out... Please <a href='FactoryServlet?action=login'>Login</a>";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }

        return forwardToJsp;
    }

}
