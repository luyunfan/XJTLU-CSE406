package per.yunfan.cse406.javaee.cookie;import javax.servlet.annotation.WebServlet;import javax.servlet.http.Cookie;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;@WebServlet("/cookieRead")public class CookieReadServlet extends HttpServlet {    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {        Cookie[] cookies = req.getCookies();		boolean hasFoundCookie = false;        for(Cookie cookie : cookies) {			if("CID".equals(cookie.getName())) {				resp.getWriter().println(cookie.getValue());				hasFoundCookie = true;			}		}		if(!hasFoundCookie) {		    resp.getWriter().println("Do not found any cookie value of 'CID', please visit /cookie page");		}    }    @Override    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {        this.doGet(req, resp);    }}