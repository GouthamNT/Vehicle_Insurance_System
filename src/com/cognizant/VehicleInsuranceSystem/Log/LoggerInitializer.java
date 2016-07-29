package com.cognizant.VehicleInsuranceSystem.Log;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Servlet implementation class Loggers
 */
public class LoggerInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoggerInitializer.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void init(final ServletConfig config) throws ServletException {
		final String realPath = config.getServletContext().getRealPath("/");
		//final String log4jFile = realPath+ SuccessConstants.LOG4J_FILE;
		final String log4jFile = realPath+"/WEB-INF/log.properties";
		PropertyConfigurator.configure(log4jFile);
		LOG.info("Application Initialized");
		LOG.info("Real Path....."+realPath);
	}
}

