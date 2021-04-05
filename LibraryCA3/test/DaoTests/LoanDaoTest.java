package DaoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import business.Loan;
import daos.LoanDao;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Osama Kheireddine
 */
public class LoanDaoTest {

    private static LoanDao lDao;

    public LoanDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        lDao = new LoanDao("librarydb_test_2");
    }

    /**
     * To test the result of how many active loans a select user has Will verify
     * by using the size of the list to the exp result Success
     */
    @Test
    public void testAllLoansActive() {
        //user 38 will be used
        int userId = 38;
        //get the list of active loans
        ArrayList<Loan> loanList = lDao.allLoansActive(userId);
        int expResult = 3;
        int result = loanList.size();
        assertEquals(expResult, result);
    }

    /**
     * To test the result of how many loans a select user has made (inc. all)
     * Will verify using the size of the list to the exp result Success
     */
    @Test
    public void testAllLoansMade() {
        //user 38 will be used
        int userId = 38;
        //get the list of active loans
        ArrayList<Loan> loanList = lDao.allLoansMade(userId);
        int expResult = 5;
        int result = loanList.size();
        assertEquals(expResult, result);
    }

//    /**
//     * Will be used to test if a loan can be created
//     */
//    @Test
//    public void testCreateLoan() {
//        //user 38 will be used
//        int userId = 38;
//        //book id = 12
//        int bookId = 12;
//        String date_taken = "2021-01-12";
//        String due_date = "2021-01-18";
//        boolean expResult = true;
//        boolean result = lDao.createLoan(bookId, userId, date_taken, due_date);
//        assertEquals(expResult, result);
//    }
    /**
     * Will verify if a user has a book on loan based off supplied user_id and
     * book_id
     */
    @Test
    public void testIsBookOnLoan() {
        //user 38 will be used
        int userId = 38;
        //book id = 12
        int bookId = 12;
        boolean expResult = true;
        boolean result = lDao.isBookOnLoan(bookId, userId);
        assertEquals(expResult, result);
    }
    
//    /**
//     * To test if a book can be returned successfully 
//     */
//    @Test
//    public void testReturnBook() {
//        //user 38 will be used
//        int userId = 38;
//        //book id = 12
//        int bookId = 12;
//        //DATE RETURNED
//        String date_ret = "2021-01-17";
//        boolean expResult = true;
//        boolean result = lDao.returnBook(date_ret, bookId, userId);
//        assertEquals(expResult, result);
//    }
}
