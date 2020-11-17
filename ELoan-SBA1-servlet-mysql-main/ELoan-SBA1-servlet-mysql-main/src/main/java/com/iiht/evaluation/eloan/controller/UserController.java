package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.xdevapi.Statement;




@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
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
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
        	
        
        List<User> userl = (List<User>)request.getAttribute("User");
        String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);        
        if("admin".equals(request.getParameter("loginid")) && "admin".equals(request.getParameter("password"))) {
        	session.invalidate();
        	session=req.getSession(true);
        	session.setAttribute("logUser", "admin");
        	
       
        } else
        {
        	resp.sendRedirect("index.jsp?action=failed");
        }
        }catch(Exception e)
        {
        	request.setAttribute("errMsg", e.getMessage());
			return "errorPage.jsp";

        }
        
		
		return null;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		
		
		LoanInfo loan = new LoanInfo();
		loan.setApplno(request.getParameter("applno"));
		loan.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		loan.setPurpose(request.getParameter("purpose"));
		loan.setDoa(request.getParameter("doa"));
		loan.setBstructure(request.getParameter("bstructure"));
		loan.setBindicator(request.getParameter("bindicator"));
		loan.setEmail(request.getParameter("email"));
		loan.setMobile(request.getParameter("mobile"));
		loan.setStatus(request.getParameter("status"));
	    return "index.jsp";
		
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("isNew", true);
		return "application.jsp";
		
		
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		 
		LoanInfo loan = new LoanInfo();
		loan.setApplno(request.getParameter("applno"));
		loan.setPurpose(""+request.getParameter("purpose"));
		loan.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		loan.setDoa(""+request.getParameter("doa"));
		loan.setBstructure(""+request.getParameter("bstructure"));
		loan.setBindicator(""+request.getParameter("bindicator"));
		loan.setAddress(""+request.getParameter("address"));
		loan.setEmail(""+request.getParameter("email"));
		loan.setMobile(""+request.getParameter("mobile"));
		loan.setStatus(""+request.getParameter("status"));
		try
		{
			if(request.getParameter("action").equals("updateloaninfo")) 
			{
				connDao.save(loan);
		}
		
		else
		{
			connDao.add(loan);
		}
		}catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			return "errorPage.jsp";
		}
		return null;
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		request.setAttribute("isNew", true);
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		User use = new User();
		use.setUsername(request.getParameter("username"));
		use.setPassword(request.getParameter("password"));
		return "index.jsp";
		
		
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("isNew", true);
		return "register.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String view="";
		
		
		try {
			request.setAttribute("ConnectionDao", connDao);
			view = "trackloan.jsp";
		} catch (Exception e) {
			view = "errorPage.jsp";
		}
		return view;
	}
		
		
		
	

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		request.setAttribute("isNew", true);
		return "editloan.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		
		return "tracklon.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		return null;
	}
}