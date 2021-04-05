/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leigh Briody
 */
public class Dao_1 {

    private String databaseName;

    public Dao_1(String databaseName) {
        this.databaseName = databaseName;
    }

    //This methof attempts to create a connection to database and return it
    public Connection getConnection() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find the driver class " + ex1.getMessage());
        } catch (SQLException ex2) {
            System.out.println("Failed to find the driver class " + ex2.getMessage());
        }
        return con;

    }
    
    
     //we then also need a method to free the connection 
    public void freeConnection(Connection con){
        
        try {
            if(con != null){
                con.close();
                con = null;
            }
        }catch(SQLException e){
            System.out.println("Failed to free connection " + e.getMessage());
            System.exit(1);
        }
    }

}
