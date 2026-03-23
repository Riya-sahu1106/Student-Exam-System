package Online.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import Online.DBConnection;
import Online.Dao.UserDAO;
import Online.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Connection con=DBConnection.getConnection();
		UserDAO dao=new UserDAO();
		
		User user=dao.login(email, password);
		
		if(user!=null) {
			HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            if (user.getRole().equals("admin")) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("student.jsp");
            }
        } else {
            response.sendRedirect("Register.html");
        }
		}	
	}
