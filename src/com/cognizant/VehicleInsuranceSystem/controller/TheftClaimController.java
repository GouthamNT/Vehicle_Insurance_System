package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.ClaimPolicyBO;
import com.cognizant.VehicleInsuranceSystem.bo.TheftClaimBO;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;

/**
 * Servlet implementation class TheftClaimController
 */
public class TheftClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TheftClaimTO theftto=new TheftClaimTO ();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TheftClaimController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean val=false;
		ClaimPolicyBO claimbo=new ClaimPolicyBO();
		TheftClaimBO theftbo=new TheftClaimBO ();

		HttpSession ses = request.getSession(false);
		RequestDispatcher dispatcher=null;

		

		String dtheft=request.getParameter("dtheft");
		ses.setAttribute("dtheft", dtheft);
		theftto.setDateoftheft(dtheft);

		String dcomplaint=request.getParameter("dcomplaint");
		ses.setAttribute("dcomplaint",dcomplaint);
		theftto.setComplaint(dcomplaint);

		String firno=request.getParameter("firno");
		ses.setAttribute("firno",firno);
		theftto.setFirno(firno);

		String psbranch=request.getParameter("psbranch");
		ses.setAttribute("psbranch",psbranch);
		theftto.setState(psbranch);
		String theftclaim=request.getParameter("claim");
		String id=null;
		double amt=0;
		try{
			String validate=(String) ses.getAttribute("theft_claim");
			if(validate.equals("theftcheck"))
			{
				try {
					val=theftbo.validate(theftto);
					if(val)
					{
						if(!(theftclaim.equals(null)))
							id=theftbo.generate();
						theftto=(TheftClaimTO)ses.getAttribute("theft");
						//ses.setAttribute("validate", "check");

						try
						{

							amt=Double.parseDouble(theftto.getAmount());
						}
						catch(Exception e)
						{
						}
						double am= amt*0.75;
						ses.setAttribute("amount", am);
						ses.setAttribute("ID", id);
						ses.setAttribute("claimamount",amt);
						double totamount=(Double) ses.getAttribute("claimamount");
						String datetheft=(String) ses.getAttribute("dtheft");
						String datecomplaint=(String) ses.getAttribute("dcomplaint");
						String firnum=(String) ses.getAttribute("firno");
						String policebranch=(String) ses.getAttribute("psbranch");
						Double theftclaimamount=(Double) ses.getAttribute("amount");
						String theftid=(String) ses.getAttribute("ID");
						String claimid=(String) ses.getAttribute("ID");
						String claimtype=(String) ses.getAttribute("claimtype");
						String policy=(String) ses.getAttribute("policyid");
						int rest=claimbo.update(claimid,policy,claimtype);
						int reslt=theftbo.update(totamount,datetheft,datecomplaint,firnum,policebranch,theftclaimamount,theftid);
						if(reslt==1 && rest==1)
						{
							ses.removeAttribute("theft_claim");
							dispatcher=request.getRequestDispatcher("TheftClaimID.jsp");
							dispatcher.forward(request, response);	
						}


					}
				} 
				catch (ValidationException e) 
				{
					request.setAttribute("theft_exception", theftto);
					request.setAttribute("error", e.getErrormap());
					dispatcher=request.getRequestDispatcher("TheftClaim.jsp");
					dispatcher.forward(request, response);
				}
				catch(Exception e1)
				{ 
					request.setAttribute("mandatory", "All fields are Mandatory");
					dispatcher=request.getRequestDispatcher("TheftClaim.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		catch(Exception e1)
		{
			dispatcher=request.getRequestDispatcher("ClaimPolicy.jsp");
			dispatcher.forward(request, response);
		}

	}
}

