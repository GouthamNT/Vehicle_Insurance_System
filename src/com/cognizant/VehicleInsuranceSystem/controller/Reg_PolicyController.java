package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.PolicyBO;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

/**
 * Servlet implementation class Reg_PolicyController
 */
public class Reg_PolicyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reg_PolicyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PolicyTO userto=new PolicyTO();
		PolicyBO userbo=new PolicyBO();
		RequestDispatcher rd=null;
		boolean idcheck=false;
		int check=Integer.parseInt(request.getParameter("check"));
		String policyID=request.getParameter("policyid");
		userto.setPolicyID(policyID);
		HttpSession ses=request.getSession(false);
		String customerID=(String)ses.getAttribute("uname");	
		try{
			String username=(String)ses.getAttribute("usertype");
			if(!username.equalsIgnoreCase("somevalue"))
			{
				if(check==0)
				{
					userto.setUserID(customerID);
					userto.setPaytype("reg");
					idcheck=userbo.PayPremium(userto);
					if(idcheck)
					{
						boolean SecondPay=userbo.SecondPay(userto);
						if(!SecondPay)
						{
							request.setAttribute("invalid", "You have already paid");
							rd=request.getRequestDispatcher("Pay_Register.jsp");
							rd.forward(request, response);
						}
						else
						{
							ses.setAttribute("payprevent", "check");
							request.setAttribute("actualamount", userto.getAmountactual());
							request.setAttribute("valid", "true");
							request.setAttribute("policyid", userto.getPolicyID());
							request.setAttribute("state", userto.getState());
							request.setAttribute("type", userto.getVehicle_type());
							request.setAttribute("model", userto.getModel());
							request.setAttribute("amount", userto.getAmount());
							rd=request.getRequestDispatcher("Pay_Register.jsp");
							rd.forward(request, response);
						}
					}
					else
					{
						rd=request.getRequestDispatcher("Pay_Register.jsp");
						request.setAttribute("invalid", "Invalid Policy ID!!!!");
						rd.forward(request, response);
					}
				}
				else if(check==1)
				{
					userto.setAmountactual(Double.parseDouble(request.getParameter("actualamount")));
					request.setAttribute("actualamount", userto.getAmountactual());
					request.setAttribute("valid", "true");
					request.setAttribute("policyid", userto.getPolicyID());
					String submit=request.getParameter("submit");
					if(submit.equalsIgnoreCase("reset"))
					{
						rd=request.getRequestDispatcher("Pay_Register.jsp");
						rd.forward(request, response);
					}
					else
					{
						userto.setState(request.getParameter("state"));
						userto.setVehicle_type(request.getParameter("vehicletype"));
						userto.setModel(request.getParameter("model"));
						userto.setAmount(request.getParameter("amount"));
						request.setAttribute("policyid", userto.getPolicyID());
						request.setAttribute("state", userto.getState());
						request.setAttribute("type", userto.getVehicle_type());
						request.setAttribute("model", userto.getModel());
						request.setAttribute("amount", userto.getAmount());
						String mode=request.getParameter("Mode_of_payment");
						System.out.println(mode);
						String contactno=request.getParameter("contactno");
						userto.setModeofpayment(mode);
						userto.setContactno(contactno);
						request.setAttribute("contact", userto.getContactno());
						request.setAttribute("pay_type", "reg");
						request.setAttribute("mode", userto.getModeofpayment());
						request.setAttribute("mode", userto.getModeofpayment());
						boolean blank;
						String paycheck=(String) ses.getAttribute("payprevent");
						try{
							if(paycheck.equalsIgnoreCase("check"))
							{
								try 
								{
									blank = userbo.Field_valid(userto);
									if(blank)
									{
										request.setAttribute("contact", userto.getContactno());
										request.setAttribute("pay_type", "reg");
										request.setAttribute("mode", userto.getModeofpayment());
										if(mode.equalsIgnoreCase("credit_card")||mode.equalsIgnoreCase("Debit_card"))
										{
											rd=request.getRequestDispatcher("Pay_Card_reg.jsp");
											rd.forward(request, response);
										}
										else
										{
											rd=request.getRequestDispatcher("Pay_Netbanking_Reg.jsp");
											rd.forward(request, response);
										}
									}
								} catch (ValidationException e) {
									request.setAttribute("pay", userto);
									request.setAttribute("error", e.getErrormap_pay());
									rd=request.getRequestDispatcher("Pay_Register.jsp");
									rd.forward(request, response);
								}
							}
						}
						catch(Exception e)
						{
							request.setAttribute("valid", null);
							rd=request.getRequestDispatcher("Pay_Register.jsp");
							rd.forward(request, response);
						}
					}
				}
			}
		}
		catch(Exception e)
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
