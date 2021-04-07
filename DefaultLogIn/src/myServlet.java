import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email,password;
		email = request.getParameter("email");
		password = request.getParameter("password");
		if(email.equals("admin@admin.com") && password.equals("admin")) {
			out.println("<h1>Welcome : "+email+"</h1>");
		}
		else
		{
			out.println("<h1>Invalid Password</h1>");
		}
			}
		
		catch(Exception e) {
			e.printStackTrace();
			
			System.err.println(e.getLocalizedMessage());
		}
	}

}
