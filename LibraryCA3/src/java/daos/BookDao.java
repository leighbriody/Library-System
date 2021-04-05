/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Leigh Briody, Brianan Johnson, Osama Kheireddine
 */
public class BookDao extends Dao implements BookDaoInterface {

    public BookDao(String databaseName) {
        super(databaseName);
    }

    /**
     *
     * @return Returns all book details in a an array list format
     */
    @Override
    /**
     * TEST PASSED This method returns an array list of all books in the
     * database
     */
    public ArrayList<Book> viewAllBookDetails() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from books WHERE status = 1";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                int genreId = rs.getInt("genre");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double overdueFeePerDay = rs.getDouble("overdue_fee_per_day");

                //create the object
                Book book = new Book(bookId, bookName, genreId, description, quantity, overdueFeePerDay);
                //Add it to the array list.
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the viewAllBookDetails() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllProducts() method: " + e.getMessage());
            }
        }

        return books;
    }

    /**
     *
     * @param bookId
     * @return Returns an arrayList of books based off the id passed
     */
    @Override
    /*
     TEST PASSED
    */
    public ArrayList<Book> viewAllBooksById(int bookId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from books WHERE book_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Get all the data fields
                int book_Id = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                int genreId = rs.getInt("genre");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double overdueFeePerDay = rs.getDouble("overdue_fee_per_day");

                //create the object
                Book book = new Book(book_Id, bookName, genreId, description, quantity, overdueFeePerDay);
                //Add it to the array list.
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the viewAllBookDetails() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllProducts() method: " + e.getMessage());
            }
        }

        return books;
    }

    /**
     *
     * @param bookId
     * @return Returns a book's quantity based off the id passed as a parameter
     */
    @Override
    /**
     * TEST PASSED This method accepts a book id as a param ant returns the
     * quantity of that book
     */
    public int getBookQuantity(int bookId) {
        //This method will return the quanity of a book given the book id
        //IF THE BOOK ID PROVIDED DOES NOT EXISTS WE WILL RETURN -1
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book b = null;
        int quantity = -1;

        try {
            con = getConnection();

            String query = "Select quantity from books where book_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                //If rs.next this means the book id worked so we will return the quantity
                quantity = rs.getInt("quantity");
            } else {
                System.out.println("Book doesnt exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookQuantity() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getProductByCode() method: " + e.getMessage());
            }
        }
        return quantity;
    }

    /**
     *
     * @param bookName
     * @return Returns the book id based off the name passed through
     */
    /*
    TEST PASSED
     */
    @Override
    public int getBookIdByName(String bookName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int bookId = 0;

        try {
            con = getConnection();

            String query = "Select book_id from books where book_name = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                //If rs.next this means the book id worked so we will return the quantity
                bookId = rs.getInt("book_id");
            } else {
                System.out.println("Book doesnt exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookIdByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookIdByName() method: " + e.getMessage());
            }
        }
        return bookId;
    }

    /**
     * This method accepts a Book object and adds that book to the database.
     *
     * @returns Returns a boolean (true/false) and is appropriate to if the book
     * has been added or not
     */
    @Override
    public boolean addBook(Book newBook) {
        {
            Connection con = null;
            PreparedStatement ps = null;
            int rowsAffected = 0;
            boolean insertedSuccess = false;

            try {
                con = getConnection();

                String query = "Insert into books (book_name,  genre, description, quantity) values (? , ? , ? , ? )";

                ps = con.prepareStatement(query);
                ps.setString(1, newBook.getBookName());
                ps.setInt(2, newBook.getGenreId());
                ps.setString(3, newBook.getDescription());
                ps.setInt(4, newBook.getQuantity());

                rowsAffected = ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Exception occured in the addBook() method: " + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in the finally section of the addBook() method");
                    e.getMessage();
                }
            }

            if (rowsAffected > 0) {
                insertedSuccess = true;
            }

            return insertedSuccess;
        }
    }

    /**
     * This method accepts a bookId and quantity as parameter. It will then
     * increase that books quantity by the parameter passed
     *
     * @param bookId
     * @param qty
     * @return A boolean (true/false) based off of a success or failure to
     * increase quantity
     */
    @Override
    /*
     TEST PASSED
     */
    public boolean increaseBookQty(int bookId, int qty) {
        //update books set quantity = quantity + 4 where book_id = 49;
        {
            Connection con = null;
            PreparedStatement ps = null;
            int rowsAffected = 0;
            boolean updateSuccess = false;

            try {
                con = getConnection();

                String query = "UPDATE books SET quantity = quantity + ?  WHERE book_id = ? AND status = 1";

                ps = con.prepareStatement(query);

                ps.setInt(1, qty);
                ps.setInt(2, bookId);

                rowsAffected = ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Exception occured in the increaseBookQty() method: " + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in the finally section of the updateProductName() method");
                    e.getMessage();
                }
            }
            if (rowsAffected > 0) {
                updateSuccess = true;
            }
            return updateSuccess;
        }
    }

    /**
     * This method accepts a bookId and quantity as parameter. It will then
     * decrease that books quantity by the parameter passed
     *
     * @param bookId
     * @param qty
     * @return A boolean (true/false) based off of a success or failure to
     * decrease quantity
     */
    @Override
    /*
      TEST PASSED 
     */
    public boolean decreaseBookQty(int bookId, int qty) {
        {
            Connection con = null;
            PreparedStatement ps = null;
            int rowsAffected = 0;
            boolean updateSuccess = false;

            try {
                con = getConnection();

                String query = "UPDATE books SET quantity = (quantity - ? )  WHERE book_id = ?";

                ps = con.prepareStatement(query);

                ps.setInt(1, qty);
                ps.setInt(2, bookId);

                rowsAffected = ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Exception occured in the increaseBookQty() method: " + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in the finally section of the updateProductName() method");
                    e.getMessage();
                }
            }
            if (rowsAffected > 0) {
                updateSuccess = true;
            }
            return updateSuccess;
        }
    }

    /**
     * Will be used to disable a book given the id passed as a parameter
     *
     * @param bookName
     * @return A boolean based off of success or failure to disable a book
     */
    @Override
    public boolean disableBook(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean bookDisabled = false;

        try {
            con = getConnection();

            String query = "UPDATE  books SET status = 0 WHERE book_name = ? AND status = 1 ";
            ps = con.prepareStatement(query);
            ps.setString(1, bookName);

            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                bookDisabled = true;
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

    /**
     * Will be used to enable a book given the id passed as a parameter
     *
     * @param bookName
     * @return A boolean based off of success or failure to enable a book
     */
    @Override
    public boolean enableBook(String bookName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean bookEnabled = false;

        try {
            con = getConnection();

            String query = "UPDATE  books SET status = 1 WHERE book_name = ? AND status = 0 ";
            ps = con.prepareStatement(query);
            ps.setString(1, bookName);

            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                bookEnabled = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the enableBook() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the enableBook() method: " + e.getMessage());
            }
        }

        return bookEnabled;
    }

    /**
     * Search for books or book. 
     * This method takes a book name as parameter and
     * returns a list of books or a book thats name contains that parameter.
     *
     * @param bookName
     * @return ArrayList<Book> type Book list 
     */
    @Override
    public ArrayList<Book> searchForBooks(String bookName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList<>();

        try {
            conn = getConnection();

            String query = "SELECT * FROM books where book_name LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + bookName + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                Book b = new Book();

                b.setBookId(rs.getInt("book_id"));
                b.setBookName(rs.getString("book_name"));
                b.setGenreId(rs.getInt("genre"));
                b.setDescription(rs.getString("description"));
                b.setQuantity(rs.getInt("quantity"));
                b.setOverdueFeePerDay(rs.getDouble("overdue_fee_per_day"));

                books.add(b);
            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific book in the searchForBooks() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            // Close resultset
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("An exception occurred when closing the ResultSet of the searchForBooks(): " + e.getMessage());
            }
            // Close prepared statement
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("An exception occurred when closing the PreparedStatement of the searchForBooks(): " + e.getMessage());
            }
            // Close connection
            freeConnection(conn);
        }

        // Return results
        return books;
    }
    
    /**
     * 
     * @param bookId
     * @return Returns a Book based off of id passed through as a parameter
     */
    @Override
    public Book getBookById(int bookId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book b = null;

        try {
            con = getConnection();

            String query = "Select * from books where book_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                //If rs.next this means the book id worked so we will return the quantity
                b = new Book();
                b.setBookId(rs.getInt("book_id"));
                b.setBookName(rs.getString("book_name"));
                b.setDescription(rs.getString("description"));
                b.setQuantity(rs.getInt("quantity"));

            } else {
                System.out.println("Book doesnt exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookById() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookById() method: " + e.getMessage());
            }
        }
        return b;
    }
    
    /**
     * 
     * @param name
     * @return Returns a Book based off the name passed through as a String parameter
     */
    @Override
    public Book getBookByName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String bookName = null;
        Book b = new Book();

        try {
            con = getConnection();

            String query = "Select * from books where book_name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);

            rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("book_id");
                String bookname = rs.getString("book_name");
                int genre = rs.getInt("genre");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double overdue = rs.getDouble("overdue_fee_per_day");

                b = new Book(id, bookname, genre, description, quantity, overdue);

            } else {
                System.out.println("Book doesnt exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getBookByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookByName() method: " + e.getMessage());
            }
        }
        return b;
    }

}
