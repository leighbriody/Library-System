/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author Osama Kheireddine
 */
public interface SecurityAnswersDaoInterface {
    //Only method we currently need
    public int addAnswer(int questionId, int userId, String answer);
}
