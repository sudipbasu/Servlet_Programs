import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class Servlet1 extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM ORACLE_DB_STUDENT_DATA_FETCH1");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			out.print("<html>"
					+ "<head>"
					+ "<title>"
					+ "Student Data"
					+ "</title>"
					+ "<meta charset=\"utf-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n" + 
					"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
					"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>"
					+ "</head>"
					+ "<body>"
					+ "<div class='container'>");
			out.print("<h1>Student Data Sheet</h1>");
			out.print("<table class='table table-striped table-bordered table-hover'>");
			out.print("<tr>");
			for(int i=1;i<=rsmd.getColumnCount();i++)
			{
				
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");
				
			}
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
			out.print("</div>"
					+ "</table>"
					+ "</body>");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
