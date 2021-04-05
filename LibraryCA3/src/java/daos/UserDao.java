/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leigh Briody
 */
public class UserDao extends Dao implements UserDaoInterface {

    public static void main(String[] args) {
        UserDao uDao = new UserDao("librarydb");

        User userByUsername = uDao.getUserByUsername("andy");
        System.out.println(userByUsername.toString());

        int rows = uDao.signUpUser("username", "osama@email", "password", "osama", "kheireddine", 1000);
        System.out.println(rows);

        User u = new User();
        String firstName = "Osama";
        String lastName = "Kherieddine";
        String username = "Big";
        String password = "password";
        String email = "email@emiaol.com";
        u.setUsername(username);
        u.setPassword(password);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
//        u.setPaymentDetailsId(paymentDetailsId);
        uDao.signUpUser(u.getUsername(), u.getEmail(), u.getPassword(), u.getFirstName(), u.getLastName(), 6);
        //wasnt allowing to view user details because the user dao was using method get by username and password to get the user , but we dont use this method because of hashing

        //librarydb was spelled wrong in the userdao in the serverlet view details action 
        System.out.println(uDao.getUserByUsername("Osamakh"));
    }

    public UserDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method is used when a user signs up to the service
     *
     * @param username
     * @param email
     * @param password
     * @return the number of rows that the execute has affected. (1)
     * @author Osama Kheireddine
     */
   @Override
    public int signUpUser(String username, String password, String first_name, String last_name,  String email , String phone, int cardId) {
 
        Connection con = null;
        PreparedStatement ps = null;

        int rowsAffected = 0;
        try {
            con = getConnection();

            String query = "INSERT INTO users (id , paymentDetails , first_name, last_name, email, username  , password , phone )VALUES (null , ?, ?, ?, ? ,  ?, ? , ? )";

            ps = con.prepareStatement(query);
             ps.setInt(1, cardId);
            ps.setString(2, first_name);
            ps.setString(3, last_name);
            ps.setString(4, email);
            ps.setString(5, username);
            ps.setString(6,  password);
            ps.setString(7, phone);
           

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occured in the signUpUser() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the signUpUser() method");
                e.getMessage();
            }
        }

