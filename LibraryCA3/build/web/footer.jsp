<%-- 
    Document   : footer
    Created on : 29-12-2020
    Author     : Brianan Johnson
--%>
<html>
    <head>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
      <title><%=dataBundle.getString("displayBooks_title")%></title>  
<!DOCTYPE html>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div>
            <a href="index.jsp"><%=dataBundle.getString("footer_title")%></a>
            
             <p><%=dataBundle.getString("blog_template")%> <a href="https://getbootstrap.com/"></a></p>
                    <p>
                        <a href="#">Back to top</a>
                    </p>
        </div>
    </body>
</html>
