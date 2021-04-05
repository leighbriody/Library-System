<%-- 
    Document   : viewBooksByAuthor
    Created on : 31-Dec-2020, 15:44:19
    Author     : User
--%>

<%@page import="business.Book"%>
<%@page import="daos.BookDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.BookAuthor"%>
<%@page import="daos.BookAuthorDao"%>
<%@page import="daos.AuthorDao"%>
<%@page import="business.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Books by Author</title>
    </head>
    <body>
        <h1>View Books by Author</h1>
        
        

        <%
            //Get the name of the author that was clicked on
            String idVal = request.getParameter("author_id");
           
            //Confirm that a name was supplied
            if (idVal != null) {
                // parse as parameter to Strings)
                int author_id = 0;
                int book_id = 0;
                try {
                    author_id = Integer.parseInt(idVal);
                    
                } catch (NumberFormatException e) {
                    String errorMessage = "Text was supplied for the author id (instead of a number)";
                    session.setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("error.jsp");
                }
                //Retrieve the author from the db and display
                AuthorDao authorDao = new AuthorDao("librarydb");
                BookAuthorDao bookAuthorDao = new BookAuthorDao("librarydb");
               
                Author a = authorDao.getAuthorById(author_id);
                
                           
                if (a != null) {
                    %>
                   <table>
            <tr>
                <th>Author</th>
            </tr>
            <tr>
                <td><%=a.getAuthorFirstName()%> <%=a.getAuthorLastName()%></td>
            </tr>
        </table>
            
               <%
               
              ArrayList<BookAuthor> book = bookAuthorDao.getBookIdByAuthorId(author_id);  
               if (book != null && !book.isEmpty() ){
                   
                   for(BookAuthor b : book){
                    book_id = b.getBook_id();
                     // ArrayList<BookAuthor> bookids = new ArrayList(author_id);
                //Loop through the list of books that have this author Id
                //and get the id for each
                   
                //Pass the id into the viewAllBooksById() and return the name of each
                BookDao bookDao = new BookDao("librarydb"); 
                ArrayList<Book> books = bookDao.viewAllBooksById(book_id);
                    
             
               if (books != null && !books.isEmpty() )
            {
               %>
                
                
                <table class="table">
            <thead class="thead-light">
                <tr>
                    <th scope="col">Book Id</th>
                    <th scope="col">Book Name</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Overdue Fee Per Day</th>
                </tr>
            </thead>
    <%
            for(Book bk: books){
            
                %>
                 
              <tbody>
                <tr>
                     <td><%=bk.getBookId()%></td>
                     <td><a href="viewBook.jsp?book_id=<%=bk.getBookId()%>"><%=bk.getBookName()%></a></td> 
                    <td><%=bk.getGenreId()%></td>
                    <td><%=bk.getDescription()%></td>
                    <td><%=bk.getQuantity()%></td>
                    <td><%=bk.getOverdueFeePerDay() %></td>
                </tr>
             
                </table>
        
        <%
           
            }          
   
           //Clost books loop
            }//Close books if
            else {            
        %>
        <div><b>No books were found with that author.</b></div>
       
       <%
                     }// Close the else statement
                }//Close bookauthor loop
            }//Close book if 
        }//Close if a
}
            else{
                // Set an error message and redirect to the error page
                String errorMessage = "No author name was supplied";
                session.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("error.jsp");
            }
        %>
            
        <!-- Include a footer so that there is always a link back to the home page! -->
         <%@ include file = "footer.jsp" %>
    </body>
</html>
