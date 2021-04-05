/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.SecurityAnswers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Osama Kheireddine
 */
public class SecurityAnswersDao extends Dao implements SecurityAnswersDaoInterface {

    public static void main(String[] args) {
        SecurityAnswers u = new SecurityAnswers();
        SecurityAnswersDao uDao = new SecurityAnswersDao("login");

        u.setUserId(1);
        u.setQuestionId(2);
        u.setAnswer("osama@someemail.com");

        int rows = uDao.addAnswer(u.getQuestionId(), u.getUserId(), u.getAnswer());

        System.out.println(rows);
    }

    public SecurityAnswersDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method is required to take the user's selected password, userId and
     * their answer (hashed before) and store it in the securityanswers table in
     * the database
     *
     * @param questionId
     * @param userId
     * @param answer
     * @return The number of rows affected (1 at a time)
     */
    @Override
    public int addAnswer(int questionId, int userId, String answer) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "INSERT INTO securityanswers (userId, question, answer) VALUES (?,?,?)";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, questionId);
            ps.setString(3, answer);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occured in the addAnswer() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addAnswer() method");
                e.getMessage();
            }
        }
        return rowsAffected;
    }
}
