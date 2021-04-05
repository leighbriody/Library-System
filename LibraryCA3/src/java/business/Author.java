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
public class Author {
    /*
     CREATE TABLE `author` (
     `author_id` int(2) NOT NULL,
     `author_first_name` varchar(20) DEFAULT NULL,
     `author_last_name` varchar(20) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */

    //create fields for table 
    private int authorId;
    private String authorFirstName;
    private String authorLastName;
    
    //Constructors
    public Author(){
        
    }
     public Author( String authorFirstName , String authorLastName){
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }
    
    public Author(int authorId , String authorFirstName , String authorLastName){
        this.authorId = authorId;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }
    
    //Getters and setters

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
    
    //To String

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName + '}';
    }
    
    
    //Equals and hash

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.authorId;
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
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        return true;
    }
    
}