        return rowsAffected;
    }

    /**
     * This method will be used mainly to allow the user's security answers to
     * be added to the db
     *
     * @param username
     * @return the user's id
     */
    @Override
    public int getUserId(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            con = getConnection();

            String query = "SELECT id FROM users WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCustomerById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getCustomerById() method: " + e.getMessage());
            }
        }
        return userId;
    }

    /**
     * This method retrieves the user from the db based on the username supplied
     *
     * @param username
     * @return User based off of supplied username
     */
    @Override
    public User getUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {

                u = new User();
                u.setUserId(rs.getInt("id"));
                u.setPaymentDetailsId(rs.getInt("paymentDetails"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                u.setAccessId(rs.getString("access"));
                u.setUserTypeId(rs.getString("user_type"));
                u.setAddressId(rs.getInt("address"));

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getUserByUsername() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return u;
    }

    /**
     * Method will be used to find a specific user based off the username and
     * password supplied in the parameters
     *
     * @param username
     * @param password
     * @return A user with the given username and password
     */
    @Override
    public User getUserByUsernamePassword(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("id"));
                u.setPaymentDetailsId(rs.getInt("paymentDetails"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                u.setAccessId(rs.getString("access"));
                u.setUserTypeId(rs.getString("user_type"));
                u.setAddressId(rs.getInt("address"));
            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getUserByUsernamePassword() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getUserByUsernamePassword() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getUserByUsernamePassword() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return u;
    }

    /**
     * This method will change user details supplied. Validation must be done
     * before using this method
     *
     * @param userId
     * @param fName
     * @param lName
     * @param email
     * @param phone
     * @return A true/false on if the user's details have been updated
     */
    @Override
    public boolean editUserDetails(int userId, String fName, String lName, String email, String phone) {
        Connection conn = null;
        PreparedStatement ps = null;
        //set to false
        boolean successfulUpdate = false;
        int rowsAffected = 0;

        try {
            conn = getConnection();

            String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, userId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                successfulUpdate = true;
            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to edit a specific user in the editUserDetails() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the editUserDetails() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be false (if it doesn't work)
        return successfulUpdate;
    }

    /**
     * This method will allow an admin to disable another member.
     *
     * @param username
     * @return
     */
//    @Override
//
//    public boolean disableUser(String username) {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int rowsAffected = 0;
//        boolean output = false;
//        try {
//            con = getConnection();
//
//            String query = "UPDATE users SET access = 2 WHERE username = ? AND user_type = 1";
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            //as it is an update
//            ps.executeUpdate();
//
//            if (rowsAffected > 0) {
//                output = true;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Exception occured in the disableUser() method: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (con != null) {
//                    freeConnection(con);
//                }
//            } catch (SQLException e) {
//                System.out.println("Exception occured in the finally section of the disableUser() method: " + e.getMessage());
//            }
//        }
//
//        return output;
//    }
    /**
     * This method will check if a username passed as a string already exists.
     *
     * @param username
     * @return
     */
//    @Override
//
//    public boolean checkIfUsernameExists(String username) {
//
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        boolean ifUsernameExists = false;
//
//        try {
//            con = getConnection();
//
//            String query = "Select * from users where username = ?";
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                //it means the username returned something therfore a user with that username exists
//                ifUsernameExists = true;
//            } else {
//                ifUsernameExists = false;
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception occured in the getAllProducts() method: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (con != null) {
//                    freeConnection(con);
//                }
//            } catch (SQLException e) {
//                System.out.println("Exception occured in the finally section of the getAllProducts() method: " + e.getMessage());
//            }
//        }
//
//        return ifUsernameExists;
//
//    }
    /**
     * This method will allow the user to login to the library Returns true if
     * login success and false otherwise
     *
     * @param username
     * @param password
     * @return
     */
//    @Override
//    public boolean loginToLibrary(String username, String password) {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        boolean loginSuccess = false;
//
//        User u = null; //only returning one record so no list is needed
//
//        try {
//            conn = getConnection();
//
//            String query = "Select * from Users WHERE username =? AND password = ? AND access = 1";
//            ps = conn.prepareStatement(query);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//
//            if (rs.next()) // While not need as you are just searching for one record and returning it once found
//            {
//                loginSuccess = true;
//            }
//        } catch (SQLException ex) {
//            System.out.println("Exception occured in the loginToLibrary() method: " + ex.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (conn != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    freeConnection(conn);
//                }
//
//            } catch (SQLException e) {
//                System.out.println("Exception occured the the finally section of the loginToLibrary() method: " + e.getMessage());
//            }
//        }
//        return loginSuccess;
//    }
    /**
     * This method will get the current user that is logged in , it will get
     * their username and password and return all their details as a user object
     *
     * @param username
     * @param password
     * @return
     */
//    public User getUserLoggedIn(String username, String password) {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        User userFound = new User();
//
//        //String firstName , String lastName , String email , String username , String password , String phone , int accessId , int userTypeId , int addressId
//        int rowsAffected = 0;
//
//        boolean ifUserWasRegistered = false;
//
//        try {
//            con = getConnection();
//
//            String query = "select * from users where username = ? and password = ?";
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                int userId = rs.getInt("user_id");
//                String firstName = rs.getString("first_name");
//                String lastName = rs.getString("last_name");
//                String email = rs.getString("email");
//                String pass = rs.getString("password");
//                String phone = rs.getString("phone");
//                int access = rs.getInt(("access"));
//                int userType = rs.getInt("user_type");
//                int address = rs.getInt("address");
//                String user = rs.getString("username");
//
//                //String firstName , String lastName , String email , String username , String password , String phone , int accessId , int userTypeId , int addressId
//                userFound = new User(userId, firstName, lastName, email, username, password, phone, access, userType, address);
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception occured in the getUserLoggedIn() method: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (con != null) {
//                    freeConnection(con);
//                }
//            } catch (SQLException e) {
//                System.out.println("Exception occured in the finally section of the getUserLoggedIn() method: " + e.getMessage());
//            }
//        }
//
//        return userFound;
//    }
    //make get user type menu
    /**
     * This method will check if the username passed is an admin or member user
     * True if admin and false otherwise
     *
     * @param username
     * @return
     */
//    @Override
//    public boolean isUserAdmin(String username) {
//
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        boolean userIsAdmin = false;
//        try {
//            con = getConnection();
//
//            String query = "SELECT * from users where username = ? and user_type = 2";
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                userIsAdmin = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception occured in the getUserType() method: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (con != null) {
//                    freeConnection(con);
//                }
//            } catch (SQLException e) {
//                System.out.println("Exception occured in the finally section of the getUserType() method: " + e.getMessage());
//            }
//        }
//        return userIsAdmin;
//    }
    /**
     * This method is to return all the usernames so that they can be displayed.
     *
     * @return
     */
//    @Override
//    public ArrayList<User> displayUsernames() {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ArrayList<User> userList = new ArrayList();
//
//        try {
//            con = getConnection();
//
//            String query = "SELECT * FROM users";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                //Get all the data fields
//                int userId = rs.getInt("user_id");
//                String fName = rs.getString("first_name");
//                String lName = rs.getString("last_name");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                String phone = rs.getString("phone");
//                int accessId = rs.getInt("access");
//                int userTypeId = rs.getInt("user_type");
//                int addressId = rs.getInt("address");
//                String userName = rs.getString("username");
//
//                //create the object
//                User user = new User(userId, fName, lName, email, userName, password, phone, accessId, userTypeId, addressId);
//                //Add it to the array list.
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception occured in the displayUsernames() method: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (con != null) {
//                    freeConnection(con);
//                }
//            } catch (SQLException e) {
//                System.out.println("Exception occured in the finally section of the displayUsernames() method: " + e.getMessage());
//            }
//        }
//
//        return userList;
//
//    }
    @Override
    public boolean logout() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   

    }

//    public boolean addAddress(String address_line_1, String address_line2, String county, String city, String country) {
//
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
//    }
    @Override
    public int signUpUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
