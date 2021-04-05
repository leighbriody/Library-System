/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Brianan Johnson
 */
public class BookAuthor {
  
    //create fields for table 
    private int bookauthor_id;
    private int book_id;
    private int author_id;
      
    //Constructors
    public BookAuthor(){
        
    }

       
    public BookAuthor( int book_id, int author_id) {
       
        this.book_id = book_id;
        this.author_id = author_id;
        
    }
    

    public int getBookauthor_id() {
        return bookauthor_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setBookauthor_id(int bookauthor_id) {
        this.bookauthor_id = bookauthor_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.bookauthor_id;
        hash = 17 * hash + this.book_id;
        hash = 17 * hash + this.author_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookAuthor other = (BookAuthor) obj;
        if (this.bookauthor_id != other.bookauthor_id) {
            return false;
        }
        if (this.book_id != other.book_id) {
            return false;
        }
        return this.author_id == other.author_id;
    }

    @Override
    public String toString() {
        return "BookAuthor{" + "bookauthor_id=" + bookauthor_id + ", book_id=" + book_id + ", author_id=" + author_id + '}';
    }

    
  
    
   
 
}
