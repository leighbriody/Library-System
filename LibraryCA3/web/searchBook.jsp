<%-- 
    Document   : searchBook
    Created on : 30-Dec-2020, 14:51:08
    Author     : Brianan Johnson
--%>

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
        
   <!-- Bootstrap core CSS -->
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <link href="blog.css" rel="stylesheet">
        <title><%=dataBundle.getString("searchBooks_title")%></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    
    <div class="container">
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

                  
    <div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
            <a class="text-muted" href="home.jsp"><%=dataBundle.getString("home_name")%></a>
          </div>
         
          
        </div>
          
      </header>
   
          <div class="signup-form">
        <h1><%=dataBundle.getString("searchBooks_title")%></h1>

        <div>
            <p><%=dataBundle.getString("searchBooks_instructions")%></p>
            <!-- form going to servlet to trigger action on that end -->
            <form action="FactoryServlet" method="post">
                <div class="form-group">
                    
                    <h2> <%=dataBundle.getString("searchBooks_bookName")%> : </td><td> <input name="bookName" size=30 type="text" /> </h2>
                   </div>
               
                <input type="submit"  class="form-control" value="<%=dataBundle.getString("searchBook_button")%>" />
                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name="action" value="searchBooks">
            </form>
        </div>  
                </div>
        
        <div>
            <a href="error.jsp"></a>
        </div>
                <br>
        <p><%=dataBundle.getString("blog_template")%> <a href="https://getbootstrap.com/"></a></p>
    </div>
    </div>
    </body>
</html>
