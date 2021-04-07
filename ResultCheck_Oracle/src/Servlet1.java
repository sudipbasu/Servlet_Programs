import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class Servlet1 extends HttpServlet{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String reg;
			reg = request.getParameter("reg_no");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
			PreparedStatement pst = con.prepareStatement("select * from RESULT_SERVLET where REGISTRATION_NUMBER = ?");
			pst.setString(1, reg);
			ResultSet rs = pst.executeQuery();
			out.print("<center>");
			out.print("<table border = '1px' cellpadding='10' cellspacing='0'>");
			out.print("<tr>");
			out.print("<th>NAME</th>");
			out.print("<th>REGISTRATION NUMBER</th>");
			out.print("<th>C PROGRAMMING</th>");
			out.print("<th>DATA STRUCTURE</th>");
			out.print("<th>COA</th>");
			out.print("</tr>");
				while(rs.next()) {
				out.print("<tr>");
				out.print("<td>"+rs.getString(1)+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td>"+rs.getString(5)+"</td>");
				out.print("</tr>");
			}
				
			out.print("</table>");
			out.print("</center>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Main Error");
			System.err.println(e.getLocalizedMessage());
		}
	}

}
