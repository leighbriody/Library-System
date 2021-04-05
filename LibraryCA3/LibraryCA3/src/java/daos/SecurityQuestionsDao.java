/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.SecurityQuestions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Osama Kheireddine
 */
public class SecurityQuestionsDao extends Dao implements SecurityQuestionsDaoInterface {
    public SecurityQuestionsDao(String databaseName) {
        super(databaseName);
    }
    
    /**
     * Sends a request to get all security questions.
     * This will be used to display them in a selection box for a user.
     * @return All security questions
     */
    @Override
    public List<SecurityQuestions> getAllQuestions() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SecurityQuestions> allQuestions = new ArrayList();
        
        try{
            con = getConnection();
            String query = "SELECT * FROM securityQuestions";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            //as there are more than 1 result we need a while loop
            while(rs.next()){
                //Get all the fields
                int questionId = rs.getInt("id");
                String question = rs.getString("question");
                
                //create an object to hold the security question
                SecurityQuestions sQuestion = new SecurityQuestions(questionId, question);
                //add the question to the list
                allQuestions.add(sQuestion);
            }
        } catch (SQLException e){
            System.out.println("Exception occured in the getAllQuestions() method: " + e.getMessage());
        } finally{
            //now close all connections
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (SQLException e){
                System.out.println("Exception occured in the finally section of the getAllQuestions() method: " + e.getMessage());
            }
        }
        
        return allQuestions;
    }
    
    /**
     * This method will allow an admin to change an existing question.
     * @return A boolean (true/false) to indicate success or failure
     */
    @Override
    public boolean changeExisitingQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method will allow an admin to add a question.
     * @return A boolean (true/false) to indicate success or failure
     */
    @Override
    public boolean addNewQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
