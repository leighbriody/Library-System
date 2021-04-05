<%-- 
    Document   : displayBooks
    Created on : 28-Dec-2020, 14:48:30
    Author     : Brianan Johnson                                         
--%>

<%@page import="business.Book"%>
<%@page import="daos.BookDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <%@include file="internationalisationHeader.jsp" %>
       <%
            // Retrieve the resource bundle from the session
            ResourceBundle dataBundle = (ResourceBundle) session.getAttribute("dataBundle");
            // If there is no bundle stored (i.e. if this is the first time you're coming to the site)
            if(dataBundle == null){
                // Create a resource bundle based on the client's current locale settings
                dataBundle = ResourceBundle.getBundle("languages.librarydb", clientLocale);

                // Store this resource bundle for future use
                session.setAttribute("dataBundle", dataBundle);
            }
            
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body>
       
        <%
            // Get the name of the book that was clicked on
            String idVal = request.getParameter("book_id");
            // Confirm that a name was supplied
            if(idVal != null){
                // parse as parameter to Strings)
                int book_id = 0;
                try{
                book_id = Integer.parseInt(idVal);
                }
                catch(NumberFormatException e){
                 String errorMessage = "Text was supplied for the book id (instead of a number)";
                 session.setAttribute("errorMessage", errorMessage);
                 response.sendRedirect("error.jsp");
                }
                //Retrieve the book from the db and display
               BookDao bookDao = new BookDao("librarydb");
               Book b = bookDao.getBookById(book_id);
                if(b != null){
            
        %>  
                    <!-- set up table structure -->
                    <table>
                        <tr>
                            <th>Book Id</th>
                            <th>Book Name</th>
                            <th>Genre ID</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Overdue Fee Per Day</th>                    
                            
                            
                        </tr>
                        <!-- Create a row for this customer -->
                        <tr>
                            <!-- Create a cell for each component of this customer's information and fill it with 
                                 data in this customer's object -->
                            <td><%=b.getBookId() %></td>
                            <td><%=b.getBookName()%></td>
                            <td><%=b.getGenreId() %></td>
                            <td><%=b.getDescription()%></td>
                            <td><%=b.getQuantity()%></td>
                            <td><%=b.getOverdueFeePerDay()%></td>
                        </tr>
                    </table>
        
        <%
                }else{
        %>
        <div><b>No book was found with that name.</b></div>
        <%
                }
                // Close the if statement
            }
            else{
                // Set an error message and redirect to the error page
                String errorMessage = "No book name was supplied";
                session.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("error.jsp");
            }
        %>
       
    </body>
</html>
