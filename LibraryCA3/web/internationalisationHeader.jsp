<%-- 
    Document   : internationalisationHeader
    Created on : 03-Dec-2017, 22:17:10
    Author     : michelle
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Retrieve the appropriate Locale - check if it's already been set within the site
    Locale clientLocale = (Locale) session.getAttribute("currentLocale");

    // If there was no locale already set -- it's their first time here or their session timed out
    if(clientLocale == null){
        // Get the locale for the client's browser (that is what's stored in the request)
        clientLocale = request.getLocale();
        // Save it as the currently selected locale
        session.setAttribute("currentLocale", clientLocale);
    }
%>
<!-- create a form to change the language based on changing the drop down selection -->
<form action="controller.jsp" method="post">
    <!-- When the value of the drop down changes, 
    submit the form and send the value to the controller -->
    <select name ="language" onchange="this.form.submit()">
        <%
            String language = (String) session.getAttribute("language");
            if(language == null || language.equals("en")){
                
        %>
            <option value="en" selected>English</option>
            <option value="fr">French</option>
        <%
            }else{
        %>
            <option value="en">English</option>
            <option value="fr" selected>French</option>
        <%        
            }
        %>
    </select>
    <input type="hidden" name="action" value="changeLanguage"/>
</form>
