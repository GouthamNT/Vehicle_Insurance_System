package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ContactUs
 */
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses=request.getSession(false);
		RequestDispatcher rd=null;
		try{
			String username=(String)ses.getAttribute("usertype");
			if(username.equalsIgnoreCase("admin"))
			{
				rd=request.getRequestDispatcher("AdminContactUs.jsp");
				rd.forward(request, response);		
			}
			else
			{
				rd=request.getRequestDispatcher("UserContactUs.jsp");
				rd.forward(request, response);
			}
		}
		catch (Exception e)
		{
			request.setAttribute("errmsg", "Please Login again!!!");
			rd=request.getRequestDispatcher("AdminLogin.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
