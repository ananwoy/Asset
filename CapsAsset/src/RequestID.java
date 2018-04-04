import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.Dao.ManagerDao;
@WebServlet("/request")
public class RequestID extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	PrintWriter out=resp.getWriter();
	int empno=(Integer.parseInt(req.getParameter("empno")));
	String assetname=req.getParameter("type");
	String assetdes[]=req.getParameterValues("assetdes");
	String ades=null;
	for (String string : assetdes) {
		if(string!="")
		{
			ades=string;
		}
	}
	System.out.println("assetname is: "+assetname+" asset des is: "+ades+" empno is: "+empno);
	ManagerDao md=new ManagerDao();
	RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/manager.jsp");
	rd.include(req, resp);
	int reqid=md.requestAsset(empno, assetname, ades);
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('Your request id is "+reqid+"');");  
	out.println("</script>");
}
}
