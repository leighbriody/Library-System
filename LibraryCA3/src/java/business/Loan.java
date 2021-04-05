/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


public class Loan {
    /*
     CREATE TABLE `loans` (
     `loan_id` int(7) NOT NULL,
     `book_id` int(11) DEFAULT NULL,
     `user_id` int(11) DEFAULT NULL,
     `date_taken` date DEFAULT NULL,
     `due_date` date DEFAULT NULL,
     `date_returned` date DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    
    //fields 
    private int loanId;
    private int bookId;
    private int userId;
    private String dateTaken;
    private String dueDate;
    private String dateReturned;
    private double overdue;
    
    //Constructors
    public Loan(){
        
    }
    
    public Loan(int loanId , int bookId , int userId , String dateTaken , String dueDate , String dateReturned , double overdue){
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
        this.dateTaken = dateTaken;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
        this.overdue = overdue;
    }
    
    //Getters and setters

    public int getLoanId() {
        return loanId;
    }
    

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
    
    public double getOverdue(){
        return this.overdue;
    }
    
    public double setOverdue(double overdue){
        this.overdue = overdue;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
    
    //To String

    @Override
    public String toString() {
        return "Loans{" + "loanId=" + loanId + ", bookId=" + bookId + ", userId=" + userId + ", dateTaken=" + dateTaken + ", dueDate=" + dueDate + ", dateReturned=" + dateReturned + '}';
    }
    
    //Hash and equals
    //equal by loan id
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.loanId;
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
        final Loan other = (Loan) obj;
        if (this.loanId != other.loanId) {
            return false;
        }
        return true;
    }
    
    //format
}
