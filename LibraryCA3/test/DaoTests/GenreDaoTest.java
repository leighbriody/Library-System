package DaoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import business.Genre;
import daos.GenreDao;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class GenreDaoTest {
    
    private static GenreDao genre;
    
    public GenreDaoTest() {
    }
    
    
    public static void setUpClass() {
        genre = new GenreDao("librarydatabasev2");
    }
    

    public static void tearDownClass() {
    }

    @Test
    public void testShowAllGenres() {
        System.out.println("showAllGenres");
        int noOfGenres = 5;
        ArrayList<Genre> result = genre.showAllGenres();
        assertEquals(noOfGenres, result.size());
      
    }

    
    
    
    
    
    
    //THERE TESTS ARE COMMENTED OUT AS THE METHODS ARE NOT BEING USED
    
    
    
    
    
    /**
     * Test of checkIfGenreExists method, of class GenreDao.
     */
//    @Test
//    public void testCheckIfGenreExists() {
//        System.out.println("checkIfGenreExists");
//        String genre = "";
//        GenreDao instance = null;
//        boolean expResult = false;
//        boolean result = instance.checkIfGenreExists(genre);
//        assertEquals(expResult, result);
//        
//    }

    /**
     * Test of addGenre method, of class GenreDao.
     */
//    @Test
//    public void testAddGenre() {
//        System.out.println("addGenre");
//        Genre newGenre = null;
//        GenreDao instance = null;
//        boolean expResult = false;
//        boolean result = instance.addGenre(newGenre);
//        assertEquals(expResult, result);
//        
//    }
    
}
