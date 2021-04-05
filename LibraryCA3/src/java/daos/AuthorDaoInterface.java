/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Author;
import java.util.ArrayList;

/**
 *
 * @author Brianan Johnson
 */
public interface AuthorDaoInterface {
    
    
    public boolean checkIfAuthorExists(String author_first_name, String author_last_name);
    
    public boolean addAuthor(Author newAuthor);
    
    public ArrayList<Author> displayAllAuthors();
    
    public int getAuthorIdByName(String authorFirst, String authorLast);
    
    public boolean disableAuthor(String authorFirst, String authorLast);
    
     public boolean enableAuthor(String authorFirst, String authorLast);
     
    public ArrayList<Author> displayAuthorById(int author_id);
    
    public Author getAuthorById(int author_id);
}
