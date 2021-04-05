/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Loan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public class LoanDao extends Dao implements LoanDaoInterface {
    
    public static void main(String[] args) {
       
        LoanDao l = new LoanDao("librarydb");
        
        l.returnBook("2021-03-01", 20 , 44, 0.00);

    }

    public LoanDao(String databaseName) {
        super(databaseName);
    }
    
     /**
     * *
     * This class will access the db and return all records where the userId
     * matches & all loans are active
     *
     * @param userId
     * @return An ArrayList of books still on loan
     */

    /**
     * This method will return a loan given the loan id
     * active
     * @param loanId
     */
    @Override
    public Loan getLoan(int userId , int book_id) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create an arraylist that will be used to hold the loans
        Loan loan = null;

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans where user_id= ? and book_id = ?" ;
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            ps.setInt(2, book_id);
            
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            if (rs.next()) {
                //create an object of loans to add to the list
                Loan l = new Loan(rs.getInt("loan_id"), rs.getInt("book_id"), rs.getInt("user_id"), rs.getString("date_taken"), rs.getString("due_date"), rs.getString("date_returned") , rs.getDouble("overdue_fee"));
                //add the loan to the list
                loan = l;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in getLoan() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getLoan() method: " + e.getMessage());
            }
        }

        return loan;
    }
    
    
     /**
     * This method will return the total overdue fee a user owes.
     * @param userId
     * @return 
     */
    @Override
    public double getUsersTotalOverdueFee(int userId ) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create an arraylist that will be used to hold the loans
       double total = 0;
        
        

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT sum(overdue_fee) from loans where user_id = ?" ;
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            
            
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            if (rs.next()) {
                
                total = rs.getDouble("sum(overdue_fee)");
                
                
                
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in getUsersTotalOverdueFee() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getUsersTotalOverdueFee() method: " + e.getMessage());
            }
        }

        return total;
    }
    
    
    
    /**
     * This method will return all the loans with an overdue fee that is not null and not 0
     * @param userId
     * @return 
     */
    @Override
    public ArrayList<Loan> getOverdueFeeLoans(int userId ) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create an arraylist that will be used to hold the loans
        Loan loan = null;
        
        ArrayList<Loan> overdueFeeLoans = new ArrayList<Loan>();

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans where user_id = ? and overdue_fee is not null and overdue_fee != 0;" ;
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            
            
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            while (rs.next()) {
                //create an object of loans to add to the list
                Loan l = new Loan(rs.getInt("loan_id"), rs.getInt("book_id"), rs.getInt("user_id"), rs.getString("date_taken"), rs.getString("due_date"), rs.getString("date_returned") , rs.getDouble("overdue_fee"));
                //add the loan to the list
                loan = l;
                
                overdueFeeLoans.add(loan);
                
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in getLoan() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getLoan() method: " + e.getMessage());
            }
        }

        return overdueFeeLoans;
    }

    

    /**
     * *
     * This class will access the db and return all records where the userId
     * matches & all loans are active
     *
     * @param userId
     * @return An ArrayList of books still on loan
     */
    @Override
    /**
     * This method will allow the user to see all the loans they currently have
     * active
     */
    public ArrayList<Loan> allLoansActive(int userId) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create an arraylist that will be used to hold the loans
        ArrayList<Loan> output = new ArrayList();

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans WHERE user_id = ? AND date_returned is null";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            while (rs.next()) {
                //create an object of loans to add to the list
                Loan l = new Loan(rs.getInt("loan_id"), rs.getInt("book_id"), rs.getInt("user_id"), rs.getString("date_taken"), rs.getString("due_date"), rs.getString("date_returned") , rs.getDouble("overdue_fee"));
                //add the loan to the list
                output.add(l);
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in allLoansActive() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the allLoansActive() method: " + e.getMessage());
            }
        }

        return output;
    }

    /**
     * This method will allow the user to see all the loans they have made since
     * joining the library
     *
     * @param userId
     * @return All loans the user has made since joining the library
     */
    @Override

    public ArrayList<Loan> allLoansMade(int userId) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;
        //create an arraylist that will be used to hold the loans
        ArrayList<Loan> output = new ArrayList();

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans WHERE user_id = ?";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, userId);
            //execute the query
            rs = ps.executeQuery();

            //while loop to add the results to the arraylist
            while (rs.next()) {
                //create an object of loans to add to the list
                Loan l = new Loan(rs.getInt("loan_id"), rs.getInt("book_id"), rs.getInt("user_id"), rs.getString("date_taken"), rs.getString("due_date"), rs.getString("date_returned") ,  rs.getDouble("overdue_fee"));
                //add the loan to the list
                output.add(l);
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in allLoansMade() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the allLoansMade() method: " + e.getMessage());
            }
        }

        return output;
    }

    /**
     * This method creates a Loan for a User given the appropriate parameters
     * passed through
     *
     * @param book_id
     * @param user_id
     * @param date_taken
     * @param due_date
     * @return A boolean based off of the success/failure to create a Loan
     */
    @Override
    public boolean createLoan(int book_id, int user_id, String date_taken, String due_date) {
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a rowsAffected
        int rowsAffected = 0;
        //create a flag for a successful creation
        boolean successfulLoan = false;

        //we now need to try to execute a query & use the parameter that is passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "INSERT INTO loans (book_id, user_id, date_taken, due_date) VALUES (?,?,?,?)";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, book_id);
            ps.setInt(2, user_id);
            ps.setString(3, date_taken);
            ps.setString(4, due_date);
            //execute the query
            rowsAffected = ps.executeUpdate();

            //add the loan to the table
            if (rowsAffected > 0) {
                successfulLoan = true;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in allLoansMade() method: " + e.getMessage());
        } //finally block to close the connection
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the allLoansMade() method: " + e.getMessage());
            }
        }

        return successfulLoan;
    }

    /**
     *
     * @param book_id
     * @param user_id
     * @return A boolean based off the success/failure to find a loaned Book for
     * a given User
     */
    @Override
    public boolean isBookOnLoan(int book_id, int user_id) {
        //a flag to say if the book is on loan or not
        boolean isBookOnLoan = false;
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //Create a result set object
        ResultSet rs = null;

        //we now need to try to execute a query & use the parameters passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "SELECT * FROM loans WHERE book_id = ? AND user_id = ? AND date_returned IS NULL";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setInt(1, book_id);
            ps.setInt(2, user_id);
            //execute the query
            rs = ps.executeQuery();

            //if the book is on loan...
            if (rs.next()) {
                isBookOnLoan = true;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in allLoansMade() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the allLoansMade() method: " + e.getMessage());
            }
        }

        return isBookOnLoan;
    }
    
    /**
     * 
     * @param date_returned
     * @param book_id
     * @param user_id
     * @return A boolean based off of the success/failure to return a Book for a given User
     */
    @Override
    public boolean returnBook(String date_returned, int book_id, int user_id , double overdue) {
//a flag to say if the book is on loan or not
        boolean isBookReturned = false;
        //create a connection object
        Connection con = null;
        //Create a prepared statement object
        PreparedStatement ps = null;
        //rowsaffected
        int rowsAffected = 0;

        //we now need to try to execute a query & use the parameters passed through this method
        try {
            //getConnection()
            con = getConnection();
            //query to ask db
            String query = "UPDATE loans SET date_returned = ? ,  overdue_fee = ?  WHERE user_id = ? AND book_id = ?";
            //prepare the statement
            ps = con.prepareStatement(query);
            //bind the parameter to the value were looking for
            ps.setString(1, date_returned);
            ps.setDouble(2, overdue);
            ps.setInt(3, user_id);
            ps.setInt(4, book_id);
            //execute the query
            rowsAffected = ps.executeUpdate();

            //if the book is on loan...
            if (rowsAffected > 0) {
                isBookReturned = true;
            }
            //catch statement in case of error
        } catch (SQLException e) {
            System.out.println("Exception occured in returnBook() method: " + e.getMessage());
        } //finally block to close the connection
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the allLoansMade() method: " + e.getMessage());
            }
        }

        return isBookReturned;
    }

}
