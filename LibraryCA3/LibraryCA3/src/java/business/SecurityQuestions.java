/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Osama Kheireddine
 */
public class SecurityQuestions {
    private int id;
    private String question;
    
    /**
     * A constructor for the securityQuestions
     * Takes no parameters 
     */
    public SecurityQuestions(){
        
    }
    
    
    /**
     *Parameterized constructor, can be used to set a question
     * @param id
     * @param question
     */
    public SecurityQuestions(int id, String question) {    
        this.id = id;
        this.question = question;
    }

    /**
     * Used to retrieve the security question
     * @return The security question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets a security question
     * @param question 
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }
    
}
