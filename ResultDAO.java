package Online.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Online.DBConnection;
import Online.model.Result;

public class ResultDAO {

   
    public void saveResult(int userId, int subjectId, int score) {

        String sql = "INSERT INTO results(user_id, subject_id, score) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, subjectId);
            ps.setInt(3, score);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
  
    public List<Result> getAllResult(){
    	  System.out.println("Admin getAllResults() called");
        List<Result>list=new ArrayList<>();
        String sql="""
          SELECT u.name, u.email, s.subject_name, r.score
          FROM results r
          JOIN users u ON r.user_id = u.id
          JOIN subjects s ON r.subject_id = s.subject_id
        """;

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
           
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            	Result r=new Result();
                r.setStudentName(rs.getString("name"));
                r.setEmail(rs.getString("email"));
                r.setSubjectName(rs.getString("subject_name"));
                r.setScore(rs.getInt("score"));
                 list.add(r);           
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


public Result getStudentResult(int userId, int subjectId) {

    System.out.println("getStudentResult() called");

    Result r = null;

    String sql = """
        SELECT u.name, u.email, s.subject_name, r.score
        FROM results r
        JOIN users u ON r.user_id = u.id
        JOIN subjects s ON r.subject_id = s.subject_id
        WHERE r.user_id = ? AND r.subject_id = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ps.setInt(2, subjectId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            r = new Result();
            r.setStudentName(rs.getString("name"));
            r.setEmail(rs.getString("email"));
            r.setSubjectName(rs.getString("subject_name"));
            r.setScore(rs.getInt("score"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return r;
}


public List<Result> getResultsByStudentId(int studentId) {

    List<Result> list = new ArrayList<>();

    String sql = """
        SELECT u.name, u.email, s.subject_name, r.score
        FROM results r
        JOIN users u ON r.user_id = u.id
        JOIN subjects s ON r.subject_id = s.subject_id
        WHERE r.user_id = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Result r = new Result();
            r.setStudentName(rs.getString("name"));
            r.setEmail(rs.getString("email"));
            r.setSubjectName(rs.getString("subject_name"));
            r.setScore(rs.getInt("score"));
            list.add(r);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
