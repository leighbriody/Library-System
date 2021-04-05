/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Leigh Briody
 */
public class User {
    /*
    CREATE TABLE `users` (
  `user_id` int(7) NOT NULL,
  `first_name` varchar(15) DEFAULT NULL,
  `last_name` varchar(15) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL,
  `username` int(15) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `access` int(11) DEFAULT NULL,
  `user_type` varchar(25) DEFAULT NULL,
  `address` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    */
    
    //Create fields based on databas table of users.
    private int userId;
    private static int nextId = 1;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phone;
    private int accessId;
    private int userTypeId;
    private int addressId;
    
    //Create our constructor 
    
    public User(){
        
    }
    
     public User(String username, String password , String email){
        this.username = username;
        this.email = email;        
        this.password = password;
       
        
        //Note : we will need to validate username and password before creating objects.
    }
     
      public User(int userId, String username, String password , String email){
        this.userId = userId;
        this.username = username;
        this.email = email;        
        this.password = password;
       
        
        //Note : we will need to validate username and password before creating objects.
    }
     
//    int userId , WE DO NOT INITALIZE THE USERID AS THAT SHOULD INCREMENT BY ITSELF
    public User( String firstName , String lastName , String email , String username , String password , String phone , int accessId , int userTypeId , int addressId){
        
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.accessId = accessId;
        this.userTypeId = userTypeId;
        this.addressId = addressId;
        
        //Note : we will need to validate username and password before creating objects.
    }

 
    //Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    
    //Equals and to string
    //2 users are equal if they have the same user id
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
    
    //to string

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username=" + username + ", password=" + password + ", phone=" + phone + ", accessId=" + accessId + ", userTypeId=" + userTypeId + ", addressId=" + addressId + '}';
    }
    
    
    //Format
    
}
