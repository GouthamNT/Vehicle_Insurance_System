package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.VehicleInsuranceSystem.bo.CustomerRegisterBO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.CustomerRegisterTO;

/**
 * Servlet implementation class CustomerRegisterController
 */
public class CustomerRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		CustomerRegisterBO cusregisterbo=new CustomerRegisterBO();
		CustomerRegisterTO cusregisterto=new CustomerRegisterTO();
		boolean v=false;
		RequestDispatcher dispatcher=null;
		boolean cusresult=false;

		String CusName=request.getParameter("CusName");
		String CusPassword=request.getParameter("CusPassword");
		String CusRe_password=request.getParameter("CusRe_password");
		String CusAddress=request.getParameter("CusAddress");
		String CusCity=request.getParameter("CusCity");
		String CusState=request.getParameter("CusState");
		String CusCountry=request.getParameter("CusCountry");
		String Cuspin=request.getParameter("CusPincode");

		String CusEmail=request.getParameter("CusEmail");
		String CusGender=request.getParameter("CusGender");
		String CusCtc=request.getParameter("CusContact_no");
		long CusPincode=0;
		long CusContact_no=0;
		try{

			CusPincode=Long.parseLong(Cuspin);
			CusContact_no=Long.parseLong(CusCtc);
		}
		catch(Exception e)
		{
			CusPincode=0;
			CusContact_no=0;
		}
		String CusDate_of_birth=request.getParameter("CusDate_of_birth");

		cusregisterto.setCusName(CusName);
		cusregisterto.setCusPassword(CusPassword);
		cusregisterto.setCusRe_password(CusRe_password);
		cusregisterto.setCusAddress(CusAddress);
		cusregisterto.setCusCity(CusCity);
		cusregisterto.setCusState(CusState);
		cusregisterto.setCusCountry(CusCountry);
		cusregisterto.setCusPincode(Cuspin);
		cusregisterto.setCusPincode_num(CusPincode);
		cusregisterto.setCusEmail(CusEmail);
		cusregisterto.setCusContact_no(CusCtc);
		cusregisterto.setCusContact_no1(CusContact_no);
		cusregisterto.setCusDate_of_birth(CusDate_of_birth);
		try{
			cusregisterto.setCusGender(CusGender);
		}
		catch(Exception e)
		{
			cusregisterto.setCusGender("");
		}

		try{
			v=cusregisterbo.validate(cusregisterto);
			if(v)
			{
				cusresult=cusregisterbo.registerUser(cusregisterto);
				if(cusresult)
				{
					dispatcher=request.getRequestDispatcher("Login.jsp");
					request.setAttribute("message","Customer Registered successfully" +"<br>"+"Kindly note your Customer ID" +"<br>"+"Customer id="+cusregisterto.getCustomer_id());
					dispatcher.forward(request,response);
				}
				else
				{
					dispatcher=request.getRequestDispatcher("CustomerRegistration.jsp");
					request.setAttribute("message","Registration Unsuccessfull!....");
					dispatcher.forward(request,response);
				}}
			else
			{
				dispatcher=request.getRequestDispatcher("CustomerRegistration.jsp");
				request.setAttribute("message","Registration Unsuccessfull!....");
				dispatcher.forward(request,response);
			}

		}
		catch (ValidationException e) {
			request.setAttribute("customer",cusregisterto);
			request.setAttribute("err", e.getErrormap());
			dispatcher=request.getRequestDispatcher("CustomerRegistration.jsp");
			dispatcher.forward(request, response);
		}

		catch(DatabaseOperationException e)
		{
			dispatcher=request.getRequestDispatcher("CustomerRegistration.jsp");
			request.setAttribute("message", "Problem occured while registering..........");
			dispatcher.forward(request, response);
		} catch (ApplicationException e) {
			dispatcher=request.getRequestDispatcher("CustomerRegistration.jsp");
			request.setAttribute("message", "Problem occured while registering..........");
			dispatcher.forward(request, response);
		}



	}

}
