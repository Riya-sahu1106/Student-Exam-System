package Online.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import Online.Dao.ResultDAO;
import Online.model.Result;

@WebServlet("/ViewResultServlet")
public class ViewResultServlet extends HttpServlet {

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

	  HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("currentUser") == null) {
          response.sendRedirect("Login.html");
          return;
      }	 
	 
     ResultDAO dao = new ResultDAO();
     List<Result> resultList = dao.getAllResult();
     
     request.setAttribute("resultList", resultList);
     RequestDispatcher rd = request.getRequestDispatcher("Result.jsp");
     rd.forward(request, response);
     
 }
}
