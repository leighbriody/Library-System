/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTests;

import daos.SecurityAnswersDao;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Osama Kheireddine
 */
public class SecurityAnswersDaoTest {
    private static SecurityAnswersDao sAnsDao;
    
    public SecurityAnswersDaoTest(){
        
    }
    
    // Put in code to, for example, create object that will be tested (instead of
    // recreating it over and over)
    @BeforeClass
    public static void setUpClass() {
        sAnsDao = new SecurityAnswersDao("librarydb_test_2");
    }
    
    /**
     * Testing for adding an answer to the db for a given userId, SecurityQuestionId and answer.
     */
    @Test
    public void testAddAnswer(){
        int questionId = 2;
        int userId = 44;
        String answer = "testAnswer";
        int expResult = 1;
        int result = sAnsDao.addAnswer(questionId, userId, answer);
        assertEquals(expResult, result);
    }
}
