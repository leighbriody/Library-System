/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import business.SecurityAnswers;
import business.User;
import daos.CardsDao;
import daos.SecurityAnswersDao;
import daos.UserDao;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.AES;
import utilities.Pbkdf2;

public class SignUpCommand implements Command {

    /**
     * @author Brianan Johnson & Osama
     * @param request
     * @param response
     * @return the user if successfully signed up, will be sent to the login
     * page
     */
    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
         String forwardToJsp = "error.jsp";

        //Get the user object details from the sign up page
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            //hash the password
            //here we have to hash the password
            password = Pbkdf2.genPasswordHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        //obtain the security Question
        int securityQuestion1 = Integer.parseInt(request.getParameter("securityQuestion1"));
        String securityAnswer1 = request.getParameter("answer1");
        int securityQuestion2 = Integer.parseInt(request.getParameter("securityQuestion2"));
        String securityAnswer2 = request.getParameter("answer2");
        int securityQuestion3 = Integer.parseInt(request.getParameter("securityQuestion3"));
        String securityAnswer3 = request.getParameter("answer3");

        //Get the payment details
        String cardNumber = request.getParameter("cardNumber");
        String expDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        //Genereate a secret key
        String secretKey = "SecretKey";

        //Encrypt these card detaills
        cardNumber = AES.encrypt(cardNumber, secretKey);
        expDate = AES.encrypt(expDate, secretKey);
        cvv = AES.encrypt(cvv, secretKey);

        // String answer = request.getParameter("answer");
        if (username != null && !username.equals("")
                && password != null && !password.equals("")
                && email != null && !email.equals("")) {
            //Set the user object variablles
            UserDao uDao = new UserDao("librarydb");
            User u = new User();

            //need to add a method to get all usernames from the database
            //this will be used to compare them against each other
            if (uDao.getUserByUsername(username) != null) {
                //Username already exists
                String errorMessage = "Sorry but that username already exists. Please <a href='signUp.jsp'>go back</a> and try again.<br/>Try a different username!";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            } else {

                //Now we need to insert the users payment details into the database
                CardsDao cards = new CardsDao("librarydb");
                cards.insertPaymentDetails(cardNumber, expDate, cvv);

                //we now need to the primary key of that card record for the foreign key in the users table
                int paymentDetailsId = cards.getCardsRecentId();

                u.setPaymentDetailsId(paymentDetailsId);
                u.setUsername(username);
                u.setPassword(password);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setEmail(email);
                u.setPhone(phone);

                
                
                
                int rows = uDao.signUpUser(u.getUsername() , u.getPassword() , u.getFirstName() , u.getLastName() , u.getEmail() , u.getPhone() , u.getPaymentDetailsId());

                if (rows > 0) {

                    forwardToJsp = "login.jsp";

                    //as the user insert is a success we need to get thhe user id
                    int userId = uDao.getUserId(username);

                    //default is -1 in method
                    if (userId > -1) {
                        //add the answers to the db if the user is successfully created
                        SecurityAnswers sAns = new SecurityAnswers();

                        SecurityAnswersDao sAnsDao = new SecurityAnswersDao("login");
                        //set the user id
                        sAns.setUserId(userId);
                        //set the question id
                        sAns.setQuestionId(securityQuestion1);
                        try {
                            //set the users question (we need to hash it, we can use the password hash method)
                            securityAnswer1 = Pbkdf2.genPasswordHash(securityAnswer1);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //add it to the instance
                        sAns.setAnswer(securityAnswer1);
                        //add it to the db
                        sAnsDao.addAnswer(sAns.getQuestionId(), sAns.getUserId(), sAns.getAnswer());
                        //add the answers to the db if the user is successfully created
                        SecurityAnswers sAns2 = new SecurityAnswers();

                        //set the user id
                        sAns2.setUserId(userId);
                        //set the question id
                        sAns2.setQuestionId(securityQuestion2);
                        try {
                            //set the users question (we need to hash it, we can use the password hash method)
                            securityAnswer2 = Pbkdf2.genPasswordHash(securityAnswer2);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //add it to the instance
                        sAns2.setAnswer(securityAnswer2);
                        //add it to the db
                        sAnsDao.addAnswer(sAns2.getQuestionId(), sAns2.getUserId(), sAns2.getAnswer());

                        //add the answers to the db if the user is successfully created
                        SecurityAnswers sAns3 = new SecurityAnswers();
                        //set the user id
                        sAns3.setUserId(userId);
                        //set the question id
                        sAns3.setQuestionId(securityQuestion3);
                        try {
                            //set the users question (we need to hash it, we can use the password hash method)
                            securityAnswer3 = Pbkdf2.genPasswordHash(securityAnswer3);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //add it to the instance
                        sAns3.setAnswer(securityAnswer3);
                        //add it to the db
                        sAnsDao.addAnswer(sAns3.getQuestionId(), sAns3.getUserId(), sAns3.getAnswer());

                        //Sign up successful, log the user in
                        u = uDao.getUserByUsernamePassword(username, password);
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInUser", u);

                        forwardToJsp = "home.jsp";
                    }
                } else {
                    //User couldn't be added to the db, output error
                    //(username, hashedPassword, email, paymentDetailsId);
                    String errorMessage = "Sorry you could not be registered " + "username: " + username + "<br/> hashed pw: " + password + "<br/> email: " + email + "<br/> pay: " + paymentDetailsId
                            + "<br/>  + rows: " + rows + "<br/>Please <a href='signUp.jsp'>go back</a> and try again.<br/>Try a different username!"
                    + "User" + "<br/>" 
                             + "Username" + u.getUsername() + "<br/>" 
                             + "Password" +  u.getPassword() +"<br/>" 
                             + "FirstName" + u.getFirstName() +"<br/>" 
                             + "Last" + u.getLastName() + "<br/>" 
                            + "Email" + u.getEmail() + "<br/>" 
                            + "Phone " + u.getPhone() + "<br/>" ;
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            }
        } else {
            // One or more fields were missing
            // Send the user to the error page and inform them of this
            String errorMessage = "One or more fields were missing. Please <a href='signUp.jsp'>go back</a> and try again." + " " + securityQuestion1;
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }

        return forwardToJsp;

    }

}
