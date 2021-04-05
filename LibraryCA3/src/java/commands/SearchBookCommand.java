/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.Book;
import daos.BookDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brianan Johnson
 */
public class SearchBookCommand implements Command{
    
     @Override
     public String doAction(HttpServletRequest request, HttpServletResponse response){
        String forwardToJsp = null;
        
        String bookName = request.getParameter("bookName");
        
        //Confirm information is valid
        if(bookName !=null && !bookName.equals(""))
        {
            BookDao bookDao = new BookDao("librarydb");
            ArrayList<Book> books = bookDao.searchForBooks(bookName);
            
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

             // Add the list of matching customers to the session (so it can be displayed on the results JSP)
            session.setAttribute("books", books);
            
             // Set the page to be viewed to the book search results page
            forwardToJsp = "searchBookResults.jsp";
            
        }else        
        {
           // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            //Display an error message to the user to inform what went wrong
            session.setAttribute("errorMessage", "You did not supply a name to search for");
        }
        return forwardToJsp;
    }
    
}
