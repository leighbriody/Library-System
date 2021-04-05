/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.Book;
import business.SecurityAnswers;
import business.User;
import daos.BookDao;
import daos.CardsDao;
import daos.SecurityAnswersDao;
import daos.UserDao;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.AES;
import utilities.Pbkdf2;

/**
 * Author Brianan Johnson
 */
@WebServlet(name = "LibraryServlet", urlPatterns = {"/LibraryServlet"})

public class LibraryServlet extends HttpServlet {

    /**
     * Brianan Johnson, Servlet controller that will handle the actions and call
     * the correct methods
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //Check for the action that the user wants to do
        String action = request.getParameter("action");
        // If an action was provided, choose which to do next
        String forwardToJsp = null;
        //Check its not null
        if (action != null) {
            switch (action) {
                case "signup":
                    forwardToJsp = signUpAction(request, response);
                    //in here we need to hash password
                    System.out.println("Inside the sign up case");

                    break;
                case "login":
                    forwardToJsp = loginAction(request, response);

                    break;
                case "changeSecurity":
                    //  forwardToJsp = changeSecurityAction(request, response);
                    break;
                case "displayBooks":
                    forwardToJsp = displayBooksAction(request, response);
                    break;
                case "logout":
                    forwardToJsp = logoutAction(request, response);
                    break;
                case "changeUserDetails":
                    forwardToJsp = changeUserDetailsAction(request, response);
                    break;
                case "viewUserDetails":
                    forwardToJsp = viewUserDetailsAction(request, response);
                    break;
                case "searchBooks":
                    forwardToJsp = searchBooksAction(request, response);
                    break;

                default:
                    forwardToJsp = noAction(request, response);
                    break;
            }
        } else {
            forwardToJsp = noAction(request, response);
        }
        response.sendRedirect(forwardToJsp);
    }

//*****************************ACTION METHODS***********************************
    /**
     * This method will be used for when a user wants to change their details
     *
     * @param request
     * @param response
     * @return Returned to the viewUserDetails page after details have been
     * changed
     */
    public String changeUserDetailsAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";

        //get the fields from the form
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        //if the above parameters are not filled in yet (i.e the user is jsut coming to the page)
        //we want to direct them
        if (fName == null && lName == null && email == null && phone == null) {
            forwardToJsp = "editUserDetails.jsp";
            //We need to have at least the username and password field entered to 
            //know the user is comitted to changing their details. 
            //They will be used as a confirmation
        } else {
            if (username != null && !username.equals("") && password != null && !password.equals("")) {
                //create a connection to the db to use functions on it
                UserDao uDao = new UserDao("librarydb");
                //somewhere in here we will use the hashing to get the password
                //now we want to validate the user is in the db
                User u = uDao.getUserByUsernamePassword(username, password);
                //here we will check if they have their firstname, username and password in db 
                //things that should be already there
                if (u.getFirstName() != null && !u.getFirstName().equals("")
                        && u.getUsername() != null && !u.getUsername().equals("")
                        && u.getPassword() != null && !u.getPassword().equals("")) {
                    //now we need to get the user's id and add that into the param for changing details
                    //I am storing these in varaibles for ease of use
                    int userId = u.getUserId();
                    //As it's not null (the result) then we can continue and now change the user details
                    //this will work as if the user hasn't entered a value for any field, 
                    //it will still be "updated" as we have set the value of it to their current info
                    if (uDao.editUserDetails(userId, fName, lName, email, phone) == true) {
                        //we want to validate it has worked and send the user on to view their new details
                        //we want to now set the session to contain the new user details and forward them on
                        u = uDao.getUserByUsernamePassword(username, password);
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInUser", u);
                        forwardToJsp = "viewUserDetails.jsp";
                    } else {
                        //we couldn't successfully update the user's details
                        String errorMessage = "Something went wrong in updating your details"
                                + "Please <a href='editUserDetails.jsp'>go back</a> and try again." + userId + " " + username + " " + password + " " + fName;
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", errorMessage);
                    }
                } else {//User couldn't be found
                    String errorMessage = "Incorrect username or password"
                            + "Please <a href='editUserDetails.jsp'>go back</a> and try again.";
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage", errorMessage);
                }
            } else {
                //Username and password not entered
                String errorMessage = "You must enter your username and password..."
                        + "Please <a href='editUserDetails.jsp'>go back</a> and try again.<br/>";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
            }
        }

