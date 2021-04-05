/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class GenreDao extends Dao implements GenreDaoInterface{

    public GenreDao(String databaseName) {
        super(databaseName);
    }

     @Override
     /**
      * This method will return a array list of all genres from which will be displayed to the user
      */
    public ArrayList<Genre> showAllGenres(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Genre> list = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from genre";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
     
            while (rs.next()) {
                Genre g = new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));
                list.add(g);
            } 
        } catch (SQLException e) {
            System.out.println("Exception occured in the showAllGenres() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the displayAllGenres() method: " + e.getMessage());
            }
        }

        return list;
    }
    
    @Override
    /**
     * This method will alow the user to check if the genre passed as a paramater exists
     * @param genre
     */
    public boolean checkIfGenreExists(String genre) {
       
      Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean ifCoAuthorExists = false;

        try {
            con = getConnection();
            
            //This is not working, it will only return true if 2 authors and 0 are entered but not 3 ids
            String query = "Select * from genre where genre_name = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, genre);
            rs = ps.executeQuery();

            if (rs.next()) {
                //it means the username returned something therfore a coAuthor with 1 or 2 of these names exists
                ifCoAuthorExists = false;
            } else {
                ifCoAuthorExists = true;

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the checkIfGenreExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the checkIfGenreExists() method: " + e.getMessage());
            }
        }

        return ifCoAuthorExists;

    }   

    /**
     *This method will allow the user to add a new genre
     * @param newGenre
     * @return
     */
    @Override
    
    public boolean addGenre(Genre newGenre) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int rowsAffected = 0;

        boolean ifaddGenreAdded = false;

        try {
            con = getConnection();

            String genre = newGenre.getGenreName();
                        
            String query = "INSERT INTO genre VALUES (null,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, genre);
          
            
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the addGenre() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the addGenre() method: " + e.getMessage());
            }
        }

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }

        
    }
    
}
