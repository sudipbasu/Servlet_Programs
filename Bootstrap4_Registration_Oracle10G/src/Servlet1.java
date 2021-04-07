import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class Servlet1 extends HttpServlet{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<html>");
			
			out.print("<head>");
			out.print("<title>Ok</title>");
			out.print(" <meta charset=\"utf-8\">");
			out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
			out.print("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
			out.print("  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
					"");
			out.print("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n" + 
					"");
			out.print("</head>");
			out.print("<body>");
			out.print("<div class='container'>");
			String name  = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String dob = request.getParameter("dob");
			Class.forName("oracle.jdbc.driver.OracleDriver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
	String sql = "insert into REG_SERVLET(NAME,EMAIL,PHONE_NUMBER,DOB) values (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, phone);
			pst.setString(4, dob);
			int res = pst.executeUpdate();
			if(res>0) {
				out.print("<div class=\"alert alert-success alert-dismissible fade show\">\r\n" + 
						"    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n" + 
						"    <strong>Success!</strong> Data Inserted.\r\n" + 
						"  </div>");
				out.print("<table class='table table-hover table-bordered table-striped'>");
				out.print("<tr>");
				out.print("<th>Name : </th>");
				out.print("<td>"+name+"</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<th>Email : </th>");
				out.print("<td>"+email+"</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<th>Phone Number : </th>");
				out.print("<td>"+phone+"</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<th>DOB : </th>");
				out.print("<td>"+dob+"</td>");
				out.print("</tr>");
				out.print("</table>");
			}else {
				out.print("<div class=\"alert alert-danger alert-dismissible fade show\">\r\n" + 
						"    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n" + 
						"    <strong>Error!</strong> Failed to insert Data.\r\n" + 
						"  </div>");
			}
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
	}

}
