/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Author;
import business.Book;
import business.BookAuthor;
import business.Genre;
import java.util.ArrayList;

/**
 *
 * @author Brianan Johnson
 */
public interface BookAuthorDaoInterface {
    
  
public boolean addBookAuthor( int bookId, int authorId);

 
public boolean disableBookAuthor(int bookId, int authorId);

public boolean enableBookAuthor(int bookId, int authorId);

public boolean deleteBookAuthor(int bookId, int authorId);

public ArrayList<BookAuthor> displayAllBooksAndAuthors(); //*TESTED

public ArrayList<Author> displayAuthorsByName(int AuthorId);

public ArrayList<Book> displayBooksByName(int BookId);

public ArrayList displayGenreByName(int genreId);

public ArrayList<BookAuthor> getAllBookAuthors();

public ArrayList<BookAuthor> getBookIdByAuthorId (int authorId);



}


