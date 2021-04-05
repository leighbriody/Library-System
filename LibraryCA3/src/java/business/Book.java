/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Leigh Briody
 */
public class Book {

    //Create all the fields our database table has
    private int bookId;
    protected static int nextId = 50;
    private String bookName;
    private int genreId;
    private String description;
    private int quantity;
    private double overdueFeePerDay;

    //Create a non param construcotor
    public Book() {

    }

    //Paramater constructor
    public Book(String bookName, int genreId, String description, int quantity) {

        this.bookId = nextId;
        nextId++;
        this.bookName = bookName;
        this.genreId = genreId;
        this.description = description;
        this.quantity = quantity;

    }

    public Book(int bookId, String bookName, int genreId, String description, int quantity, double overdueFeePerDay) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.genreId = genreId;
        this.description = description;
        this.quantity = quantity;
        this.overdueFeePerDay = overdueFeePerDay;

    }

    //Getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getOverdueFeePerDay() {
        return overdueFeePerDay;
    }

    public void setOverdueFeePerDay(Double overdueFeePerDay) {
        this.overdueFeePerDay = overdueFeePerDay;
    }

    //Hash code and equals
    //Two books are equal if they have the same book id 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.bookId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    //To string
    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", bookName=" + bookName + ", genreId=" + genreId + ", description=" + description + ", quantity=" + quantity + ", overdueFeePerDay=" + overdueFeePerDay + '}';
    }

    //Need to add formats
}
