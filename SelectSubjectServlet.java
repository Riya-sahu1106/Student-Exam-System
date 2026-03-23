package Online.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/SelectSubjectServlet")
public class SelectSubjectServlet extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

     int subjectId = Integer.parseInt(request.getParameter("subject_id"));

     HttpSession session = request.getSession();
     session.setAttribute("subject_id", subjectId);

     response.sendRedirect("StartTestServlet");
 }
}
