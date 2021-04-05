<%-- 
    Document   : viewBooksByGenre
    Created on : 03-Jan-2021, 19:49:16
    Author     : User
--%>



<%@page import="java.util.ResourceBundle"%>
<%@page import="daos.AuthorDao"%>
<%@page import="business.Author"%>
<%@page import="daos.BookAuthorDao"%>
<%@page import="business.BookAuthor"%>
<%@page import="business.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.BookDao"%>
<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
      
    <title><%=dataBundle.getString("home_title")%></title>
       <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!-- Bootstrap core CSS -->
        <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <link href="blog.css" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
         <div class="col-4 pt-1">
            <a class="text-muted" href="home.jsp"><%=dataBundle.getString("home_name")%></a>
          </div>
         
          <!-- SHOW TABLE OF ALL THE BOOKS  -->
         <h1><%=dataBundle.getString("viewBook_GenreKey")%></h1>
       <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
          <a class="p-2 text-muted" href="viewBooksByGenre.jsp?genre=1"><%=dataBundle.getString("displayBooks_biography")%></a>
          <a class="p-2 text-muted" href="viewBooksByGenre.jsp?genre=2"><%=dataBundle.getString("displayBooks_thriller")%> </a>
          <a class="p-2 text-muted" href="viewBooksByGenre.jsp?genre=3"><%=dataBundle.getString("displayBooks_Romance")%></a>
          <a class="p-2 text-muted" href="viewBooksByGenre.jsp?genre=4"><%=dataBundle.getString("displayBooks_Comedy")%></a>
          <a class="p-2 text-muted" href="viewBooksByGenre.jsp?genre=5"><%=dataBundle.getString("displayBooks_NonFiction")%></a>
                 
        </nav>
      </div>
          <%  
      // Get the user object from the session
            User u = (User) session.getAttribute("loggedInUser");
            // If the user has successfully logged in (and their session hasn't expired yet)
            // Then display a greeting to them
            if(u != null){
        %>
        
        
        
         <%
            //Get the name of the genre that was clicked on
            String idVal = request.getParameter("genre");
           
            //Confirm that a name was supplied
            if (idVal != null) {
                // parse as parameter to Strings)
                int genre = 0;
               
                try {
                    genre = Integer.parseInt(idVal);
                    
                } catch (NumberFormatException e) {
                    String errorMessage = "Text was supplied for the author id (instead of a number)";
                    session.setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("error.jsp");
                }
          
                BookDao bookDao = new BookDao("librarydb");
                ArrayList<Book> gen = bookDao.viewAllBooksByGenre(genre);
                
                if(gen != null && !gen.isEmpty()){
                    
                for(Book g: gen){
                  
                         %>
   
    <!-- SHOW TABLE OF ALL THE BOOKS  -->
    <h1></h1>         
       
        <table class="table">
            <thead class="thead-light" >
                <tr>                    
                    <th scope="col"><%=dataBundle.getString("displayBooks_bookName")%> </th> 
                                      
                </tr>
            </thead>
            
            <%   //Loop to print our all rows
                for (Book b : gen) 
                {                  
            %> 
            <!--Create a row for each book-->
            
                <tr>                
                    <td><a href="viewBook.jsp?book_id=<%=b.getBookId()%>"><%=b.getBookName()%></a></td> 
                    
                </tr>    
            
            <%
                }  //Close book loop

               }//Close if

        
           }//Close BookAuthor Loop

            %>
        </table>
        <%
            }else
        {
                        out.println("No books found.Please try again later" );
        }
        %>
  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../../../assets/js/vendor/popper.min.js"></script>
    <script src="../../../../dist/js/bootstrap.min.js"></script>
    <script src="../../../../assets/js/vendor/holder.min.js"></script>
    <script>
      Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
      });
    </script>
        
         <%
            }
            // Otherwise, redirect them to the login page and tell them to try again
            else{
                String sessionExpired = "Your session has expired, please log in again.";
                session.setAttribute("sessionExpired", sessionExpired);
                response.sendRedirect("login.jsp");
            }
        %>  
    
        
    </body>
</html>