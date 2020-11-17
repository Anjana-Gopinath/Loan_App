package com.iiht.evaluation.eloan.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iiht.evaluation.eloan.model.LoanDetails;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class AdminConnDao 
{
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	private final String INS_APPR_QRY="INSERT INTO LoanDetails (applno,amotsanctioned,loanterm,psd,lcd,emi) values(?,?,?,?,?,?)";  
	private final String SEL_APPR_QRY = "SELECT applno,amotsanctioned,loanterm,psd,lcd,emi from LoanDetails";				
	private final String GET_APPR_QRY = "SELECT applno,amotsanctioned,doa,bstructure,bindicator,address, email, mobile,status from LoanInfo WHERE applno=?";
  
	public AdminConnDao(String jdbcURL, String jdbcUsername, String jdbcPassword)
	{
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
	public  Connection connect() throws SQLException 
	{
		if (jdbcConnection == null || jdbcConnection.isClosed())
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e)
			{
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}
	public void disconnect() throws SQLException 
	{
		if (jdbcConnection != null && !jdbcConnection.isClosed())
		{
			jdbcConnection.close();
		}
	}
	public String save(LoanDetails loan) throws Exception {
		String applno="";
		if (loan != null) {
			connect();
			try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_APPR_QRY);) {				
				
				ApprovedLoan l = new ApprovedLoan();
				List<ApprovedLoan> loand= loan.getApprovedLoan();
				applno= new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()).toString();
				for(int i=0;i<loand.size();i++) {				
					
					//applno,amtrequest,loanterm,psd,lcd,emi from LoanDetails
					pst.setString(1, applno);
					
					pst.setInt(2,l.getAmotsanctioned());
					pst.setInt(3, l.getLoanterm());
					pst.setString(4, l.getPsd());
					pst.setString(5, l.getLcd());
					pst.setInt(6, l.getEmi());
					
					
					
					pst.addBatch();
				}	
				
			} catch (SQLException exp) {
				throw new Exception("An error occured, Could not save the user details!");
			}
			disconnect();
			
		}
		return applno;
	}
	public LoanDetails getApprovedLoan(String applno) throws Exception {
		List<ApprovedLoan> aloan=new ArrayList<>();
		LoanInfo loan=new LoanInfo();
		LoanDetails loand = new LoanDetails(loan,aloan);
		
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_APPR_QRY);){
			pst.setString(1, applno);
			ResultSet rs = pst.executeQuery();
			
	
			while(rs.next()) {
				ApprovedLoan apploan = new ApprovedLoan();
				apploan.setAmotsanctioned(rs.getInt(2));
				apploan.setLoanterm(rs.getInt(3));
				apploan.setPsd(rs.getString(4));
				apploan.setLcd(rs.getString(5));
				apploan.setEmi(rs.getInt(6));
				aloan.add(apploan);
				loan.setPurpose(rs.getString(2));
				loan.setAmtrequest(rs.getInt(3));	
				loan.setDoa(rs.getString(4));
				loan.setBstructure(rs.getString(5));
				loan.setBindicator(rs.getString(6));
				loan.setAddress(rs.getString(7));
				loan.setEmail(rs.getString(8));
				loan.setMobile(rs.getString(9));
				loan.setStatus(rs.getString(10));
			}
			if(aloan.isEmpty()) {
				aloan=null;
			}
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the product details!" +exp);
		}
		disconnect();
		return loand;
	}
	public List<LoanDetails> getApprovedLoan() throws Exception{
		List<LoanDetails> aloan=new ArrayList<>();
		
		LoanInfo loan=new LoanInfo();
		
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(SEL_APPR_QRY);){
			ResultSet rs = pst.executeQuery();
			
			//oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus 
			while(rs.next()) {
				ApprovedLoan apploan = new ApprovedLoan();
				apploan.setAmotsanctioned(rs.getInt(2));
				apploan.setLoanterm(rs.getInt(3));
				apploan.setPsd(rs.getString(4));
				apploan.setLcd(rs.getString(5));
				apploan.setEmi(rs.getInt(6));
				
				loan.setPurpose(rs.getString(2));
				loan.setAmtrequest(rs.getInt(3));	
				loan.setDoa(rs.getString(4));
				loan.setBstructure(rs.getString(5));
				loan.setBindicator(rs.getString(6));
				loan.setAddress(rs.getString(7));
				loan.setEmail(rs.getString(8));
				loan.setMobile(rs.getString(9));
				loan.setStatus(rs.getString(10));
			}
			if(aloan.isEmpty()) {
				aloan=null;
			}
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the order summary details!" +exp);
		}
		disconnect();
		return aloan;
	}	

}
