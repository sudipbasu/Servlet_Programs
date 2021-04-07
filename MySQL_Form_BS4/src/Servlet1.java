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
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_db","root","");
			PreparedStatement pst = con.prepareStatement("insert into reg_form(FIRST_NAME,LAST_NAME,PHONE_NUMBER,ADDRESS) values (?,?,?,?)");
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, phone);
			pst.setString(4, address);
			int res = pst.executeUpdate();
			if(res>0) {
				out.println("<h1>Done</h1>");
			}else {
				out.println("<h1>Error!</h1>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}
