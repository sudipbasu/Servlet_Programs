package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class ActionServlet extends HttpServlet {
	
	public void doPost(HttpServletResponse response,HttpServletRequest request) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String email = request.getParameter("email");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_db","root","");
			PreparedStatement pst = con.prepareStatement("insert into form_data(NAME ,ADDRESS,PHONE_NMBER,EMAIL) values (?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setInt(3, phone);
			pst.setString(4, email);
			int i = pst.executeUpdate();
			if(i>0) {
				out.println("<h1>Data Inserted</h1>");
			}else {
				out.println("<h1>Failed to Insert Data</h1>");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
