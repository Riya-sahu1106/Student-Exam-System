package Online.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Online.DBConnection;
import Online.model.Subject;

public class SubjectDAO {

   

    public int addSubject(String subjectName) {
           int id=0;
        try {
        	Connection con=DBConnection.getConnection();
            String sql = "INSERT INTO subjects(subject_name) VALUES(?)";
            System.out.println("DB = " + con.getCatalog());
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subjectName);
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);  
            }
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

   
    public List<Subject> getAllSubjects() {

        List<Subject> list = new ArrayList<>();

        try {
        	Connection con=DBConnection.getConnection();
            String sql = "SELECT * FROM subjects";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
