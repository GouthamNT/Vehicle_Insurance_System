package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.AccidentClaimBO;
import com.cognizant.VehicleInsuranceSystem.bo.ClaimPolicyBO;
import com.cognizant.VehicleInsuranceSystem.to.AccidentClaimTO;

/**
 * Servlet implementation class AccidentClaimController
 */
public class AccidentClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccidentClaimController() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AccidentClaimBO accidentbo=new AccidentClaimBO ();
		AccidentClaimTO accidentto=new AccidentClaimTO ();
		HttpSession ses = request.getSession(true);
		String policy=(String) ses.getAttribute("policyid");
		String value=accidentbo.check(policy);
		accidentto= (AccidentClaimTO)ses.getAttribute("accident");
		//RequestDispatcher dispatcher=null;
		double am=0;
		double amt=0;
		int flag=0;
		amt=Double.parseDouble(accidentto.getAmount());
		String weightage=request.getParameter("weight");
		ses.setAttribute("weightage", weightage); 
		if(value.equalsIgnoreCase("tw"))
		{
			flag=1;	
			if(weightage.equalsIgnoreCase("Fire"))
				am=  amt*0.80;
			else if(weightage.equalsIgnoreCase("Roadaccident"))
				am= (amt*0.70);
			else if(weightage.equalsIgnoreCase("MaliciousActs"))
				am= (amt*0.50);
			else if(weightage.equalsIgnoreCase("Self-destruction"))
				am= (amt*0.40);
			else if(weightage.equalsIgnoreCase("PartFailure"))
				am= (amt*0.20);
		}
		else if(value.equalsIgnoreCase("fw")||(value.equalsIgnoreCase("ot")))
		{
			flag=2;
			if(weightage.equalsIgnoreCase("Fire"))
				am= (amt*0.70);
			else if(weightage.equalsIgnoreCase("Roadaccident"))
				am= (amt*0.65);
			else if(weightage.equalsIgnoreCase("MaliciousActs"))
				am= (amt*0.50);
			else if(weightage.equalsIgnoreCase("Self-destruction"))
				am= (amt*0.30);
			else if(weightage.equalsIgnoreCase("PartFailure"))
				am= (amt*0.20);
		}
		RequestDispatcher dispatcher1=null;
		accidentto.setAccamt(am);
		if((flag==1 && am>=5000)||(flag==2 && am>=20000))
		{
			dispatcher1=request.getRequestDispatcher("Weightage.jsp");
			ses.setAttribute("amount",accidentto.getAccamt());
			dispatcher1.forward(request, response);
		}
		else
		{
			dispatcher1=request.getRequestDispatcher("Weightage.jsp");
			ses.setAttribute("amount",accidentto.getAccamt());
			dispatcher1.forward(request, response);
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id="";
		ClaimPolicyBO claimbo=new ClaimPolicyBO();
		AccidentClaimTO accidentto=new AccidentClaimTO ();
		AccidentClaimBO accidentbo=new AccidentClaimBO ();

		HttpSession ses = request.getSession(false);
		RequestDispatcher dispatcher=null;
		String policy=(String) ses.getAttribute("policyid");
		String accidentclaim=request.getParameter("claim");
		String accidenttype=request.getParameter("AccidentType");
		String image=request.getParameter("image");
		if(!accidentclaim.equals(null))
			id=accidentbo.generate();

		String value=accidentbo.check(policy);

		String weightage=(String) ses.getAttribute("weightage");
		double am=0;
		double amt=0;
		int flag=0;
		accidentto=(AccidentClaimTO)ses.getAttribute("accident");
		try{
			String validate=(String) ses.getAttribute("accident_claim");
			if(validate.equals("accidentcheck"))
			{

				try
				{
					amt=Double.parseDouble(accidentto.getAmount());
				}
				catch(Exception e)
				{

				}
				if(accidenttype.equalsIgnoreCase("default"))
				{
					request.setAttribute("errmsg", "Enter details in each field");
					dispatcher=request.getRequestDispatcher("AccidentClaim.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					if(value.equalsIgnoreCase("tw"))
					{
						flag=1;	

						if(weightage.equalsIgnoreCase("Fire"))
							am=  amt*0.80;
						else if(weightage.equalsIgnoreCase("Roadaccident"))
							am= (amt*0.70);
						else if(weightage.equalsIgnoreCase("MaliciousActs"))
							am= (amt*0.50);
						else if(weightage.equalsIgnoreCase("Self-destruction"))
							am= (amt*0.40);
						else if(weightage.equalsIgnoreCase("PartFailure"))
							am= (amt*0.20);
						ses.setAttribute("accidenttype",weightage);
					}
					else if(value.equalsIgnoreCase("fw")||(value.equalsIgnoreCase("ot")))
					{
						flag=2;

						if(weightage.equalsIgnoreCase("Fire"))
							am= (amt*0.70);
						else if(weightage.equalsIgnoreCase("RoadAccidents"))
							am= (amt*0.65);
						else if(weightage.equalsIgnoreCase("MaliciousActs"))
							am= (amt*0.50);
						else if(weightage.equalsIgnoreCase("Self-destruction"))
							am= (amt*0.30);
						else if(weightage.equalsIgnoreCase("PartFailure"))
							am= (amt*0.20);
						ses.setAttribute("accidenttype",weightage);

					}
					ses.setAttribute("amount", am);
					accidentto.setAccamt(am);
					ses.setAttribute("ID",id);
					String claimid=(String) ses.getAttribute("ID");
					String claimtype=(String) ses.getAttribute("claimtype");


					String acctype=(String) ses.getAttribute("accidenttype");
					double claimamount=(Double) ses.getAttribute("amount");


					if(flag==1 && am>=5000)
					{
						int res=claimbo.update(claimid,policy,claimtype);
						int accres=accidentbo.update(acctype,image,claimamount,claimid);
						if(res==1 && accres==1)
						{ ses.removeAttribute("accident_claim");
						accidentto.setRes(res);
						accidentto.setAccres(accres);
						dispatcher=request.getRequestDispatcher("AccidentClaimID.jsp");
						dispatcher.forward(request, response);
						}
					}
					else if(flag==2 && am>=20000)
					{
						int res=claimbo.update(claimid,policy,claimtype);
						int accres=accidentbo.update(acctype,image,claimamount,claimid);
						if(res==1 && accres==1)
						{
							ses.removeAttribute("accident_claim");
							ses.setAttribute("res",res);
							ses.setAttribute("accres",accres);
							dispatcher=request.getRequestDispatcher("AccidentClaimID.jsp");
							dispatcher.forward(request, response);
						}
					}
					else
					{
						request.setAttribute("errmsg", "You are not eligible for the CLAIM");
						dispatcher=request.getRequestDispatcher("ClaimPolicy.jsp");
						dispatcher.forward(request,response);
					}
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

