import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.Dao.AdminDao;
@WebServlet("/allocate")
public class Allocate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int reqid=Integer.parseInt(req.getParameter("reqid"));
		int status=Integer.parseInt(req.getParameter("status"));
		AdminDao ad=new AdminDao();
		ad.allocateAsset(reqid, status);
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/adminstatus.jsp");
		rd.forward(req, resp);
	}

}