        return forwardToJsp;
    }

    /**
     * Brings the user to view their details in a webpage. Note the user's
     * password is not visible as is with some other details as they are
     * sensitive.
     *
     * @param request
     * @param response
     * @return The user is brought to a webpage containing their account details
     */
    public String viewUserDetailsAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "error.jsp";
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("loggedInUser");
        String username = userSession.getUsername();
        // String password = userSession.getPassword();
        //create a DAO to work with
        UserDao uDao = new UserDao("librarydb");
        //get an instance for the user and populate based off the username and password entered 
        User u = uDao.getUserByUsername(username);
        //usernames will always have to exisit so if there are none in the instance then it hasn't worked
        if (u.getUsername() != null && !u.getUsername().equals("")) {
            //forward the user to the page with their user details (send on the u instance)

            forwardToJsp = "viewUserDetails.jsp";
        } else {
            // Send the user to the error page and inform them of this
            String errorMessage = "There seemed to be no username present... Please <a href='signUp.jsp'>go back</a> and try again.";
            session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
        }
        return forwardToJsp;
    }

    public String displayBooksAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "displayBooks.jsp";
        HttpSession session = request.getSession();
        // To log a user out, wipe their session clear

        return forwardToJsp;

    }

    public String searchBooksAction(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = "viewBook.jsp";
        HttpSession session = request.getSession();
        // To log a user out, wipe their session clear

        return forwardToJsp;

    }

    /**
     *
     *
     * @param request
     * @param response
     * @return the user if successfully signed up, will be sent to the login
     * page
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public String signUpAction(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String forwardToJsp = "error.jsp";

        //Get the user object details from the sign up page
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //hash the password
        //here we have to hash the password
        password = Pbkdf2.genPasswordHash(password);

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

                u.setUsername(username);
                u.setPassword(password);
                u.setEmail(email);
                u.setPaymentDetailsId(paymentDetailsId);

                int rows = uDao.signUpUser(u.getUsername(), u.getEmail(), u.getPassword(), u.getFirstName(), u.getLastName(), 0);

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
                        //set the users question (we need to hash it, we can use the password hash method)
                        securityAnswer1 = Pbkdf2.genPasswordHash(securityAnswer1);
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
                        //set the users question (we need to hash it, we can use the password hash method)
                        securityAnswer2 = Pbkdf2.genPasswordHash(securityAnswer2);
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
                        //set the users question (we need to hash it, we can use the password hash method)
                        securityAnswer3 = Pbkdf2.genPasswordHash(securityAnswer3);
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
                    String errorMessage = "Sorry you could not be registered " + "username: " + username + " hashed pw: " + password + " email: " + email + " pay: " + paymentDetailsId
                            + "rows: " + rows + "Please <a href='signUp.jsp'>go back</a> and try again.<br/>Try a different username!";
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

    /**
     * Will be used to generate a salt
     *
     * @return a salt to be used when hashing
     * @throws NoSuchAlgorithmException
     */
    //firstly need to generate a salt
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    /**
     * Converts the salt to a hexadecimal string
     *
     * @param array
     * @return Inputted salt as a hexadecimal
     * @throws NoSuchAlgorithmException
     */
    //need to convert salt to hex
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * The password (param) will be taken and hashed using the following
     * iterations are set to a large number to slow down attackers but are not
     * too large to impact user experience
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    //then we can combine the both to hash the password
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //set iterations, the larger the better as it will significantly slow down an attacker
        int iterations = 100000;
        //keyLength is required output length of hashed function
        int keyLength = 512;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Author Brianan Johnson Method to log out user and redirect the user
     *
     * @param request
     * @param response
     * @return action to jsp
     */
    public String logoutAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession();
        // To log a user out, wipe their session clear
        session.invalidate();
        return forwardToJsp;
    }

    public String loginAction(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;

        // Get the username and password information that the user
        // entered into the form on the previous page
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //If the fields are not null or empty we can proceed
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            UserDao uDao = new UserDao("librarydb");

            //We get the user object that matches the username passed
            User u = uDao.getUserByUsername(username);

            // If a user was found, then there is a user with this username    
            if (u != null) {

                boolean validUser = false;

                try {

                    //now we need to validate the password
                    validUser = Pbkdf2.validatePassword(password, u.getPassword());

                    if (validUser) {
                        // Put the user in the session
                        // (we can use this to track if the user has logged in -
                        // if it's there, they they have completed this process
                        // if it's not, then they haven't)
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInUser", u);

                        forwardToJsp = "home.jsp";

                    } else {

                    }

                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(LibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                // The username and/or password didn't match someone in the database
                // Send the user to the error page and inform them of this
                String errorMessage = "No user found matching those details."
                        + "Please <a href='login.jsp'>go back</a> and try again." + username;
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            // The username and/or password was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username and/or password was missing. Please <a href='login.jsp'>go back</a> and try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

    // Method to handle where there is no valid action supplied within the request
    // This method MUST return a String - the name of the page the client should be
    // redirect to
    public String noAction(HttpServletRequest request, HttpServletResponse response) {
        // Handle where NO action was supplied
        String forwardToJsp = null;
        String errorMessage = "No valid action was supplied in this request" + " Action : " + request.getParameter("action");
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        forwardToJsp = "error.jsp";
        return forwardToJsp;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
