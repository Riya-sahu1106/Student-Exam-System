package Online.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Online.DBConnection;
import Online.model.Question;

public class QuestionDAO {
    
    
    public boolean addQuestions(Question q) {
    	 boolean f=false;
    	try {
    		Connection con=DBConnection.getConnection();
    		String query = "INSERT INTO questions(question,A,B,C,D,Right_Ans,subject_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, q.getQuestion());
            ps.setString(2, q.getA());
            ps.setString(3, q.getB());
            ps.setString(4, q.getC());
            ps.setString(5, q.getD());
            ps.setString(6, q.getRight_Ans());
            ps.setInt(7, q.getSubject_id());
            int i=ps.executeUpdate();
            if(i>0) {
            	f=true;
            }
			con.close();        
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return f;
		
    }
    
    
    public List<Question>getQuestionsBySubject(int subject_id){
    	List<Question>list=new ArrayList<>();
    	
    	try {
    		Connection con=DBConnection.getConnection();
			String query="Select * from questions where subject_id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, subject_id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Question qr=new Question();
				
				qr.setId(rs.getInt("id"));
				qr.setQuestion(rs.getString("question"));
				qr.setA(rs.getString("A"));
				qr.setB(rs.getString("B"));
				qr.setC(rs.getString("C"));
				qr.setD(rs.getString("D"));
				qr.setRight_Ans(rs.getString("Right_Ans"));
				
				list.add(qr);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    	
    	
    }
}
