/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author Leigh Briody
 */
public interface CardsDaoInterface {

    /**
     * This method will insert the payment details provided into the database.
     * @param cardNumber
     * @param exDate
     * @param cvv
     * @return 
     */
    public int insertPaymentDetails(String cardNumber, String exDate, String cvv);

    /**
     * This method returns the most recent id in the cards table
     * @return 
     */
    public int getCardsRecentId();
}
