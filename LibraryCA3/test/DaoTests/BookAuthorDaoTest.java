/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTests;

import business.Author;
import business.Book;
import business.BookAuthor;
import daos.BookAuthorDao;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class BookAuthorDaoTest {

    private static BookAuthorDao bookauthor;

    public BookAuthorDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        bookauthor = new BookAuthorDao("test_library");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * TEST PASSED Test of addBookAuthor method, of class BookAuthorDao. Book
     * and Author IDs are retrieved from their Daos
     */
    @Test
    public void testAddBookAuthor() {
        System.out.println("addBookAuthor where both ids exist");
        int bookId = 2;
        int authorId = 5;
        boolean expResult = true;
        boolean result = bookauthor.addBookAuthor(bookId, authorId);
        assertEquals(expResult, result);

    }

    /**
     *TEST PASSED
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
    @Test
    public void testDeleteBookAuthor() {
        System.out.println("disableBookAuthor bookauthor does not exist");
        int bookId = 2;
        int authorId = 5;
        boolean expResult = true;
        boolean result = bookauthor.deleteBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }

    /**
     *TEST PASSED
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
    @Test
    public void testDeleteBookAuthor2() {
        System.out.println("deleteBookAuthor bookauthor does not exist");
        int bookId = 12;
        int authorId = 15;
        boolean expResult = false;
        boolean result = bookauthor.deleteBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }

    /**
     *TEST PASSED
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
    @Test
    public void testDisableBookAuthor2() {
        System.out.println("disableBookAuthor bookauthor does not exist");
        int bookId = 12;
        int authorId = 13;
        boolean expResult = false;
        boolean result = bookauthor.disableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }

    /**
     *TEST PASSED
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
    @Test
    public void testDisableBookAuthor3() {
        System.out.println("disableBookAuthor bookauthor does not exist");
        int bookId = 2;
        int authorId = 3;
        boolean expResult = true;
        boolean result = bookauthor.disableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }

    /**
     * TEST PASSED
     * TEST PASSED Test of disableBookAuthor method, of class BookAuthorDao.
     */
    @Test
    public void testEnableBookAuthor() {
        System.out.println("enableBookAuthor");
        int bookId = 2;
        int authorId = 3;
        boolean expResult = true;
        boolean result = bookauthor.enableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }

    /**
     *TEST PASSED
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
       @Test
    public void testDisableBookAuthor() {
        System.out.println("disableBookAuthor");
        int bookId = 2;
        int authorId = 5;
        boolean expResult = true;
        boolean result = bookauthor.disableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
    }


    /**
     * Test of displayAllBooksAndAuthors method, of class BookAuthorDao. PASSED
     */
    @Test
    public void testDisplayAllBooksAndAuthors() {
        System.out.println("displayAllBooksAndAuthors");
        int noOfBooksAndAuthors = 6;
        ArrayList<BookAuthor> result = bookauthor.displayAllBooksAndAuthors();
        assertEquals(noOfBooksAndAuthors, result.size());

    }

}
