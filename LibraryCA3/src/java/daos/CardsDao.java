/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.CardsDaoInterface;
import daos.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leigh Briody
 */
public class CardsDao extends Dao implements CardsDaoInterface {

    public static void main(String[] args) {

    }

    /**
     * This constructor will super the database name to the super classs dao making a connection to the database.
     * @param databaseName 
     */
    public CardsDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method will insert the payment details provided into the database.
     * @param cardNumber
     * @param exDate
     * @param cvv
     * @return 
     */
    @Override
    public int insertPaymentDetails(String cardNumber, String exDate, String cvv) {

        Connection con = null;
        PreparedStatement ps = null;

        int rowsAffected = 0;
        try {
            con = getConnection();

            String query = "INSERT INTO cards (id , cardNum, expiry, cvv) VALUES (null , ? , ?, ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, cardNumber);
            ps.setString(2, exDate);
            ps.setString(3, cvv);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occured in the insertPaymentDetails() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the signUpUser() method");
                e.getMessage();
            }
        }

        return rowsAffected;
    }

    /**
     * This method will get the most recent id in the cards table
     *
     * @return
     */
    @Override
    public int getCardsRecentId() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cardId = -1;

        try {
            con = getConnection();

            String query = "SELECT max(id) from cards";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                cardId = rs.getInt("max(id)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getMaxCardId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getCustomerById() method: " + e.getMessage());
            }
        }
        return cardId;
    }
}
