package DaoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import business.Book;
import daos.BookDao;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Osama Kheireddine
 */
public class BookDaoTest {

    private static BookDao bDao;

    public BookDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        bDao = new BookDao("librarydb_test_2");
    }

    /**
     * To test the view all books function, should return an arraylist of 49
     * books this should be a successful test
     * Success
     */
    @Test
    public void testViewAllBookDetails() {
        //there are 49 books in our db
        int numOfBooks = 49;

        ArrayList<Book> bookList = bDao.viewAllBookDetails();
        assertEquals(numOfBooks, bookList.size());
    }

    /**
     * To test and see what books are returned based off the id passed This
     * should be a successful test
     * Success
     */
    @Test
    public void testViewAllBookDetailsById() {
        //there are 49 books in our db, we will select the first one for ease of testing
        int bookId = 1;
        ArrayList<Book> bookList = bDao.viewAllBooksById(bookId);
        Book expResult = new Book(1, "Faulkner: A Biography", 1, "William Faulkner (1897-1962) remains the pre-eminent literary chronicler of the American South and a giant of American arts and letters. Creatively obsessed with problems of race, identity, power, politics, and family dynamics, he wrote novels, stories, and lectures that continue to shape our understanding of the region", 10, 0);
        Book result = bookList.get(0);
//        System.out.println(result.toString());
        assertEquals(expResult, result);
    }

    /**
     * To see how many of a specific book are in the db Should be a successful
     * test
     * Success
     */
    @Test
    public void testGetBookQuantity() {
        int bookId = 1;
        //there should be a qty of 10
        int expResult = 10;
        int result = bDao.getBookQuantity(bookId);
//        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * To find a book id based off the name passed through test
     * Success
     */
    @Test
    public void testGetBookIdByName() {
        String bookName = "Faulkner: A Biography";
        //there should be a qty of 10
        int expResult = 1;
        int result = bDao.getBookIdByName(bookName);
//        System.out.println(result);
        assertEquals(expResult, result);
    }

//    /**
//     * To test adding a book to the db
//    Success
//     */
//    @Test
//    public void testAddBook() {
//        Book testBook = new Book("test book", 1, "this testing is awful", 10);
//        boolean expResult = true;
//        boolean result = bDao.addBook(testBook);
////        System.out.println(result);
//        assertEquals(expResult, result);
//    }
    /**
     * To test increasing a book's quantity based off passed params
     * Success
     */
    @Test
    public void testIncreaseBookQty() {
        int bookQty = 12;
        int bookId = 2;
        //there should be a qty of 12
        boolean expResult = true;
        boolean result = bDao.increaseBookQty(bookId, bookQty);
//        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * To test decreasing a book's quantity based off passed params
     * Success
     */
    @Test
    public void testDecreaseBookQty() {
        int bookQty = 2;
        int bookId = 2;
        //there should be a qty of 10
        boolean expResult = true;
        boolean result = bDao.increaseBookQty(bookId, bookQty);
//        System.out.println(result);
        assertEquals(expResult, result);
    }

//    /**
//     * To test disabling a book, field should be 0 now
//    Success
//     */
//    @Test
//    public void testDisableBook() {
//        String bookName = "Faulkner: A Biography";
//        //there should be a qty of 10
//        boolean expResult = true;
//        boolean result = bDao.disableBook(bookName);
////        System.out.println(result);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * To test enabling a book, field should be 1 now
//    Success
//     */
//    @Test
//    public void testEnableBook() {
//        String bookName = "Faulkner: A Biography";
//        //there should be a qty of 10
//        boolean expResult = true;
//        boolean result = bDao.disableBook(bookName);
////        System.out.println(result);
//        assertEquals(expResult, result);
//    }
    /**
     * To test searching for books with similar names supplied. The expected list size should be returned
     * Success
     */
    @Test
    public void testSearchForBooks() {
        String bookName = "Faulk";
        //there should be a qty of 10
        int expResult = 1;
        ArrayList<Book> result = bDao.searchForBooks(bookName);
//        System.out.println(result);
        assertEquals(expResult, result.size());
    }

    /**
     * To test getting a book from the id value supplied
     * Success
     */
    @Test
    public void testGetBookById() {
        int bookId = 1;
        //there should be a qty of 10
        Book expResult = new Book(1, "Faulkner: A Biography", 1, "William Faulkner (1897-1962) remains the pre-eminent literary chronicler of the American South and a giant of American arts and letters. Creatively obsessed with problems of race, identity, power, politics, and family dynamics, he wrote novels, stories, and lectures that continue to shape our understanding of the region", 10, 0);
        Book result = bDao.getBookById(bookId);
//        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * To test searching a book based off it's full name supplied
     * Success
     */
    @Test
    public void testGetBookByName() {
        String bookName = "Faulkner: A Biography";
        //there should be a qty of 10
        Book expResult = new Book(1, "Faulkner: A Biography", 1, "William Faulkner (1897-1962) remains the pre-eminent literary chronicler of the American South and a giant of American arts and letters. Creatively obsessed with problems of race, identity, power, politics, and family dynamics, he wrote novels, stories, and lectures that continue to shape our understanding of the region", 10, 0);
        Book result = bDao.getBookByName(bookName);
//        System.out.println(result.toString());
        assertEquals(expResult, result);
    }
}
