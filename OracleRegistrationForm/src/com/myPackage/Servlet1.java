package com.myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class Servlet1 extends HttpServlet{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String name= request.getParameter("name");
			int roll = Integer.parseInt(request.getParameter("roll"));
			String college = request.getParameter("college");
			try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //Load the Driver
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
		//Eshtablish the Connection
		PreparedStatement pst = con.prepareStatement("INSERT INTO STUDENT_SERVLET VALUES (?,?,?)"); //Execute the SQL Query
		pst.setString(1, name);
		pst.setInt(2, roll);
		pst.setString(3, college);
		pst.executeUpdate(); //Insert the Data
		out.println("<h1>Data Inserted</h1>");
			out.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		catch(Exception e3) {
			e3.printStackTrace();
		}
	}

}
