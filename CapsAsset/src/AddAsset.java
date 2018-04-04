import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.bean.Asset;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
@WebServlet("/addasset")
public class AddAsset extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	Asset a=new Asset();
	int aid=Integer.parseInt(req.getParameter("aid"));
	a.setAssetId(aid);
	String aname=req.getParameter("aname");
	a.setAssetName(aname);
	String ades=req.getParameter("ades");
	a.setAssetDes(ades);
	int aquan=Integer.parseInt(req.getParameter("aquan"));
	a.setQuantity(aquan);
	String status=req.getParameter("status");
	a.setStatus(status);
	AdminService as=new AdminServiceImpl();
	String st=as.addAsset(a);
	RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/admin.jsp");
	rd.include(req, resp);
	out.println("<script type=\"text/javascript\">");
	out.println("alert('"+st+"');");
	out.println("</script>");
	
}
}
