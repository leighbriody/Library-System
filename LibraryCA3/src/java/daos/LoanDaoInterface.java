/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Loan;
import java.util.ArrayList;

/**
 *
 * @author Leigh Briody
 */
public interface LoanDaoInterface {

    //This one will be loans where date returned = null
    public ArrayList<Loan> allLoansActive(int userId);

    public Loan getLoan(int loanId, int bookId);
    //This one will be select all loans where user id = user id

    public ArrayList<Loan> allLoansMade(int userId);

    public double getUsersTotalOverdueFee(int userId);

    public ArrayList<Loan> getOverdueFeeLoans(int userId);

    public boolean createLoan(int book_id, int user_id, String date_taken, String due_date);

    public boolean isBookOnLoan(int book_id, int user_id);

    public boolean returnBook(String date_returned, int book_id, int user_id, double overdue);
}
