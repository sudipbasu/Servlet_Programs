import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;
public class Servlet1 extends HttpServlet{
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"    <title>Confirmation Page</title>\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css\">\r\n" + 
					"  </head>\r\n" + 
					"  <body>"
					+ "<div class='container'>");
			int employee_id = (int)(Math.random()*(9999-1000+1)+1000);  
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String education = request.getParameter("education");
			String study_area = request.getParameter("study_area");
			String pass_year = request.getParameter("pass_year");
			String address = request.getParameter("address");
			String pin_code = request.getParameter("pin_code");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","SYSTEM");
			String sql = "INSERT INTO EMPLOYEE_FORM_BULMA (EMPLOYEE_ID,	NAME,DOB,GENDER,PHONE_NUMBER,EMAIL_ID,EDUCATION,AREA_OF_STUDY,YEAR_OF_PASSING,ADDRESS,PIN_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, employee_id);
			pst.setString(2, name);
			pst.setString(3, dob);
			pst.setString(4, gender);
			pst.setString(5, phone);
			pst.setString(6, email);
			pst.setString(7, education);
			pst.setString(8, study_area);
			pst.setString(9, pass_year);
			pst.setString(10, address);
			pst.setString(11, pin_code);
			if(pst.executeUpdate()>0) {
				out.print("<div class=\"notification is-success is-light\">\r\n" + 
						"  <button class=\"delete\"></button>\r\n" + 
						" <strong>Data Inserted</strong> \r\n" + 
						" \r\n" + 
						"</div>");
				out.print("<table class='table is-hoverable table is-fullwidth'>"
						+ "<tr>"
						+ "<th>Employee ID</th>"
						+ "<th>Employee Name</th>"
						+ "<th>DOB</th>"
						+ "<th>Gender</th>"
						+ "<th>Phone Number</th>"
						+ "<th>Email ID</th>"
						+ "<th>Education</th>"
						+ "<th>Area Of Study</th>"
						+ "<th>Year Of Passing</th>"
						+ "<th>Address</th>"
						+ "<th>Pin Code</th>"
						+ "</tr>"
						

						
						);
				
				out.print("<tr>");
				out.print("<td>"+employee_id+"</td>");
				out.print("<td>"+name+"</td>");
				out.print("<td>"+dob+"</td>");
				out.print("<td>"+gender+"</td>");
				out.print("<td>"+phone+"</td>");
				out.print("<td>"+email+"</td>");
				out.print("<td>"+education+"</td>");
				out.print("<td>"+study_area+"</td>");
				out.print("<td>"+pass_year+"</td>");
				out.print("<td>"+address+"</td>");
				out.print("<td>"+pin_code+"</td>");
				out.print("</tr>");
				out.print("</table>"
						+ "<button onclick='window.print()' class='button is-primary'>Print Page</button>");
			}
			else {
				out.print("<div class=\"notification is-danger is-light\">\r\n" + 
						"  <button class=\"delete\"></button>\r\n" + 
						" <strong>Data Inserted</strong> \r\n" + 
						" \r\n" + 
						"</div>");
			}
			out.print("</div>"
					+ "</body>"
					+ "</html>"
					+ "<script>document.addEventListener('DOMContentLoaded', () => {\r\n" + 
					"  (document.querySelectorAll('.notification .delete') || []).forEach(($delete) => {\r\n" + 
					"    const $notification = $delete.parentNode;\r\n" + 
					"\r\n" + 
					"    $delete.addEventListener('click', () => {\r\n" + 
					"      $notification.parentNode.removeChild($notification);\r\n" + 
					"    });\r\n" + 
					"  });\r\n" + 
					"});</script>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
