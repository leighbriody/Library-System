/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brianan Johnson
 */
public class AddressDao extends Dao implements AddressDaoInterface {

    public AddressDao(String databaseName) {
        super(databaseName);
    }

    @Override
    /**
     * This method will check if the address already exists in the dataase
     */

    public boolean checkIfAdddressExists(String line1, String line2, String county, String city, String country) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean ifAddressExists = false;

        try {
            con = getConnection();

            String query = "Select * from address where address_line_1 = ?"
                    + " AND address_line_2 = ?"
                    + " AND county = ?"
                    + " AND city = ?"
                    + " AND country =?";

            ps = con.prepareStatement(query);
            ps.setString(1, line1);
            ps.setString(2, line2);
            ps.setString(3, county);
            ps.setString(4, city);
            ps.setString(5, country);

            rs = ps.executeQuery();

            if (rs.next()) {
                //it means the address returned something therfore an address exists
                ifAddressExists = false;
            } else {
                ifAddressExists = true;

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the checkIfAdddressExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the checkIfAdddressExists() method: " + e.getMessage());
            }
        }

        return ifAddressExists;

    }

    @Override
    /**
     * This method takes an address object as a paramater and adds it to the
     * database.
     */
    public boolean addAddress(Address newAddress) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int rowsAffected = 0;

        boolean ifAddressAdded = false;

        try {
            con = getConnection();

            String line1 = newAddress.getAddressLine1();
            String line2 = newAddress.getAddressLine2();
            String county = newAddress.getCounty();
            String city = newAddress.getCity();
            String country = newAddress.getCountry();

            String query = "Insert into address values(null,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, line1);
            ps.setString(2, line2);
            ps.setString(3, county);
            ps.setString(4, city);
            ps.setString(5, country);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the addAddress() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the addAddress() method: " + e.getMessage());
            }
        }

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method will get the id of the last address that was created
     *
     * @return
     */
    public int getLastAddressId() {

        //This method will return the most recent adress id added
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int max = -1;

        try {
            con = getConnection();

            String query = "Select max(address_id) from address as max";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                max = rs.getInt("max(address_id)");
            } else {
                System.out.println("failed getLastAddressId");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getLastAddressId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getLastAddressId() method: " + e.getMessage());
            }
        }
        return max;
    }
}
