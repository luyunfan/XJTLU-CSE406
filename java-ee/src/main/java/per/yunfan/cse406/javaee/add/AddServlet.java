package per.yunfan.cse406.javaee.add;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        final String errorInfo = "Please check the parameter: add?num1=$value&num2=&value";
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        if (num1 == null || num2 == null) {
            out.println(errorInfo);
            return;
        }
        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            req.setAttribute("num1", n1);
            req.setAttribute("num2", n2);
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            out.println(errorInfo);
            out.println("$value must be number");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = (String) req.getAttribute("from");
        resp.getWriter().println("from = " + from);
        this.doGet(req, resp);
    }


}