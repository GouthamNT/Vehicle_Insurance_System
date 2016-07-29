package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.CardBO;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.CardTO;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

/**
 * Servlet implementation class CardController
 */
public class CardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardController() {
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
		CardTO cardto=new CardTO();
		CardBO cardbo=new CardBO();
		PolicyTO userto=new PolicyTO();
		userto.setPolicyID(request.getParameter("policyid"));
		userto.setAmount(request.getParameter("amount"));
		userto.setModeofpayment(request.getParameter("mode"));
		String mode=request.getParameter("mode");
		RequestDispatcher rd=null;
		String paytype=request.getParameter("pay_type");
		request.setAttribute("pay_type",paytype);
		String card=null;
		String success=null;
		String netbanking=null;
		String pay=null;
		HttpSession ses=request.getSession(false);
		String paycheck=(String) ses.getAttribute("payprevent");
		System.out.println(mode);
		request.setAttribute("mode",mode);
		try
		{
			if(paytype.equalsIgnoreCase("reg"))
			{
				userto.setPaytype("reg");
				pay="Pay_Register.jsp";
				card="Pay_Card_reg.jsp";
				success="Pay_Success_Reg.jsp";
				netbanking="Pay_Netbanking_Reg.jsp";
			}
			else
			{
				pay="Pay_Direct.jsp";
				userto.setPaytype("direct");
				card="Pay_Card.jsp";
				success="Pay_Success.jsp";
				netbanking="Pay_Netbanking.jsp";
			}
		}catch (Exception e)
		{
			userto.setPaytype("direct");
			pay="Pay_Direct.jsp";
			card="Pay_Card.jsp";
			success="Pay_Success.jsp";
			netbanking="Pay_Netbanking.jsp";
		}
		try{
			if(paycheck.equalsIgnoreCase("check"))
			{
				try{
					userto.setAmountactual(Double.parseDouble(request.getParameter("actualamount")));
				}
				catch(Exception e){
					System.out.println("null caught");
				}
				
				if(mode.equalsIgnoreCase("Net_Banking"))
				{
					String bank=request.getParameter("Bank");
					userto.setPolicyID(request.getParameter("policyid"));
					userto.setAmount(request.getParameter("amount"));
					userto.setModeofpayment(request.getParameter("mode"));
					boolean checkbank=cardbo.checkBank(bank);
					if(checkbank)	
					{
						boolean proceed=cardbo.Store(userto);
						if(proceed)
						{
							ses.removeAttribute("payprevent");
							request.setAttribute("duedate", userto.getDue());
							request.setAttribute("paymentid", userto.getPaymentID());
							rd=request.getRequestDispatcher(success);
							rd.forward(request, response);
						}
					}
					else
					{
						request.setAttribute("bankerr", "Choose Preferred Bank");
						rd=request.getRequestDispatcher(netbanking);
						rd.forward(request, response);
					}
				}
				else
				{
					String cardstr=request.getParameter("cardno");
					String cardname=request.getParameter("cardname");
					String month=request.getParameter("month");
					String year=request.getParameter("year");
					String CVV=request.getParameter("cvv");
					cardto.setCardStr(cardstr);
					cardto.setCardname(cardname);
					try{

						cardto.setMonth(Integer.parseInt(month));
						cardto.setYear(Integer.parseInt(year));
						cardto.setCVV(CVV);
						userto.setPolicyID(request.getParameter("policyid"));
						userto.setAmount(request.getParameter("amount"));
						userto.setModeofpayment(request.getParameter("mode"));
						boolean blank=false;
						try
						{
							blank=cardbo.CardValid(cardto);
							if(blank)
							{
								userto.setPolicyID(request.getParameter("policyid"));
								userto.setAmount(request.getParameter("amount"));
								userto.setModeofpayment(request.getParameter("mode"));
								boolean proceed=cardbo.Store(userto);
								if(proceed)
								{
									ses.removeAttribute("payprevent");
									request.setAttribute("duedate", userto.getDue());
									request.setAttribute("paymentid", userto.getPaymentID());
									rd=request.getRequestDispatcher(success);
									rd.forward(request, response);
								}

							}
						} catch (ValidationException e) {
							request.setAttribute("user", userto);
							request.setAttribute("CardStr", cardto.getCardStr());
							request.setAttribute("cardname", cardto.getCardname());
							request.setAttribute("error", e.getErrormap_pay_card());
							rd=request.getRequestDispatcher(card);
							rd.forward(request, response);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			request.setAttribute("validid", null);
			request.setAttribute("valid", null);
			rd=request.getRequestDispatcher(pay);
			rd.forward(request, response);
		}
	}
}