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
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.VehicleTO;


/**
 * Servlet implementation class VehicleRegister
 */
public class VehicleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher=null;
		String location=request.getParameter("location");                                            
		request.setAttribute("location",location);
		dispatcher=request.getRequestDispatcher("regstate.jsp");      
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		boolean result=false;
		boolean val=false;
		int year_of_make=0;
		double price=0;
		long cid=0;
		VehicleTO vehicleto=new VehicleTO();
		VehicleBO vehiclebo=new VehicleBO();
		RequestDispatcher rd=null;
		HttpSession ses=request.getSession(false);
		String vehreg=(String)ses.getAttribute("vehreg");
		try
		{

			if(vehreg.equalsIgnoreCase("vehreg"));
			{

				try
				{
					cid=Long.parseLong(request.getParameter("cid"));
				}
				catch(NumberFormatException d)
				{
					cid=5;
					request.setAttribute("err_msg","Customer ID is mandatory");
				}
				String name=request.getParameter("uname");
				String registering_state=request.getParameter("registering_state");
				String vehicle_class=request.getParameter("vehicle_class");
				String vehicle_type=request.getParameter("vehicle_type");
				System.out.println("vehicle type in reg controller....."+vehicle_type);
				String manufacturer=request.getParameter("manufacturer");
				System.out.println("manufacturer in reg controller....."+manufacturer);
				String model=request.getParameter("model");
				String engine_number=request.getParameter("enumber");
				String rc_number=request.getParameter("rcnumber");
				String vehicleaddress=request.getParameter("registering_address");
				try
				{
					year_of_make=Integer.parseInt(request.getParameter("year"));
				}
				catch(NumberFormatException n)
				{
					request.setAttribute("err","Year of make is mandatory");
				}
				String registering_location=request.getParameter("rlocation");
				try
				{
					price=Double.parseDouble(request.getParameter("price"));
				}
				catch(NumberFormatException a)
				{
					request.setAttribute("errr","Price is mandatory");
				}
				String dop=request.getParameter("dop");
				System.out.println("cid "+cid);
				vehicleto.setCid(cid);
				vehicleto.setName(name);
				vehicleto.setRegistering_state(registering_state);
				vehicleto.setVehicle_type(vehicle_type);
				vehicleto.setVehicle_class(vehicle_class);
				vehicleto.setManufacturer(manufacturer);
				vehicleto.setModel(model);
				vehicleto.setEngine_number(engine_number);
				vehicleto.setYear_of_make(year_of_make);
				vehicleto.setRegistering_location(registering_location);
				vehicleto.setPrice(price);
				vehicleto.setDop(dop);
				vehicleto.setRegistering_address(vehicleaddress);
				vehicleto.setRcnumber(rc_number);
				try 
				{
					val=vehiclebo.validate(vehicleto);
					if(val)
					{
						result=vehiclebo.registerVehicle(vehicleto);
						if(result==true)
						{
							List<String> ls=new ArrayList<String>();
							Long c=vehicleto.getCid();
							ls.add(c.toString());
							ls.add(vehicleto.getName());
							ls.add(vehicleto.getRegistering_state());
							ls.add(vehicleto.getVehicle_class());
							ls.add(vehicleto.getVehicle_type());
							ls.add(vehicleto.getManufacturer());
							ls.add(vehicleto.getModel());
							ls.add(vehicleto.getEngine_number());
							Integer y=vehicleto.getYear_of_make();
							ls.add(y.toString());
							ls.add(vehicleto.getRegistering_location());
							Double p=vehicleto.getPrice();
							ls.add(p.toString());
							ls.add(vehicleto.getDop());
							System.out.println(vehicleto.getPolicy_id());
							ls.add(vehicleto.getPolicy_id());
							System.out.println(ls);
							Double patt=vehicleto.getPremium_amount();
							ls.add(patt.toString());
							ls.add(vehicleto.getMaturity_date());
							request.setAttribute("pop",ls);
							ses.removeAttribute("vehreg");
							rd=request.getRequestDispatcher("VehicleRegInsert.jsp");
							rd.forward(request, response);
						}
						else
						{
							rd=request.getRequestDispatcher("VehicleRegistration.jsp");
							request.setAttribute("message", "Problems occurs while registering...");
							rd.forward(request, response);
						}
					}
				}
				catch (ValidationException e) {

					request.setAttribute("vehicle", vehicleto);
					request.setAttribute("error", e.getErrormap());
					rd=request.getRequestDispatcher("VehicleRegistration.jsp");
					rd.forward(request, response);
				}
				catch(DatabaseOperationException e)
				{
					rd=request.getRequestDispatcher("VehicleRegistration.jsp");
					request.setAttribute("message", "Problem occured while registering..........");
					rd.forward(request, response);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("admin caught");
			rd=request.getRequestDispatcher("AdminIndex.jsp");
			rd.forward(request, response);
		}
	}
}

