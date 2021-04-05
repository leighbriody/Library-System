/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;


import business.User;

/**
 *
 * @author Leigh Briody
 */
public interface UserDaoInterface {

    //1 -Register with the library , providing more than a username and password
  public int signUpUser(String username, String email, String phone ,  String password, String first_name, String last_name, int cardId);

    public int getUserId(String username);
    
    public int signUpUser(User u);

    public User getUserByUsernamePassword(String username, String password);
    
    public User getUserByUsername(String username);
    
    public boolean editUserDetails(int userId, String fName, String lName, String email, String phone);
//    public ArrayList<User> displayUsernames();
//    
//    public boolean checkIfUsernameExists(String username);
//    
//    public boolean loginToLibrary(String username , String password);    
//    
//    //change return type to int 1 - user 2 - admin
//    public boolean isUserAdmin(String username);

    public boolean logout();

//    public boolean addAddress(String address_line_1, String address_line2, String county, String city,String country);
//    
//    public boolean disableUser(String username);
}
