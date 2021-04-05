

<%@page import="business.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Book Results</title>
        <!-- Bootstrap core CSS -->
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <link href="blog.css" rel="stylesheet">
        
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
     <body>
        
           
      
         
        <div></div>
       
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
        <nav class="nav d-flex justify-content-between nav-tabs">
           
             <a class="p-2 text-muted " href="FactoryServlet?action=home">Home</a>
          <a class="p-2 text-muted" href="FactoryServlet?action=displayBooks">View Books</a>
           <a class="p-2 text-muted nav-item nav-link active" href="searchBook.jsp">Search Book</a>
          <a class="p-2 text-muted" href="#">Borrow Book</a>
          <a class="p-2 text-muted" href="#">Return Book</a>
          <a class="p-2 text-muted" href="FactoryServlet?action=viewUserDetails">Profile</a>
          <a class="p-2 text-muted" href="FactoryServlet?action=changeUserDetails">Edit Profile</a>
          <a class="p-2 text-muted" href="FactoryServlet?action=viewCurrentLoans">Current Loans</a>
          <a class="p-2 text-muted" href="#">Previous Loans</a>
          <a class="p-2 text-muted" href="FactoryServlet?action=OverdueFees"><%=dataBundle.getString("menu_OverdueFees")%></a>
          <a class="p-2 text-muted" href="#">Pay  Fees</a>
          
        </nav>
      </div>

      <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
        <div class="col-md-6 px-0">
          <h1 class="display-4 font-italic">Books Found</h1>
          <p class="lead my-3">Here are the reuslts for what .</p>
         
        </div>
      </div>
        
        <!--RESULTS FOR WHAT BOOK THE USER JUST SEARCHED FOR -->
           <%
            // Get the result from the session (all books matching that search )
            Object resultValue = session.getAttribute("books");
            // If we have a result value to use, then display it
            if (resultValue != null)
            {
                // Cast it to an ArrayList of Customers
                ArrayList<Book> books = (ArrayList<Book>) resultValue;
        %> 
        
          <!-- display the number of books found containing that name -->
          <h2>There were <%=books.size()%> Books found in the database containing that name.</h2>
        
         <%
                // Check if there were books updated. If so, get the list of Books and display them
                if (books.size() > 0)
                {
                
            %>    
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Book Name</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Description</th>
                    <th scope="col" >Quantity </th>                    
                </tr>
                </thead>
                 <%
                    for (Book b : books)
                    {
                %>
                <!-- Create a row for this book -->
                <tbody>
                <tr>
                    <!-- Create a cell for each component of this customer's information and fill it with 
                         data in this customer's object -->
                     <td><a href="viewBook.jsp?book_id=<%=b.getBookId()%>"><%=b.getBookName()%>"</a></td> 
                    <td><%=b.getGenreId() %></td>
                    <td><%=b.getDescription() %></td>
                    <td><%=b.getQuantity() %></td>
                </tr>
                </tbody>
                  <%
                    }//end loop
                }//end if
            %>

            </table>
             <a href="searchBook.jsp" > <button class="btn btn-primary">Search Another Book</button></a>
            
             <%
                // Finish with book matches 
                // remove the value from the session
                session.removeAttribute("books");
        } else
        {
        %>
      
             No book matches found.
            <a href="searchBook.jsp" > <button class="btn btn-danger">Try A Different Name</button></a>
             
         <%
        }
        %>
         <%@ include file = "footer.jsp" %>

     
 

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
       
      
        
    
    </div>    
    </body>
</html>
