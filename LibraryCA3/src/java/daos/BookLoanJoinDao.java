/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Leigh Briody
 */
public class BookLoanJoinDao extends Dao implements BookLoanJoinInterface {

    public BookLoanJoinDao(String databaseName) {
        super(databaseName);
    }

    @Override
    /**
     * This method will check if a user already has a book on loan
     */
    public boolean doesUserHaveBookOnLoan(int bookId, int userId) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create a boolean that will alert if a result has been found
        boolean output = false;

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans WHERE user_id = ? AND book_id = ? AND date_returned is null";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            while (rs.next()) {
                //if the resultSet has a result, there will be a true flag
                output = true;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in doesUserHaveBookOnLoan() method: " + e.getMessage());
        } //finally block to close the connection
        finally {
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
                System.out.println("Exception occured in the finally section of the doesUserHaveBookOnLoan() method: " + e.getMessage());
            }
        }

        return output;
    }

    /**
     *This method will allow the user to return a book they have on loan
     * @param bookId
     * @param userId
     * @return
     */
    @Override
    
    public boolean returnBookOnLoan(int bookId, int userId) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create a boolean that will alert if a result has been found
        boolean output = false;
        //Timestamp object
        
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCurrentTime = format1.format(c.getTime());
        
        int rowsAffected = 0;

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            //update date_returned where the loan has this userId and bookId
            String query = "UPDATE loans SET date_returned = ? WHERE book_id = ? AND user_id = ?";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setString(1, formattedCurrentTime);
            ps.setInt(2, bookId);
            ps.setInt(3, userId);
            //execute the query
             rowsAffected = ps.executeUpdate(); 

            //while loop to add the results to the arraylist
             if(rowsAffected>0){
                //if the resultSet has a result, there will be a true flag
                output = true;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in returnBookOnLoan() method: " + e.getMessage());
        } //finally block to close the connection
        finally {
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
                System.out.println("Exception occured in the finally section of the returnBookOnLoan() method: " + e.getMessage());
            }
        }

        return output;
    }

    /**
     *This method will allow the user to borrow a book
     * @param bookId
     * @param userId
     * @return
     */
    @Override
    
    public boolean borrowBook(int bookId, int userId) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //a flag to say if there are enough books
        boolean enoughBooks = false;
        //create a boolean that will alert if a result has been found
        boolean output1 = false;
        boolean output2 = false;
        boolean output3 = false;
        
        
        //CALANDER OBJECT FOR DATE TAKEN
        int numberOfDays = 14;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCurrentTime = format1.format(c.getTime());
        
        
     
        c.add(Calendar.DAY_OF_WEEK, 14);
        
        String formattedTwoWeeks = format1.format(c.getTime());
        
       
        //Flag to see if rows affected
        int rowsAffected = 0;

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();

            //query to ask db
            //We need 2 queries, one to see if the db has that book in stock and one to update
            String query1 = "SELECT quantity FROM books WHERE book_id = ? AND quantity > 0";
            ps = con.prepareStatement(query1);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            //loop results and see if true
            while (rs.next()) {
                enoughBooks = true;
            }
            //only do this if there are enough books
            if (enoughBooks == true) {
                //create query to decrement books
                String queryBookTbl = "UPDATE books SET quantity = quantity -1 WHERE book_id = ?";
                ps = con.prepareStatement(queryBookTbl);
                ps.setInt(1, bookId);
                rowsAffected = ps.executeUpdate();
                //if there are any affected rows do this
                if (rowsAffected > 0) {
                    output1 = true;
                    //Create a loan
                    String query = "INSERT INTO loans (loan_id , book_id, user_id, date_taken, due_date) VALUES (null, ?,?,?,?)";
                    //prepare the statement
                    ps = con.prepareStatement(query);
                    //bind the parameter to the value were looking for
                    ps.setInt(1, bookId);
                    ps.setInt(2, userId);
                    ps.setString(3, formattedCurrentTime);
                    ps.setString(4, formattedTwoWeeks);
                    rowsAffected = ps.executeUpdate();
                    //check to see if any update happened
                    if (rowsAffected > 0) {
                        output2 = true;
                    }
                    //Create the final flag
                    if(output1 == true && output2 == true){
                        output3 = true;
                    }
                }
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in borrowBook() method: " + e.getMessage());
        } //finally block to close the connection
        finally {
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
                System.out.println("Exception occured in the finally section of the borrowBook() method: " + e.getMessage());
            }
        }

        return output3;
    }

}
