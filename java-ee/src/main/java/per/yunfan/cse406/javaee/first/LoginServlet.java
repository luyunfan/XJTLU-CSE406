package per.yunfan.cse406.javaee.first;

import per.yunfan.cse406.javaee.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        //resp.getWriter()
        //        .println("username = " + username + ", password = " + password);

        if ("tom".equals(username) && "123".equals(password)) {
            User user = new User();
            user.setUsername("tom");
            user.setAge(18);
            user.setCountry("China");
            req.setAttribute("USER", user);
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("failure.html");
        }
    }
}
