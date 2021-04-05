/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author ME
 */
public class SecurityAnswers {
    private int id;
    private int userId;
    private int questionId;
    private String answer;

    public SecurityAnswers() {
        
    }
    
    /**
     * Constructor for creating an answer to a security question
     * @param id
     * @param userId
     * @param questionId
     * @param answer 
     */
    public SecurityAnswers(int id, int userId, int questionId, String answer) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }
    
    /**
     * Gets the id of the answer
     * @return Answer's id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the user's id
     * @return the users id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Gets the question's id
     * @return the questions id
     */
    public int getQuestionId() {
        return questionId;
    }
    
    /**
     * Gets the user's text answer
     * @return the users answer
     */
    public String getAnswer() {
        return answer;
    }
    
    /**
     * Sets the answers id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the user's id
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * Sets the question id
     * @param questionId 
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Sets the answer
     * @param answer 
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
}
