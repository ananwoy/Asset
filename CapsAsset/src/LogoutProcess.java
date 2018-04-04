import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutProcess extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("login.html").include(req, resp);
		System.out.println("logged out");
		Cookie loginCookie = null;
    	Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("manager");
			session.removeAttribute("admin");
			session.invalidate();
		}
		
		if(cookies != null){
	    	for(Cookie cookie : cookies){
	    		cookie.setMaxAge(0);
	    		System.out.println(cookie.getName());
	    		}
	    	}
		out.println("<script type=\"text/javascript\">");
		out.println("alert('You have sucessfully logged out');");
		out.println("</script>");
	}
}
