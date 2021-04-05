/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Leigh Briody
 */
public class LateReturnFee {

    /*
     CREATE TABLE `late_return_fee` (
     `loan_id` int(7) NOT NULL,
     `days_overdue` int(11) DEFAULT NULL,
     `cost` decimal(3,2) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    
    //fields 
    private int loanId;
    private int daysOverdue;
    private double cost;
    
    //Construcor
    public LateReturnFee(){
        
    }
    
    public LateReturnFee(int loanId , int daysOverdue , double cost){
        this.loanId = loanId;
        this.daysOverdue = daysOverdue;
        this.cost = cost;
    }
    
    //getters and setters

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    //To String 

    @Override
    public String toString() {
        return "LateReturnFee{" + "loanId=" + loanId + ", daysOverdue=" + daysOverdue + ", cost=" + cost + '}';
    }
    
    //equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.loanId;
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
        final LateReturnFee other = (LateReturnFee) obj;
        if (this.loanId != other.loanId) {
            return false;
        }
        return true;
    }
    
}
