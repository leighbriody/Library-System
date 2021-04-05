/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.SecurityAnswers;
import business.User;
import daos.SecurityAnswersDao;
import daos.UserDao;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                    break;
                case "login":
                    forwardToJsp = loginAction(request, response);
                    break;
                case "changeSecurity":
                    //  forwardToJsp = changeSecurityAction(request, response);
                    break;
                case "logout":
                    forwardToJsp = logoutAction(request, response);
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

    /**
     * ********************ACTION METHODS*****************************
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //obtain the security Question
        int securityQuestion1 = Integer.parseInt(request.getParameter("securityQuestion1"));
        String securityAnswer1 = request.getParameter("answer1");
        int securityQuestion2 = Integer.parseInt(request.getParameter("securityQuestion2"));
        String securityAnswer2 = request.getParameter("answer2");
        int securityQuestion3 = Integer.parseInt(request.getParameter("securityQuestion3"));
        String securityAnswer3 = request.getParameter("answer3");

        // String answer = request.getParameter("answer");
        if (username != null && !username.equals("")
                && password != null && !password.equals("")
                && email != null && !email.equals("")) {
            //need to add a method to get all usernames from the database
            //this will be used to compare them against each other

            User u = new User();
            UserDao uDao = new UserDao("login");

            u.setUsername(username);
            //here we have to hash the password
            password = hashPassword(password);
            u.setPassword(password);
            u.setEmail(email);

            int rows = uDao.signUpUser(u.getUsername(), u.getEmail(), u.getPassword());
            if (rows > 0) {
                //as the user-insert is a success, we need to get the user's id
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
                    securityAnswer1 = hashPassword(securityAnswer1);
                    //add it to the instance
                    sAns.setAnswer(securityAnswer1);
                    //add it to the db
                    sAnsDao.addAnswer(sAns.getQuestionId(), sAns.getUserId(), sAns.getAnswer());

                    //Sign up successful, log the user in
                    u = uDao.getUserByUsernamePassword(username, password);
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInUser", u);

                    forwardToJsp = "home.jsp";
                }
            } else {
                //User couldn't be added to the db, output error
                String errorMessage = "Sorry you could not be registered"
                        + "Please <a href='signUp.jsp'>go back</a> and try again.<br/>Try a different username! " + rows + " " + username + " " + securityQuestion1;
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
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

        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            UserDao uDao = new UserDao("login");
            User u = uDao.getUserByUsernamePassword(username, password);
            // If a user was found, then there is a user with this username and password
            // so we can log them in
            if (u != null) {
                // Put the user in the session 
                // (we can use this to track if the user has logged in -
                // if it's there, they they have completed this process
                // if it's not, then they haven't)
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", u);
                forwardToJsp = "home.jsp";
            } else {
                // The username and/or password didn't match someone in the database
                // Send the user to the error page and inform them of this
                String errorMessage = "No user found matching those details."
                        + "Please <a href='login.jsp'>go back</a> and try again.";
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
        String errorMessage = "No valid action was supplied in this request";
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
