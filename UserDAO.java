package Online.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Online.DBConnection;
import Online.model.User;

public class UserDAO {

    public User login(String email,String password) {
		try {
			Connection con=DBConnection.getConnection();
			String query="Select * from users where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;	
    }
    
    public void register(User u){
    	  try{
    	   Connection con=DBConnection.getConnection();
    	   PreparedStatement ps=con.prepareStatement(
    	     "insert into users(name,email,password,role) values(?,?,?,?)");
    	   ps.setString(1,u.getName());
    	   ps.setString(2,u.getEmail());
    	   ps.setString(3,u.getPassword());
    	   ps.setString(4,"student");
    	   ps.executeUpdate();
    	   
    	  }catch(Exception e){
    		  e.printStackTrace();
    		  }
    	 }
    
    
}
