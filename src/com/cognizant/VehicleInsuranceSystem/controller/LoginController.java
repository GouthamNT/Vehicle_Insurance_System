package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.bo.LoginBO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.LoginTO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("LoginController");
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		boolean result;
		LoginTO loginto=new LoginTO();
		LoginBO loginbo=new LoginBO();
		String uname=null;
		String pwd=null;
		uname=request.getParameter("uname");
		pwd=request.getParameter("pwd");
		loginto.setUsername(uname);
		loginto.setPassword(pwd);
		//String button=request.getParameter("login");
		try {
			result=loginbo.userandpw(loginto);
		} catch (DatabaseOperationException e) {
			LOG.debug("Message" + "Login Error");
			result=false;
		} catch (ApplicationException e) {
			LOG.debug("Message" + "Login Error");
			result=false;
		}
		String user=loginto.getUsertype();
		System.out.println(result);
		if((result))
		{
			if(!(user.equalsIgnoreCase("customer")))
			{
				HttpSession ses=request.getSession(true);
				ses.setAttribute("uname",uname);
				ses.setAttribute("usertype","admin");
				dispatcher=request.getRequestDispatcher("AdminIndex.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				HttpSession ses=request.getSession(true);
				ses.setAttribute("usertype","customer");
				ses.setAttribute("uname",uname);
				dispatcher=request.getRequestDispatcher("UserIndex.jsp");
				dispatcher.forward(request,response);
			}
		}
		else
		{
			request.setAttribute("errmsg", "Invalid Credentials");
			dispatcher=request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request,response);
		}

	}
}


