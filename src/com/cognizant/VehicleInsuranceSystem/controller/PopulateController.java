package com.cognizant.VehicleInsuranceSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.VehicleInsuranceSystem.bo.VehicleBO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;

/**
 * Servlet implementation class PopulateController
 */
public class PopulateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession ses=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopulateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VehicleBO vehiclebo=new VehicleBO();
		RequestDispatcher dispatcher=null;
		List<String> manulist=new ArrayList<String>();
		ses=request.getSession(true);
		String vtype=request.getParameter("manuVal");
		ses.setAttribute("vehtype",vtype);
		if(!vtype.equalsIgnoreCase("Default"))
		{
			System.out.println("vehicle type in pop controller........"+vtype);
			try {
				manulist=vehiclebo.manufacturerdrop(vtype);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("in populate controller................."+manulist);
			if(manulist.size()>0)
			{
				dispatcher=request.getRequestDispatcher("popmanufact.jsp");
				request.setAttribute("manulist", manulist);
				System.out.println("List ="+request.getAttribute("manulist"));
				dispatcher.forward(request,response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher(".jsp");
				request.setAttribute("message", "Register Problem");
				dispatcher.forward(request,response);
			}

		}	


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		VehicleBO vehiclebo=new VehicleBO();

		RequestDispatcher dispatcher=null;
		List<String> modellist=new ArrayList<String>();
		String vtype=(String) ses.getAttribute("vehtype");
		String manufact=request.getParameter("modelVal");
		System.out.println("controller post method called.........."+manufact);
		try {
			modellist=vehiclebo.modeldrop(manufact,vtype);
		} catch (DatabaseOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in populate model controller................."+modellist);
		if(modellist.size()>0)
		{
			dispatcher=request.getRequestDispatcher("popmodelfact.jsp");
			request.setAttribute("mdllist", modellist);
			System.out.println("List ="+request.getAttribute("mdllist"));
			dispatcher.forward(request,response);
		}
		else
		{
			dispatcher=request.getRequestDispatcher(".jsp");
			request.setAttribute("message", "Register Problem");
			dispatcher.forward(request,response);
		}


	}

}
