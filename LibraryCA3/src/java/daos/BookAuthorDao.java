/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Author;
import business.Book;
import business.BookAuthor;
import business.Genre;
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
// */
public class BookAuthorDao extends Dao implements BookAuthorDaoInterface {

    public BookAuthorDao(String databaseName) {
        super(databaseName);
    }

   @Override
  /**
   * Author Brianan Johnson
   * Method has been tested
   * @return List of all book id's and their associated authors
   * 
   */
    public ArrayList<BookAuthor> displayAllBooksAndAuthors() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookAuthor> list = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from bookauthor WHERE status = 1";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                BookAuthor a = new BookAuthor(rs.getInt("book_id"), rs.getInt("author_id"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the displayAllBooksAndAuthors() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the displayAllBooksAndAuthors() method: " + e.getMessage());
            }
        }

        return list;
    }

    
    
    /**
     * Author Brianan Johnson
     * Method will add a book and author relationship to the bookauthor table
     * First will will select the bookid from the book table, then select the 
     * author id from the author table, and create a new record in the bookauthor table
     * which will generate it's own unique id.  
     * A book can have many authors.
     * @param bookId
     * @param authorId
     * @return True if table has been updated
     */
    @Override
    public boolean addBookAuthor(int bookId, int authorId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean bookAuthorAdded = false;
      
        
              
        try{
            con = getConnection();
                       
            //Query to add the book author to the bookauthor table
            String query3 = "INSERT INTO bookauthor (book_id, author_id)values(?,?)";
            ps = con.prepareStatement(query3);
            ps.setInt(1, bookId);
            ps.setInt(2, authorId);            
           
            rowsAffected = ps.executeUpdate();  
            if(rowsAffected > 0){
                bookAuthorAdded = true;
            }
            
        
    }   catch (SQLException e) {   
           System.out.println("Exception occured in the addBookAuthor() method: " + e.getMessage());  
         
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
                System.out.println("Exception occured in addBookAuthor() method: " + e.getMessage());
            }  
     
    }
       
        return bookAuthorAdded;
    
    }
    
    @Override
    public boolean disableBookAuthor(int bookId, int authorId) {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean bookDisabled = false;
      
      try {
            con = getConnection(); 
        
      String query = "UPDATE  bookauthor SET status = 0 WHERE book_id = ? AND author_id = ? AND status = 1 ";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, authorId); 
                  
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                bookDisabled = true;
            }else{
                bookDisabled = false;
                System.out.println("Sorry that book author does not exist");
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the disableBook() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the disableBook() method: " + e.getMessage());
            }
        }

        return bookDisabled;
    }  

     @Override
    public boolean enableBookAuthor(int bookId, int authorId) {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean bookEnabled = false;
      
      try {
            con = getConnection(); 
        
      String query = "UPDATE  bookauthor SET status = 1 WHERE book_id = ? AND author_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, authorId); 
                  
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                bookEnabled = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the disableBook() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the disableBook() method: " + e.getMessage());
            }
        }

        return bookEnabled;
    }  
 
      @Override
      public ArrayList<BookAuthor> getAllBookAuthors(){
       
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookAuthor> list = new ArrayList();
        

        try {
            con = getConnection();

            String query = "Select * from bookauthor WHERE status =1";
            ps = con.prepareStatement(query);          
            rs = ps.executeQuery();

            while (rs.next()) {
                //If rs.next this means the book id worked so we will return the quantity
                BookAuthor ba = new BookAuthor(rs.getInt("book_id"),rs.getInt("author_id"));
                list.add(ba);
            
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookAuthorID() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookAuthorID() method: " + e.getMessage());
            }
        }
        return list;
    }
    @Override
    public boolean deleteBookAuthor(int bookId, int authorId) {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int rowsAffected = 0;
      boolean bookDeleted = false;
      
      try {
            con = getConnection(); 
        
      String query = "DELETE from  bookauthor WHERE book_id = ? AND author_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, authorId); 
                  
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                bookDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteBookAuthor() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deleteBookAuthor() method: " + e.getMessage());
            }
        }

        return bookDeleted;
    }  

    
   /**
    * pass in the id of an author and return the name from the author table
    * @param AuthorId
    * @return 
    */
    @Override
    public ArrayList<Author> displayAuthorsByName(int AuthorId) {
        
        ArrayList<Author> authors = new ArrayList();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try
        {
        // Get a connection to the database
            conn = getConnection();
            
            // Set up the SQL and compile it for the database
            String query = "Select * from authors where author_id = ? AND status = 1";
            ps = conn.prepareStatement(query);
            
            ps.setInt(1,AuthorId);
            
            //Run query and get results
            rs = ps.executeQuery();    
            
            while(rs.next())
            {
              Author a = new Author(rs.getInt("author_id"),rs.getString("author_first_name"),
              rs.getString("author_last_name"));             
                
              authors.add(a);
            }
        }
        catch(SQLException e)
        {
            System.out.println("An exception occurred in the displayAuthorsByName(): " + e.getMessage());
        }
        finally
        {
            // Close resultset
            try{
                if(rs != null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the ResultSet of the displayAuthorsByName(): " + e.getMessage());
            }
            // Close prepared statement
            try{
                if(ps != null)
                {
                    ps.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the PreparedStatement of the displayAuthorsByName(): " + e.getMessage());
            }
            // Close connection
            freeConnection(conn);
        }
        
        // Return results
        return authors;
    }    

    @Override
    public ArrayList<Book> displayBooksByName(int BookId) {
       
       ArrayList<Book> books = new ArrayList();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try
        {
        // Get a connection to the database
            conn = getConnection();
            
            // Set up the SQL and compile it for the database
            String query = "Select * from books where book_id = ? AND status = 1";
            ps = conn.prepareStatement(query);
            
            ps.setInt(1,BookId);
            
            //Run query and get results
            rs = ps.executeQuery();    
            
            while(rs.next())
            {
              
              
            Book b = new Book(rs.getInt("book_id"),rs.getString("book_name"),
            rs.getInt("genre"),rs.getString("description"), rs.getInt("quantity"),
            rs.getDouble("overdue_fee_per_day"));
                         
              books.add(b);
            }
        }
        catch(SQLException e)
        {
            System.out.println("An exception occurred in the displayBooksByName(): " + e.getMessage());
        }
        finally
        {
            // Close resultset
            try{
                if(rs != null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the ResultSet of the displayBooksByName(): " + e.getMessage());
            }
            // Close prepared statement
            try{
                if(ps != null)
                {
                    ps.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the PreparedStatement of the displayBooksByName(): " + e.getMessage());
            }
            // Close connection
            freeConnection(conn);
        }
        
        // Return results
        return books;
       
    }

/**
 * Checks the bookAuthor table for all records that have this author ID
 * @param authorId
 * @return ArrayList of bookIds
 */
    @Override
    public ArrayList<BookAuthor> getBookIdByAuthorId(int authorId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList books = new ArrayList();
        

        try {
            con = getConnection();

            String query = "Select * from bookauthor where author_id = ? AND status = 1 ";
            ps = con.prepareStatement(query);
          
          
            ps.setInt(1, authorId);
            rs = ps.executeQuery();

            while (rs.next()) {
             
             BookAuthor ba = new BookAuthor();
             ba.setBook_id(rs.getInt("book_id"));
             
            books.add(ba);
             
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookIdByAuthorId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookAuthorID() method: " + e.getMessage());
            }
        }
        return books;
    
    }

    @Override
    public ArrayList displayGenreByName(int genreId) {
        ArrayList<Genre> genre = new ArrayList();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try
        {
        // Get a connection to the database
            conn = getConnection();
            
            // Set up the SQL and compile it for the database
            String query = "Select * from genre where genre_id = ? AND status = 1";
            ps = conn.prepareStatement(query);
            
            ps.setInt(1,genreId);
            
            //Run query and get results
            rs = ps.executeQuery();    
            
            while(rs.next())
            {
              
              
            Genre g = new Genre(rs.getInt("genre_id"),rs.getString("genre_name"));           
                         
              genre.add(g);
            }
        }
        catch(SQLException e)
        {
            System.out.println("An exception occurred in the displayBooksByName(): " + e.getMessage());
        }
        finally
        {
            // Close resultset
            try{
                if(rs != null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the ResultSet of the displayBooksByName(): " + e.getMessage());
            }
            // Close prepared statement
            try{
                if(ps != null)
                {
                    ps.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println("An exception occurred when closing the PreparedStatement of the displayBooksByName(): " + e.getMessage());
            }
            // Close connection
            freeConnection(conn);
        }
        
        // Return results
        return genre;
    }

  
    }
        
        
        
    

    

   



