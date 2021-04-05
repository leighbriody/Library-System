

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

        <%  
      // Get the user object from the session
            User u = (User) session.getAttribute("loggedInUser");
        
          
 
            // If the user has successfully logged in (and their session hasn't expired yet)
            // Then display a greeting to them
            if(u != null){
        %>
           

         
        
     
    <div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
            <a class="text-muted" href="#">The Library</a>
          </div>
         
          <div class="col-4 d-flex justify-content-end align-items-center">
           
            <a class="btn btn-sm btn-outline-secondary" href="FactoryServlet?action=logout">Logout</a>
          </div>
        </div>
          
      </header>

      <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between nav-tabs ">
           <a class="p-2 text-muted" href="FactoryServlet?action=displayBooks"><%=dataBundle.getString("menu_viewBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=displayBooks"><%=dataBundle.getString("menu_viewBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=displayBooksAndAuthors"><%=dataBundle.getString("menu_viewBooksByAuthor")%></a>
                    <a class="p-2 text-muted" href="searchBook.jsp"><%=dataBundle.getString("menu_searchBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=borrowBook"><%=dataBundle.getString("menu_borrowBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=returnBook"><%=dataBundle.getString("menu_returnBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewUserDetails"><%=dataBundle.getString("menu_viewProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=changeUserDetails"><%=dataBundle.getString("menu_editProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewCurrentLoans"><%=dataBundle.getString("menu_currentLoans")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewPreviousLoans"><%=dataBundle.getString("menu_previousLoans")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=OverdueFees"><%=dataBundle.getString("menu_OverdueFees")%></a>
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_payFees")%></a>

        </nav>
      </div>

     <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
        <div class="col-md-6 px-0">
          <h1 class="display-4 font-italic"><%=dataBundle.getString("displayBooks_greeting")%></h1>
          <p class="lead my-3"><%=dataBundle.getString("displayBooks_message")%></p>
          
        </div>
      </div>

        <!-- SHOW TABLE OF ALL THE BOOKS  -->
        <h1><%=dataBundle.getString("menu_borrowBook")%></h1>
       <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
          <a class="p-2 text-muted" href="FactoryServlet?action=displayAllBiography"><%=dataBundle.getString("displayBooks_biography")%></a>
          <a class="p-2 text-muted" href="FactoryServlet?action=displayAllThriller"><%=dataBundle.getString("displayBooks_thriller")%> </a>
          <a class="p-2 text-muted" href="FactoryServlet?action=displayAllRomance"><%=dataBundle.getString("displayBooks_Romance")%></a>
          <a class="p-2 text-muted" href="FactoryServlet?action=displayAllComedy"><%=dataBundle.getString("displayBooks_Comedy")%></a>
          <a class="p-2 text-muted" href="FactoryServlet?action=displayAllNon-Fiction"><%=dataBundle.getString("displayBooks_NonFiction")%></a>
                 
        </nav>
      </div>
        
        <%
            BookAuthorDao bookAuthorDao = new BookAuthorDao("librarydb");
            AuthorDao authorDao = new AuthorDao("librarydb");
           // BookDao bookDao = new BookDao("librarydb");
            
           int author_id = 0;
           int book_id = 0;
           ArrayList<BookAuthor> bookAuthor = bookAuthorDao.displayAllBooksAndAuthors();
           //Check its not null and not emplty 
           //loop through the bookAuthor list to retrieve the bookId and the authorId
           if(bookAuthor !=null && !bookAuthor.isEmpty()){
               for(BookAuthor ba : bookAuthor){
                    book_id = ba.getBook_id();
                    author_id = ba.getAuthor_id();
              
                
           //Pass the author id and book id into the methods to retrieve their names
            ArrayList<Author> author = authorDao.displayAuthorById(author_id);
            ArrayList<Book> book = bookAuthorDao.displayBooksByName(book_id);
         
            //Check that list is not null and not empty 
            if (author != null && !author.isEmpty() && book !=null && !book.isEmpty())
            {

        %>
        <table class="table">
            <thead class="thead-light" >
                <tr>                    
                    <th scope="col"><%=dataBundle.getString("displayBooks_bookName")%> </th> 
                    <th scope="col"><%=dataBundle.getString("displayBooks_Author")%> </th> 
                    
                </tr>
            </thead>
            
            <%   //Loop to print our all rows
                for (Book b : book) 
                {                  
            %> 
            <!--Create a row for each book-->
              <tbody>
                <tr>                
                    <td><a href="viewBook.jsp?book_id=<%=b.getBookId()%>"><%=b.getBookName()  %></a></td> 
                    
               
                 <%   //Loop to print our all rows
                for (Author a : author) 
                {                  
            %> 
            <!--Create a row for each author-->                               
            <td><a href="viewBooksByAuthor.jsp?author_id=<%=a.getAuthorId()%>"><%=a.getAuthorFirstName()%> <%=a.getAuthorLastName() %></a></td>                                       
                </tr>
            <%               
              }  //Close Author loop
            %>
                
                
                
            <%
                }  //Close book loop
            %>

            <%
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
     
 

  
</div>
 
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