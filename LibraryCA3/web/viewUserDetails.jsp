<%-- 
    Document   : home
    Created on : 08-Dec-2020, 23:19:00
    Author     : Brianan Johnson
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="business.Loan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.LoanDao"%>
<!--

viewbooks 
search for books
borrow book 
return book 

view my profile 
edit my profile 
view my current loans 
view previous loans 
view overdue fee
pay overdue fee
logout


-->

<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
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
      
         <title><%=dataBundle.getString("viewUser_title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <!-- Bootstrap core CSS -->
        <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <link href="blog.css" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>


        <!--
        <%
            // Get the user object from the session
            User u = (User) session.getAttribute("loggedInUser");

            // If the user has successfully logged in (and their session hasn't expired yet)
            // Then display a greeting to them
            if (u != null) {
        %>
         
        <div></div>
        <a href="LibraryServlet?action=logout">Logout</a>
       Include a footer so that there is always a link back to the home page! -->
        <div class="container">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1">
                        <a class="text-muted" href="heme.jsp"><%=dataBundle.getString("home_name")%></a>
                    </div>

                    <div class="col-4 d-flex justify-content-end align-items-center">

                        <a class="btn btn-sm btn-outline-secondary" href="FactoryServlet?action=logout"><%=dataBundle.getString("menu_logout")%></a>
                    </div>
                </div>

            </header>

            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between nav-tabs">
                   <a class="p-2 text-muted" href="FactoryServlet?action=displayBooks"><%=dataBundle.getString("menu_viewBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=displayBooksAndAuthors"><%=dataBundle.getString("menu_viewBooksByAuthor")%></a>
                    <a class="p-2 text-muted" href="searchBook.jsp"><%=dataBundle.getString("menu_searchBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=borrowBook"><%=dataBundle.getString("menu_borrowBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=returnBook"><%=dataBundle.getString("menu_returnBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewUserDetails"><%=dataBundle.getString("menu_viewProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=changeUserDetails"><%=dataBundle.getString("menu_editProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewCurrentLoans"><%=dataBundle.getString("menu_currentLoans")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewPreviousLoans"><%=dataBundle.getString("menu_previousLoans")%></a>
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_OverdueFees")%></a>
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_payFees")%></a>

                </nav>
            </div>

            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic"><%=u.getUsername()%>'s <%=dataBundle.getString("viewUser_profile")%></h1>
                    <p class="lead my-3"><%=dataBundle.getString("viewUser_username")%> <%=u.getUsername()%></p>
                    <p class="lead my-3"><%=dataBundle.getString("viewUser_FirstName")%><%=u.getFirstName()%></p>
                    <p class="lead my-3"><%=dataBundle.getString("viewUser_Email")%> <%=u.getEmail()%></p>
                    <p class="lead my-3"><%=dataBundle.getString("viewUser_Phone")%> <%= u.getPhone()%>  </p>
                </div>
            </div>
            <%
                //get loan info
                LoanDao lDao = new LoanDao("librarydb");
                double overdueFee = lDao.getUsersTotalOverdueFee(u.getUserId());
                ArrayList<Loan> activeLoans = lDao.allLoansActive(u.getUserId());
                ArrayList<Loan> previousLoans = lDao.allLoansMade(u.getUserId());
                //loop to get total of previous loans made
                int completedLoans = 0;
                for (Loan l : previousLoans) {
                    if (l.getDateReturned() != null) {
                        completedLoans++;
                    }
                }
            %>
            <div class="row mb-2">
                <div class="col-md-6">
                    <div class="card flex-md-row mb-4 box-shadow h-md-250">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-primary"><%=dataBundle.getString("viewUser_activeLoans")%></strong>
                            <h3 class="mb-0">
                                <a class="text-dark" href="#"><%=dataBundle.getString("viewUser_activeLoansMsg1")%>  <%= activeLoans.size()%><%=dataBundle.getString("viewUser_activeLoansMsg2")%></a>
                            </h3>
                            <div class="mb-1 text-muted"><%=dataBundle.getString("viewAllLoans")%>  </div>
                            <a class="btn btn-success stretched-link" href="FactoryServlet?action=viewCurrentLoans"><%=dataBundle.getString("viewUser_activeLoans")%></a>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card flex-md-row mb-4 box-shadow h-md-250">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-success"><%=dataBundle.getString("viewUser_LoanHistory")%> </strong>
                            <h3 class="mb-0">
                                <a class="text-dark" href="#"><%=dataBundle.getString("viewUser_LoanHistoryMsg1")%> <%=dataBundle.getString("viewUser_LoanHistoryMsg2")%></a>
                            </h3>
                            <div class="mb-1 text-muted"><%=dataBundle.getString("viewUser_viewMsg")%></div>
                            <a class="btn btn-primary stretched-link" href="FactoryServlet?action=viewPreviousLoans"><%=dataBundle.getString("viewUser_LoansBtn")%></a>
                        </div>

                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card flex-md-row mb-4 box-shadow h-md-250">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-success"><%=dataBundle.getString("viewUser_Overdue")%> </strong>
                            <h3 class="mb-0">
                                <a class="text-dark" href="#"><%=dataBundle.getString("viewUser_Debt")%> €<%=overdueFee %> <!--OVERDUE FEE CALCULATION--> </a>
                            </h3>
                            <div class="mb-1 text-muted">Each Time you return a book late you get a fee of €10.</div>
                           
                            <button class="btn btn-danger">Pay Fee's</button>
                        </div>

                    </div>
                </div>

               


                <footer class="blog-footer">
                </footer>
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
                } // Otherwise, redirect them to the login page and tell them to try again
                else {
                    String sessionExpired = "Your session has expired, please log in again.";
                    session.setAttribute("sessionExpired", sessionExpired);
                    response.sendRedirect("login.jsp");
                }
            %>  





    </body>
</html>


