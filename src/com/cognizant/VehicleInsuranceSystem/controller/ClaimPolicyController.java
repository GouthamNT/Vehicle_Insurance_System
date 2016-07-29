package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.ClaimPolicyBO;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.AccidentClaimTO;
import com.cognizant.VehicleInsuranceSystem.to.ClaimPolicyTO;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;

/**
 * Servlet implementation class ClaimPolicyController
 */
public class ClaimPolicyController extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClaimPolicyController() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result=false;
		String accident=null;
		AccidentClaimTO accidentto=new AccidentClaimTO();
		TheftClaimTO theftto=new TheftClaimTO();
		ClaimPolicyBO claimbo=new ClaimPolicyBO();
		ClaimPolicyTO claimto=new ClaimPolicyTO();
		RequestDispatcher dispatcher;
		HttpSession ses = request.getSession(false);
		String policyid=request.getParameter("polid");
		claimto.setPolicyid(policyid);
		accident=request.getParameter("acci");	
		try{

			String username=(String)ses.getAttribute("usertype");
			String customerid=(String)ses.getAttribute("uname");
			System.out.println(username);
			if(!(username.equalsIgnoreCase("somevalue")))
			{

				System.out.println(customerid);
				claimto.setCustomerID(Long.parseLong(customerid));
				System.out.println(claimto.getCustomerID());
				try 
				{
					result=claimbo.polidcheck(claimto);
					if(result)
					{
						if(!(accident.equals("somevalue")))
						{
							boolean accidentcheck=claimbo.validate(policyid,accidentto);
							System.out.println(accidentto.getAmount());
							System.out.println(accidentcheck);
							ses.setAttribute("claimtype","accident");
							if (accidentcheck)
							{
								ses.setAttribute("accident_claim","accidentcheck");
								ses.setAttribute("policyid", policyid);
								dispatcher=request.getRequestDispatcher("AccidentClaim.jsp");
								ses.setAttribute("accident", accidentto);
								dispatcher.forward(request, response);
							}
							else
							{
								ses.setAttribute("policyid", policyid);
								request.setAttribute("error_amount", "You haven't paid any Premium");
								dispatcher=request.getRequestDispatcher("ClaimPolicy.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				}
				catch (ValidationException e)
				{
					request.setAttribute("policyid", policyid);
					request.setAttribute("error", e.getErrormap());
					dispatcher=request.getRequestDispatcher("ClaimPolicy.jsp");
					dispatcher.forward(request, response);
				}
				catch(Exception e1){
					boolean theftcheck=claimbo.checkifvalid(policyid,theftto);
					ses.setAttribute("claimtype","theft");	
					ses.setAttribute("policyid", policyid);
					if(theftcheck)
					{
						ses.setAttribute("theft_claim","theftcheck");
						dispatcher=request.getRequestDispatcher("TheftClaim.jsp");
						ses.setAttribute("theft", theftto);
						dispatcher.forward(request, response);
					}
					else
					{
						ses.setAttribute("policyid", policyid);
						request.setAttribute("error_amount", "You haven't paid any Premium");
						dispatcher=request.getRequestDispatcher("ClaimPolicy.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("logout caught");
			request.setAttribute("errmsg", "Please Login again!!!");
			dispatcher=request.getRequestDispatcher("AdminLogin.jsp");
			dispatcher.forward(request, response);
		}
	}
}





