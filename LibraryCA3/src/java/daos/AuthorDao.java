/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brianan Johnson
 */
public class AuthorDao extends Dao implements AuthorDaoInterface {

    public AuthorDao(String databaseName) {
        super(databaseName);
    }

    /**
     * TEST Passed
     * @return all authors stored in the authors table
     */
    @Override
    public ArrayList<Author> displayAllAuthors() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Author> list = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from authors WHERE status = 1";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Author a = new Author(rs.getInt("author_id"), rs.getString("author_first_name"), rs.getString("author_last_name"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the displayAllAuthors() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the displayAllAuthors() method: " + e.getMessage());
            }
        }

        return list;
    }

   
    /**
     * TEST PASSED
     * This method takes an author first and last name and checks if they
     * exists, it doesn't matter if their status is active or not
     * @params authorFirstName,authorLastName
     * @return true or false
     * 
     */
     @Override
    public boolean checkIfAuthorExists(String authorFirstName, String authorLastName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean ifAuthorExists = false;

        try {
            con = getConnection();

            String query = "Select * from authors where author_first_name = ? AND author_last_name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, authorFirstName);
            ps.setString(2, authorLastName);
            rs = ps.executeQuery();

            if (rs.next()) {
                //it means the username returned something therfore a user with that username exists
                ifAuthorExists = true;
            } else {
                ifAuthorExists = false;

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the checkIfAuthorExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the checkIfAuthorExists() method: " + e.getMessage());
            }
        }

        return ifAuthorExists;

    }

    /**
     * TESTED AND PASSED
     * Method accepts an Authors first and last name and returns the ID
     * The ID can then be used for linking books with authors in the bookAuthor table
     * @param authorFirst
     * @param authorLast
     * @return authorId
     */
    @Override
    public int getAuthorIdByName(String authorFirst, String authorLast) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int author_id = 0;
       
       
        try {
            con = getConnection();

            String query = "SELECT author_id from authors WHERE author_first_name = ? AND author_last_name = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1,authorFirst);
            ps.setString(2, authorLast);
            rs = ps.executeQuery();

            if (rs.next()) // While not need as you are just searching for one record and returning it once found
            {
              author_id = rs.getInt("author_id");
               
            } else {
                System.out.println("Author does not exist");
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getAuthorIdByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAuthorIdByName() method: " + e.getMessage());
            }
        }
        return author_id;

    }

    
    /**
     * TEST PASSED
     * The client side form will take a firstname and lastname
     * and put in an object to pass here, 
     * @param newAuthor
     * @return True if added, else false     
     */
    @Override
    public boolean addAuthor(Author newAuthor ){
       
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean authorAdded = false;
      
      try{
          
          con = getConnection();
          
         String query = "INSERT INTO authors (author_first_name, author_last_name) VALUES (?,?)";
          
         ps=con.prepareStatement(query);
         ps.setString(1,newAuthor.getAuthorFirstName());
         ps.setString(2,newAuthor.getAuthorLastName());
         
        rowsAffected = ps.executeUpdate();  
           
      
      } catch (SQLException ex) {
            System.out.println("Exception occured in the addAuthor() method: " + ex.getMessage());
              
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
                System.out.println("Exception occured in the finally section of the addAuthor() method: " + e.getMessage());
            }
        }
         if (rowsAffected > 0) {
                authorAdded = true;
            }

            return authorAdded;
            
        
    }

    /**
     * TEST PASSED
     * Method to set status to 0 if author is not active
     * @param authorFirst
     * @param authorLast
     * @return true is author disabled    
     */
    @Override
    public boolean disableAuthor(String authorFirst, String authorLast) {
       
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean authorDisabled = false;
      
      try {
            con = getConnection(); 
        
      String query = "UPDATE  authors SET status = 0 WHERE author_first_name = ? AND author_last_name = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, authorFirst);
            ps.setString(2, authorLast);
       
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                authorDisabled = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteAuthor() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deleteAuthor() method: " + e.getMessage());
            }
        }

        return authorDisabled;
    }  
   
     /**
     * TEST PASSED
     * Method to set status to 0 if author is not active
     * @param authorFirst
     * @param authorLast
     * @return true is author disabled
     * 
     */
    @Override
    public boolean enableAuthor(String authorFirst, String authorLast) {
       
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean authorEnabled = false;
      
      try {
            con = getConnection(); 
        
      String query = "UPDATE  authors SET status = 1 WHERE author_first_name = ? AND author_last_name = ? AND status =0";
            ps = con.prepareStatement(query);
            ps.setString(1, authorFirst);
            ps.setString(2, authorLast);
       
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                authorEnabled = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteAuthor() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deleteAuthor() method: " + e.getMessage());
            }
        }

        return authorEnabled;
    }  

    @Override
    public ArrayList<Author> displayAuthorById(int author_id) {
         Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Author> author = new ArrayList<>();
       
       
        try {
            con = getConnection();

            String query = "SELECT * from authors WHERE author_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1,author_id);
           
            rs = ps.executeQuery();

            while (rs.next()) // While not need as you are just searching for one record and returning it once found
            {
              Author a = new Author();
              a.setAuthorId(rs.getInt("author_id"));
              a.setAuthorFirstName(rs.getString("author_first_name"));
              a.setAuthorLastName(rs.getString("author_last_name"));
             
              author.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getAuthorIdByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAuthorIdByName() method: " + e.getMessage());
            }
        }
        return author;
        
    }
   
     @Override
    public Author getAuthorById(int author_id) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Author a = null;
       
       
        try {
            con = getConnection();

            String query = "SELECT * from authors WHERE author_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1,author_id);           
            rs = ps.executeQuery();

            while (rs.next()) // While not need as you are just searching for one record and returning it once found
            {
              a = new Author();
              a.setAuthorFirstName(rs.getString("author_first_name"));
              a.setAuthorLastName(rs.getString("author_last_name"));
            
           
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getAuthorIdByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAuthorIdByName() method: " + e.getMessage());
            }
        }
        return a;
        
    }
   
        
    }


