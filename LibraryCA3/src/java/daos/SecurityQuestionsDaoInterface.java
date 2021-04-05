/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.SecurityQuestions;
import java.util.List;

/**
 *
 * @author Osama Kheireddine
 */
public interface SecurityQuestionsDaoInterface {
    //get all questions
    public List<SecurityQuestions> getAllQuestions();
    
    //change the questions
    public boolean changeExisitingQuestion();
    
    //add new question
    public boolean addNewQuestion();
}
