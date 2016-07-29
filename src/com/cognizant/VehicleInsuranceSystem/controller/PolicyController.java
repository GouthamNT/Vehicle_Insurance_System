package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.PolicyBO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

/**
 * Servlet implementation class PayController
 */
public class PolicyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PolicyController() {
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
		if(check==0)
		{
			userto.setPaytype("direct");
			try {
				idcheck=userbo.PayPremium(userto);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(idcheck)
			{
				boolean dueCheck=userbo.DueCheck(userto);
				request.setAttribute("amountgiven", userto.getAmount());
				if(!dueCheck)
				{
					request.setAttribute("Invalid", "You are not Eligible for Direct Pay!!! Kindly Login and Pay");
					rd=request.getRequestDispatcher("Pay_Direct.jsp");
					rd.forward(request, response);
				}
				else
				{
					HttpSession ses=request.getSession(true);
					ses.setAttribute("payprevent", "check");
					request.setAttribute("validid", "valid");
					request.setAttribute("policyid", userto.getPolicyID());
					rd=request.getRequestDispatcher("Pay_Direct.jsp");
					rd.forward(request, response);
				}
			}
			else
			{
				rd=request.getRequestDispatcher("Pay_Direct.jsp");
				request.setAttribute("Invalid", "Invalid Policy ID!!!!");
				rd.forward(request, response);
			}
		}
		else if(check==1)
		{
			HttpSession ses = request.getSession(false);
			request.setAttribute("validid", "valid");
			request.setAttribute("policyid", userto.getPolicyID());
			String submit=request.getParameter("submit");
			String amountgiven=request.getParameter("amountgiven");
			request.setAttribute("amountgiven", amountgiven);
			//System.out.println(amountgiven);
			if(submit.equalsIgnoreCase("reset"))
			{
				rd=request.getRequestDispatcher("Pay_Direct.jsp");
				rd.forward(request, response);
			}
			else
			{
				String amount=request.getParameter("amount");
				String mode=request.getParameter("Mode_of_payment");
				String contactno=request.getParameter("contactno");
				userto.setAmount(amount);
				userto.setModeofpayment(mode);
				userto.setContactno(contactno);
				request.setAttribute("mode", userto.getModeofpayment());
				System.out.println(userto.getModeofpayment());
				try{
					String paycheck=(String) ses.getAttribute("payprevent");
					if(paycheck.equalsIgnoreCase("check"))
					{
						try
						{
							boolean blank=userbo.Field_valid(userto);
							if(blank)
							{
								System.out.println(paycheck);
								request.setAttribute("amount", userto.getAmount());
								request.setAttribute("mode", userto.getModeofpayment());
								System.out.println(amountgiven);
								boolean amountcheck=userbo.amountcheck(amount, amountgiven);
								if(!amountcheck)
								{

									request.setAttribute("amountless", "Enter amount greater than "+amountgiven);
									request.setAttribute("pay", userto);
									rd=request.getRequestDispatcher("Pay_Direct.jsp");
									rd.forward(request, response);
								}
								else
								{
									if(mode.equalsIgnoreCase("credit_card")||mode.equalsIgnoreCase("Debit_card"))
									{
										request.setAttribute("user", userto);
										rd=request.getRequestDispatcher("Pay_Card.jsp");
										rd.forward(request, response);
									}
									else
									{
										request.setAttribute("user", userto);
										rd=request.getRequestDispatcher("Pay_Netbanking.jsp");
										rd.forward(request, response);
									}
								}
							}
						}
						catch (ValidationException e) {
							request.setAttribute("pay", userto);
							request.setAttribute("error", e.getErrormap_pay());
							rd=request.getRequestDispatcher("Pay_Direct.jsp");
							rd.forward(request, response);
						}
					}
				}

				catch(Exception e)
				{
					request.setAttribute("validid", null);
					rd=request.getRequestDispatcher("Pay_Direct.jsp");
					rd.forward(request, response);
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
