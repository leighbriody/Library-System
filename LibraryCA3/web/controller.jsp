<%-- 
    Document   : controller
    Created on : 08-Nov-2020, 21:37:47
    Author     : michelle
--%>

<%@page import="java.net.URISyntaxException"%>
<%@page import="java.net.URI"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Check what the user wants to do
    String action = request.getParameter("action");
    // Create a variable to track where we are going to send the user
    String forwardToJsp = "home.jsp";
    
    // If we got here through a valid interaction, an action value should exist
    if(action != null){
        switch(action){
            
            case "changeLanguage":
                // Update the language to the selected option
                String language = request.getParameter("language");
                // Assuming that the language parameter was supplied
                if(language != null){
                    // Create a locale based on the supplied language
                    Locale currentLocale = new Locale(language);

                    session.setAttribute("currentLocale", currentLocale);
                    session.setAttribute("language", language);
                    // Reset the resource bundle so that it will be updated 
                    // to reflect the current locale when the page is reloaded
                    session.setAttribute("dataBundle", null);
                }

                // Deal with figuring out where to send the client back to
                // As we have the internationalisation language form stored in an include,
                // it could be from any page

                // This will happen even if there was no language parameter supplied
                // That is because we always want to send the user back where they came from
                // (or back to the home page)

                try{
                    // Find the page path that sent us here
                    String refererPage = new URI(request.getHeader("referer")).getPath();
                    // Break the page path up into pieces based on /
                    String[] pathPieces = refererPage.split("/");
                    // Get the actual page name (this will always be the last part)
                    forwardToJsp = pathPieces[pathPieces.length-1];
                } catch (URISyntaxException ex){
                    //Display an error message to the log
                    System.out.println("An error occured when trying to get the page that sent the client here: " + ex.getMessage());
                    // If something goes wrong, default the user back to the home page
                    forwardToJsp = "index.jsp";
                }
                break;
            default:
                forwardToJsp = "error.jsp";
                String error = "No such function available on this site";
                session.setAttribute("errorMessage", error);
        }
    }
    response.sendRedirect(forwardToJsp);
%>