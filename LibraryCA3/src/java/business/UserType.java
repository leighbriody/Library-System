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
public class UserType {
    /*
     CREATE TABLE `user_type` (
     `user_type_id` int(2) NOT NULL,
     `type` varchar(20) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    
    //fields 
    private int userTypeId;
    private String type;
    
    //Constrcutor 
    public UserType(){
        
    }
    
    public UserType(int userTypeId , String type ){
        this.userTypeId = userTypeId;
        this.type = type;
    }
    
    //getters and setters

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   
    //to string 

    @Override
    public String toString() {
        return "UserType{" + "userTypeId=" + userTypeId + ", type=" + type + '}';
    }
    
    
    //hash and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.userTypeId;
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
        final UserType other = (UserType) obj;
        if (this.userTypeId != other.userTypeId) {
            return false;
        }
        return true;
    }
    
    
}
