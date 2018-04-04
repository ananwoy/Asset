import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.Dao.AdminDao;
import com.cg.Dao.ManagerDao;
@WebServlet("/managerstatus")
public class ManagerStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		int reqid=Integer.parseInt(req.getParameter("reqid"));
		int reqID=Integer.parseInt(req.getParameter("reqid"));
		ManagerDao md=new ManagerDao();
		String status=md.viewStatus(reqID);
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/managerstatus.jsp");
		rd.include(req, resp);
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Status for: "+reqID+" is: "+status+"');");
		out.println("</script>");
	}
}
