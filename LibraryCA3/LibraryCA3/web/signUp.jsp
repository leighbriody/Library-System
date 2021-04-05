<%-- 
    Document   : signUp
    Created on : 08-Dec-2020, 20:04:05
    Author     : Brianan Johnson
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="business.SecurityQuestions"%>
<%@page import="daos.SecurityQuestionsDao"%>
<%@page import="daos.SecurityQuestionsDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <h1>Hello Please fill in your details to sign up to the Library</h1>
        <div>
            <form action="LibraryServlet" method="post">
                <table>
                    <tr>
                    <input name="username" required size=20 type="text"  placeholder="Username"/> </td> 
                    </tr>
                    <tr>
                    <input name="firstName" required size=20 type="text"  placeholder="First Name" /> </td> 
                    </tr>
                    <tr>
                    <input name="lastName" required size=20 type="text"  placeholder="Last Name" /> </td> 
                    </tr>
                    <tr>    
                    <input name="email" required size=20 type="email"  placeholder="Email"/> </td> 
                    </tr>
                    <tr>    
                    <input name="phone" required size=20 type="number"  placeholder="Phone"/> </td> 
                    </tr>
                    <tr>    
                    <input name="password" required size=20 type="password"  placeholder="Password"/> </td> 
                    </tr>

                </table>
                <%
                    //here we will retrieve all of the security questions from the db
                    //create a security question object
                    SecurityQuestionsDao question = new SecurityQuestionsDao("login");
                    //create an arrayList to populate (populate while initalising)
                    ArrayList<SecurityQuestions> questionList = (ArrayList) question.getAllQuestions();
                %>
                <!--set up the select/option-->
                <select name="securityQuestion1" required>
                    <!--start loop-->
                    <%
                        for (SecurityQuestions s : questionList) {
                    %>
                    <!--get the id in the value-->
                    display the question as text in the option
                    <option value="<%= s.getId()%>"><%= s.getQuestion()%></option>
                    <%
                            //end of loop
                        }
                    %>
                </select>
                <tr>    
                    <td> <input name="answer1" placeholder="Answer" required size=130 type="text" required/> </td> 
                </tr>

                <select name="securityQuestion2" required>
                    <!--start loop-->
                    <%
                        for (SecurityQuestions s : questionList) {
                    %>
                    <!--get the id in the value-->
                    display the question as text in the option
                    <option value="<%= s.getId()%>"><%= s.getQuestion()%></option>
                    <%
                            //end of loop
                        }
                    %>
                </select>
                <tr>    
                    <td> <input name="answer2" placeholder="Answer" required size=130 type="text" required/> </td> 
                </tr>

                <select name="securityQuestion3" required>
                    <!--start loop-->
                    <%
                        for (SecurityQuestions s : questionList) {
                    %>
                    <!--get the id in the value-->
                    display the question as text in the option
                    <option value="<%= s.getId()%>"><%= s.getQuestion()%></option>
                    <%
                            //end of loop
                        }
                    %>
                </select>
                <tr>    
                    <td> <input name="answer3" placeholder="Answer" required size=130 type="text" required/> </td> 
                </tr>
                </table>
                <input type="submit" value="Sign Up" />
                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="action" value="signup" />
            </form>
        </div>
    </body>
</html>
