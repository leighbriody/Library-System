/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Author;
import business.BookAuthor;
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
        bookauthor = new BookAuthorDao("librarydb");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of displayAllBooksAndAuthors method, of class BookAuthorDao.
     */
   // @Test
    public void testDisplayAllBooksAndAuthors() {
        System.out.println("displayAllBooksAndAuthors");
        BookAuthorDao instance = null;
        ArrayList<BookAuthor> expResult = null;
        ArrayList<BookAuthor> result = instance.displayAllBooksAndAuthors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBookAuthor method, of class BookAuthorDao.
     */
   // @Test
    public void testAddBookAuthor() {
        System.out.println("addBookAuthor");
        int bookId = 0;
        int authorId = 0;
        BookAuthorDao instance = null;
        boolean expResult = false;
        boolean result = instance.addBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disableBookAuthor method, of class BookAuthorDao.
     */
   // @Test
    public void testDisableBookAuthor() {
        System.out.println("disableBookAuthor");
        int bookId = 0;
        int authorId = 0;
        BookAuthorDao instance = null;
        boolean expResult = false;
        boolean result = instance.disableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableBookAuthor method, of class BookAuthorDao.
     */
  //  @Test
    public void testEnableBookAuthor() {
        System.out.println("enableBookAuthor");
        int bookId = 0;
        int authorId = 0;
        BookAuthorDao instance = null;
        boolean expResult = false;
        boolean result = instance.enableBookAuthor(bookId, authorId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

/*
     * Test of displayAuthorsByName method, of class BookAuthorDao.
     */
    @Test
    public void testDisplayAuthorsByName() {
        System.out.println("displayAuthorsByName");
        int AuthorId = 2;
        int numOfBookAuthors = 1;
        ArrayList<Author> result = bookauthor.displayAuthorsByName(AuthorId);
        assertEquals(numOfBookAuthors, result.size());
        
    }
    
}
