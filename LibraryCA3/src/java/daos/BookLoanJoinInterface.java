/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Book;
import java.util.ArrayList;

/**
 *
 * @author Leigh Briody
 */
public interface BookLoanJoinInterface {
    
    
    //This wil check if a user currently has book on loan
    public boolean doesUserHaveBookOnLoan(int bookId , int userId);
    
    //This wil check if a user currently has book on loan
    public boolean returnBookOnLoan(int bookId , int userId);
    
       public boolean borrowBook(int bookId , int userId);
}
