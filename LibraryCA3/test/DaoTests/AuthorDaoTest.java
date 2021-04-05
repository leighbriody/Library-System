
import business.Author;
import daos.AuthorDao;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brianan Johnson
 */
public class AuthorDaoTest {

    private static AuthorDao author;

    public AuthorDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        author = new AuthorDao("librarydatabasev2");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of displayAllAuthors method, of class AuthorDao. you may need to
     * change the noOfAuthors as the addAuthor() will create a new instance and
     * throw this figure out
     */
//    @Test
//    public void testDisplayAllAuthors() {
//        System.out.println("displayAllAuthors");
//        int noOfAuthors = 62;
//        ArrayList<Author> result = author.displayAllAuthors();
//        assertEquals(noOfAuthors, result.size());
//        
//    }
    /**
     * Test of checkIfAuthorExists method, of class AuthorDao. Tested and
     * passed, also test if author !exist
     */
//    @Test
    public void testCheckIfAuthorExists() {
        System.out.println("checkIfAuthorExists");
        String authorFirstName = "Deirdre";
        String authorLastName = "Blair";
        boolean expResult = true;
        boolean result = author.checkIfAuthorExists(authorFirstName, authorLastName);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of addAuthor method, of class AuthorDao.
//     */

    @Test
    public void testAddAuthor() {
        System.out.println("addAuthor");
        Author newAuthor = new Author(451, "Testy", "testface");
        boolean expResult = true;
        boolean result = author.addAuthor(newAuthor);
        assertEquals(expResult, result);

    }

}
