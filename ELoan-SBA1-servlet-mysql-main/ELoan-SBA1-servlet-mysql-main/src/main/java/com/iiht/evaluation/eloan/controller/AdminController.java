package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.iiht.evaluation.eloan.dao.AdminConnDao;
import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanDetails;
import com.iiht.evaluation.eloan.model.LoanInfo;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminConnDao aconnDao;
	
	public void setConnDao(AdminConnDao aconnDao) {
		this.aconnDao = aconnDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbc:mysql://localhost:3306/invtdb");
		String jdbcUsername = config.getServletContext().getInitParameter("root");
		String jdbcPassword = config.getServletContext().getInitParameter("root");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.aconnDao = new AdminConnDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "callemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	ApprovedLoan aloan = new  ApprovedLoan();
		if(aloan!=null)
    	{
    		double emi = (aloan.getAmotsanctioned()/aloan.getLoanterm()*0.12);
    		aloan.setEmi(Integer.parseInt("emi"));
    		
    
    	}
    	return null;
		
		
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	
		return  "process.jsp";
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		String view="";	
		try
		{
			List<LoanDetails> loand = aconnDao.getApprovedLoan();
			request.setAttribute("loand", loand);
			view="listall.jsp";
		}catch(Exception e)
		{
			view ="errorPage.jsp";
		}
		return view;
		
	
	}

	
}