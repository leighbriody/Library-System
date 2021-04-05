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
public interface BookDaoInterface {
   
    //Leigh 
    
    //SOME OF THE METHODS WE WILL FOR THE BOOK DAO INTERFACE
    
    //increaseOrDecreaseBooks(ammount) - admins can increase or decrease the stock of books in the library
    public ArrayList<Book> viewAllBookDetails();   
    
    public int getBookQuantity(int bookId);
    
    //only an admin can add a book
    public boolean addBook(Book newBook);
    
    public ArrayList<Book> searchForBooks(String bookName);
    
    public boolean increaseBookQty(int bookId , int qty);
    
    public boolean decreaseBookQty(int bookId , int qty);
    
    public int getBookIdByName(String bookName);
    
    public Book getBookByName(String name);
    
    public Book getBookById(int bookId);
    
    public boolean disableBook(String bookName);
    
    public boolean enableBook(String bookName);
    
    public ArrayList<Book> viewAllBooksById(int bookId) ;
    
}
