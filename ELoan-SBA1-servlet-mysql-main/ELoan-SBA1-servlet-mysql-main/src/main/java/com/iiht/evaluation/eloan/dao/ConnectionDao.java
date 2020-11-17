package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


public class ConnectionDao
{
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	private final String INS_LOAN_INFO="INSERT INTO LoanInfo (applno,purpose,amtrequest,doa,bstructure,bindicator,address, email, mobile,status) values(?,?,?,?,?,?,?,?,?)";  
	private final String UPD_LOAN_INFO = "UPDATE LoanInfo SET purpose=? bstructure=?,bindicator=?,address=?,email=?,mobile=? WHERE applno=? and status = Approve ";					
	private final String GET_BY_ID_ORDER_QRY = "SELECT applno,purpose,amtrequest,doa,bstructure,bindicator,address, email, mobile,status from LoanInfo WHERE applno=?";

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword)
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
	
	public String add(LoanInfo loan) throws Exception
	{
		String applno="";
		if (loan != null)
		{
			//(applno,amtrequest,doa,bstructure,bindicator,address, email, mobile,status)
			connect();
			try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_LOAN_INFO);)
			{		
				applno= new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()).toString();
				pst.setString(1, applno);
				pst.setString(2, loan.getPurpose());
				pst.setInt(3, loan.getAmtrequest());
				pst.setString(4, loan.getDoa());
				pst.setString(5, loan.getBstructure());
				pst.setString(6, loan.getBindicator());
				pst.setString(7, loan.getAddress());
				pst.setString(8, loan.getEmail());
				pst.setString(9, loan.getMobile());
				pst.setString(10, loan.getStatus());
				pst.addBatch();
				pst.executeBatch();
				
			}
			
			catch(SQLException exp)
			{
				throw new Exception("An error occured, Could not save the user details!");
			}
			
		}
		disconnect();
		return applno;
	}
	
	public LoanInfo getLoanInfo(String applno) throws Exception 
	{
		//(applno,amtrequest,doa,bstructure,bindicator,address, email, mobile,status)
		LoanInfo loan=new LoanInfo();
		ApprovedLoan aloan=new ApprovedLoan();
		
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_BY_ID_ORDER_QRY);)
		{
			pst.setString(1, applno);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				LoanInfo ldetails = new LoanInfo();
				ldetails.setApplno(rs.getString(1));
				ldetails.setPurpose(rs.getString(2));
				ldetails.setAmtrequest(rs.getInt(3));
				ldetails.setDoa(rs.getString(4));
				ldetails.setBstructure(rs.getString(5));
				ldetails.setBindicator(rs.getString(6));
				ldetails.setAddress(rs.getString(7));
				ldetails.setEmail(rs.getString(8));
				ldetails.setMobile(rs.getString(9));
				ldetails.setStatus(rs.getString(10));
				aloan.setAmotsanctioned(rs.getInt(2));
				aloan.setLoanterm(rs.getInt(3));
				aloan.setPsd(rs.getString(4));
				aloan.setLcd(rs.getString(5));
				aloan.setEmi(rs.getInt(6));
			}	
				
		}catch(SQLException exp)
		{
			throw new Exception("An error occured, Could not retrive the order summary details!" +exp);
		}
		disconnect();
		return loan;
		}
	public LoanInfo save(LoanInfo loan) throws Exception
	{
		
		if (loan != null)
		{
			//(applno,amtrequest,doa,bstructure,bindicator,address, email, mobile,status)
			connect();
			try(PreparedStatement pst = jdbcConnection.prepareStatement(UPD_LOAN_INFO);)
			{		
				
				
				pst.setString(2, loan.getPurpose());
				pst.setInt(3, loan.getAmtrequest());
				pst.setString(4, loan.getDoa());
				pst.setString(5, loan.getBstructure());
				pst.setString(6, loan.getBindicator());
				pst.setString(7, loan.getAddress());
				pst.setString(8, loan.getEmail());
				pst.setString(9, loan.getMobile());
				pst.setString(10, loan.getStatus());
				pst.executeUpdate();
				
			}
			
			catch(SQLException exp)
			{
				throw new Exception("An error occured, Could not save the user details!");
			}
			
		}
		disconnect();
		return loan;
	}
	
}
