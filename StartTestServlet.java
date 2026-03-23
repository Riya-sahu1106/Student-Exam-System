package Online.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import Online.Dao.QuestionDAO;
import Online.model.Question;

@WebServlet("/StartTestServlet")
public class StartTestServlet extends HttpServlet {
	
	

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

	 String subId = request.getParameter("subject_id");

     if (subId == null) {
         response.sendRedirect("selectSubject.jsp");
         return;
     }

     int subjectId = Integer.parseInt(subId);
     
     HttpSession session = request.getSession();
     session.setAttribute("subject_id", subjectId);
     
     QuestionDAO dao = new QuestionDAO();
     List<Question> list = dao.getQuestionsBySubject(subjectId);

     request.setAttribute("questions", list);
     RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
     rd.forward(request, response);   
 }
   
}
