package Online.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Online.DBConnection;
import Online.Dao.QuestionDAO;
import Online.model.Question;

@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("Submit".equals(action)) {
		Question q = new Question();
		q.setQuestion(request.getParameter("question"));
		q.setA(request.getParameter("a"));
		q.setB(request.getParameter("b"));
		q.setC(request.getParameter("c"));
		q.setD(request.getParameter("d"));
		q.setRight_Ans(request.getParameter("right"));
	    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
	    
	    q.setSubject_id(subjectId);

		QuestionDAO dao = new QuestionDAO();
		dao.addQuestions(q);
		
		response.sendRedirect("addQuestion.jsp");
		}
		 else if ("Finish".equals(action)) {
		        
		        response.sendRedirect("admin.jsp"); 
		    }
	}
}

