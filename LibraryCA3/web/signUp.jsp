<%-- 
    Document   : signUp
    Created on : 08-Dec-2020, 20:04:05
    Author     : Brianan Johnson
--%>

<%@page import="daos.SecurityQuestionsDao"%>
<%@page import="business.SecurityQuestions"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            body {
                color: #fff;
                background: #777;
                    font-family: 'Roboto', sans-serif;
            }
            .form-control {
                height: 40px;
                box-shadow: none;
                color: #969fa4;
            }
            .form-control:focus {
                border-color: #5cb85c;
            }
            .form-control, .btn {        
                border-radius: 3px;
            }
            .signup-form {
                width: 450px;
                margin: 0 auto;
                padding: 30px 0;
                font-size: 15px;
            }
            .signup-form h2 {
                color: #636363;
                margin: 0 0 15px;
                position: relative;
                text-align: center;
            }
            .signup-form h2:before, .signup-form h2:after {
                content: "";
                height: 2px;
                width: 30%;
                background: #d4d4d4;
                position: absolute;
                top: 50%;
                z-index: 2;
            }	
            .signup-form h2:before {
                left: 0;
            }
            .signup-form h2:after {
                right: 0;
            }
            .signup-form .hint-text {
                color: #999;
                margin-bottom: 30px;
                text-align: center;
            }
            .signup-form form {
                color: #999;
                border-radius: 3px;
                margin-bottom: 15px;
                background: #f2f3f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .signup-form .form-group {
                margin-bottom: 20px;
            }
            .signup-form input[type="checkbox"] {
                margin-top: 3px;
            }
            .signup-form .btn {        
                font-size: 16px;
                font-weight: bold;		
                min-width: 140px;
                outline: none !important;
            }
            .signup-form .row div:first-child {
                padding-right: 10px;
            }
            .signup-form .row div:last-child {
                padding-left: 10px;
            }    	
            .signup-form a {
                color: #fff;
                text-decoration: underline;
            }
            .signup-form a:hover {
                text-decoration: none;
            }
            .signup-form form a {
                color: #5cb85c;
                text-decoration: none;
            }	
            .signup-form form a:hover {
                text-decoration: underline;
            }


        </style>
    </head>
    <body>
   <body>
        <div class="signup-form">
            <form action="FactoryServlet" method="post">
                <h2>Register</h2>
                <p class="hint-text">Create your account. It's free and only takes a minute.</p>
                <div class="form-group">
                    <div class="row">
                        <div class="col"><input type="text" class="form-control" name="username" placeholder="Username" required="required"></div>
                        <div class="col"><input type="password" class="form-control" name="password" placeholder="Password" required="required"></div>
                    </div>        	
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name" required="required">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name" required="required">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" placeholder="Email" required="required">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone" required="required">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="cardNumber" placeholder="Credit Card Number" required="required">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="expiryDate" placeholder="Expiry Date" required="required">
                </div>   
                <div class="form-group">
                    <input type="text" class="form-control" name="cvv" placeholder="CVV" required="required">
                </div>  

                <!--security questions -->
                <!--SECURITY QUESTIONS -->
                <%
                    //here we will retrieve all of the security questions from the db
                    //create a security question object
                    SecurityQuestionsDao question = new SecurityQuestionsDao("login");
                    //create an arrayList to populate (populate while initalising)
                    ArrayList<SecurityQuestions> questionList = (ArrayList) question.getAllQuestions();
                %>


                <!--QUESTION 1 -->

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <select class="form-control" name="securityQuestion1" required>
                        <!--start loop-->
                        <%
                            for (SecurityQuestions s : questionList) {
                        %>
                        <!--get the id in the value-->
                        <!--display the question as text in the option-->
                        <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                        <%
                                //end of loop
                            }
                        %>

                    </select>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <input name="answer1"class="form-control" placeholder="Answer" required size=130 type="text" required/>
                    </div>

                </div>


                <!--QUESTION 2  -->

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <select class="form-control" name="securityQuestion2" required>
                        <!--start loop-->
                        <%
                            for (SecurityQuestions s : questionList) {
                        %>
                        <!--get the id in the value-->
                        <!--display the question as text in the option-->
                        <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                        <%
                                //end of loop
                            }
                        %>

                    </select>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <input name="answer2" class="form-control"  placeholder="Answer" required size=130 type="text" required/>
                    </div>

                </div>

                <!--QUESTION 3  -->

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <select class="form-control" name="securityQuestion3" required>
                        <!--start loop-->
                        <%
                            for (SecurityQuestions s : questionList) {
                        %>
                        <!--get the id in the value-->
                        <!--display the question as text in the option-->
                        <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                        <%
                                //end of loop
                            }
                        %>

                    </select>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <input name="answer3" class="form-control"  placeholder="Answer" required size=130 type="text" required/>
                    </div>

                </div>


                <div class="form-group">
                    <button type="submit" value="signUp" name="action" class="btn btn-primary btn-lg btn-block">Register Now</button>
                </div>
                <div class="form-group">

                </div>
            </form>
            <a href="login.jsp"><button type="submit" value="" name="action" class="btn btn-secondary btn-lg btn-block">Already a member ? Login</button></a>
            <div class="text-center">Already have an account? <a href="#">Sign in</a></div>
        </div>
    </body>
</html>