package Online.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import Online.Dao.QuestionDAO;
import Online.Dao.ResultDAO;
import Online.model.Question;
import Online.model.Result;
import Online.model.User;

@WebServlet("/SubmitTestServlet")
public class SubmitTestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

  
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        User user = (User) session.getAttribute("currentUser");
      
        String subjectIdStr = request.getParameter("subject_id");
        int subjectId = Integer.parseInt(subjectIdStr);

        QuestionDAO qdao = new QuestionDAO();
        List<Question> questions = qdao.getQuestionsBySubject(subjectId);

        int score = 0;
        for(Question q : questions){
            String ans = request.getParameter("q"+q.getId());
            if(ans!=null && ans.equals(q.getRight_Ans())){
                score++;
            }
        }

        ResultDAO rdao = new ResultDAO();
        rdao.saveResult(user.getId(), subjectId, score);

        response.sendRedirect("StudentResultServlet");
    }
}
