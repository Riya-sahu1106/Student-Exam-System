package Online.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

import Online.Dao.SubjectDAO;

@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

     String subjectName = request.getParameter("subject_name");
     System.out.println("Subject="+subjectName);

     SubjectDAO dao = new SubjectDAO();
     int newSubjectId=dao.addSubject(subjectName);
     
     

     response.sendRedirect("addQuestion.jsp?subject_id"+newSubjectId);
 }
}
