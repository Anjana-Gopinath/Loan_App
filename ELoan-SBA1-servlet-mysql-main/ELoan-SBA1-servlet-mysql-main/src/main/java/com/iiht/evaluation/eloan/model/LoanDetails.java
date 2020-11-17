package com.iiht.evaluation.eloan.model;
import java.util.List;


public class LoanDetails 
{
    private LoanInfo loan = new LoanInfo();
    private List<ApprovedLoan> aloan ;
    
    public LoanDetails()
    {
    	
    }
     public LoanDetails(LoanInfo loan,List<ApprovedLoan> aloan) 
     {
    	 this.loan=loan;
    	 this.aloan=aloan;
     }
     public LoanInfo getLoanInfo()
     {
    	 return loan;
     }
     public void setLoanInfo(LoanInfo loan)
     {
    	 this.loan=loan;
     }
     public List <ApprovedLoan> getApprovedLoan()
     {
    	 return aloan;
     }
     public void setApprovedLoan(List<ApprovedLoan> aloan) {
 		this.aloan = aloan;
 	}
    
}
