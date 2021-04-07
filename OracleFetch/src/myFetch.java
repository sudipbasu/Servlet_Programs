/*
 * Table			Column	Data Type	Length	
 * STUDENT_SERVLET	NAME	Varchar2	100
 * 					ROLL	Number		-
 * 					COLLEGE	Varchar2	600
*/
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class myFetch extends HttpServlet{
	public void service(HttpServletResponse response) {
		try
		{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
		PreparedStatement pst = con.prepareStatement("SELECT * FROM STUDENT_SERVLET");
		ResultSet rs = pst.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int col = rsmd.getColumnCount();
		out.print("<table>");
		out.print("<tr>");
		for(int i = 1;i<=col;i++)
		{
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");
		}
		out.print("</tr>");
		while(rs.next()) {
			out.print("<tr>");
			out.print("<td>"+rs.getString(1)+"</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(2)+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
