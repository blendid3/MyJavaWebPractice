package smallPracticeModule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "classWriting", urlPatterns = {"/classWriting"})
public class classWriting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        class1 c1 = new class1();
        req.setAttribute("class1", c1);
        req.getRequestDispatcher("classHandler.jsp").forward(req, resp) ;
    }
}
