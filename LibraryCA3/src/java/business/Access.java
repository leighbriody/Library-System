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
public class Access {
    /*
     CREATE TABLE `access` (
     `access_id` int(2) NOT NULL,
     `access` varchar(20) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    
    //instance fields 
    private int accessId;
    private String access;
    
    //Constrcutors
    public Access(){
        
    }
    
    public Access(int accessId , String access){
        this.accessId = accessId;
        this.access = access;
    }
    
    //getters and setters

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    
    //To String

    @Override
    public String toString() {
        return "Access{" + "accessId=" + accessId + ", access=" + access + '}';
    }
    
    //equals and hashcode 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.accessId;
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
        final Access other = (Access) obj;
        if (this.accessId != other.accessId) {
            return false;
        }
        return true;
    }
    
}
