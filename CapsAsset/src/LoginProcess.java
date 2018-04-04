import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.bean.User_Master;
import com.cg.service.UserValidation;
import com.cg.service.UserValidationImpl;
@WebServlet("/login")
public class LoginProcess extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	PrintWriter out = resp.getWriter();  
	resp.setContentType("text/html");
	String uname=req.getParameter("uname");
	String pwd=req.getParameter("pwd");
	UserValidation uv=new UserValidationImpl();
	User_Master um=uv.validateUser(uname, pwd);
	if(um.getUserType()!=null)
	{
	if(um.getUserType().equalsIgnoreCase("manager"))
	{
		HttpSession session=req.getSession();  
        session.setAttribute("manager",um);  
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/manager.jsp");
		dispatcher.forward(req, resp);
	}
	else if(um.getUserType().equalsIgnoreCase("admin"))
	{
		HttpSession session=req.getSession();  
        session.setAttribute("admin",um);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin.jsp");
		dispatcher.forward(req, resp);
	}
	}
	else
	{
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('Enter correct username/password');");  
		out.println("</script>");
		RequestDispatcher rd=req.getRequestDispatcher("login.html");
		rd.include(req, resp);
	}
}
}
