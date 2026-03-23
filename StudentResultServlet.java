package Online.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Online.Dao.ResultDAO;
import Online.model.Result;
import Online.model.User;

@WebServlet("/StudentResultServlet")
public class StudentResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        User user = (User) session.getAttribute("currentUser");

        ResultDAO dao = new ResultDAO();

       
        Result result = null;
        List<Result> list = dao.getResultsByStudentId(user.getId());

        if (list != null && !list.isEmpty()) {
            result = list.get(list.size() - 1);
        }

        request.setAttribute("result", result);
        session.setAttribute("result", result);

        
        RequestDispatcher rd =
        	    request.getRequestDispatcher("submitSuccess.jsp");
        	rd.forward(request, response);


    }
}
