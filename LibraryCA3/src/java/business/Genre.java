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
public class Genre {
    /*
        
     CREATE TABLE `genre` (
     `genre_id` int(2) NOT NULL,
     `genre_name` varchar(20) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    
    //fields 
    private int genreId;
    private String genreName;
    
    //constructor;#
    public Genre(){
        
    }
    
    public Genre(int genreId , String genrName){
        this.genreId = genreId;
        this.genreName = genreName;
    }
    
    //Getters and setters;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    
    
    //To string

    @Override
    public String toString() {
        return "Genre{" + "genreId=" + genreId + ", genreName=" + genreName + '}';
    }
    
    //Hash and equals#

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.genreId;
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
        final Genre other = (Genre) obj;
        if (this.genreId != other.genreId) {
            return false;
        }
        return true;
    }
    
    
}
