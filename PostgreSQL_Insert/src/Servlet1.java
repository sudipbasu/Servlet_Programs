import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class Servlet1 extends HttpServlet{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int roll = Integer.parseInt(request.getParameter("roll"));
			String college=  request.getParameter("college");
			Class.forName("org.postgresql.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:postgresql:SERVLET_DB","postgres","root");
			String sql = "INSERT INTO public.\"Student\"(\"ID\", \"NAME\", \"ROLL\", \"COLLEGE\") VALUES (?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setInt(3, roll);
			pst.setString(4, college);
			if((pst.executeUpdate())>0) {
				out.println("<h1>Data Inserted</h1>");
			}
			else {
				out.println("<h1> Failed to Insert Data</h1>");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			try {
				PrintWriter out = response.getWriter();
				out.println("<h1> Failed to Insert Data</h1>");
				out.print("<br><h1>"+e.getLocalizedMessage()+"</h1>");
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
	}

}
