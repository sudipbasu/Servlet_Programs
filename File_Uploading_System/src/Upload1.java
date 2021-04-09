import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class Upload1 extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		MultipartRequest m = new MultipartRequest(request,"H:\\Servlet Projects\\File_Uploading_System\\uploads");
		out.println("<h1>File Uploaded!</h1>");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
