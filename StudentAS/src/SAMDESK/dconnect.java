package SAMDESK;
import java.sql.*;

import javax.swing.JOptionPane;
public class dconnect {
	   String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
	   String USER="root";
	   String PASS="";
	  public void updateimage(String iquery, byte[] extractimg)
	  {
		  Connection conn = null;
	      PreparedStatement psmnt = null;
		
		  try
		  {
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     conn=DriverManager.getConnection(DB_URL,USER,PASS);
		     psmnt =conn.prepareStatement(iquery);
		     psmnt.setBytes(1, extractimg);
		      int s = psmnt.executeUpdate();
		     if(s>0) {
		    	 System.out.println("Uploaded successfully !");
		    	 }
		    	 else {
		    	 System.out.println("unsucessfull to upload image.");
		    	 }

		     }
		    catch(Exception e)
		    {     
		    	System.out.println(e.toString());
		    	
		    }

	  }
	  
	 public void deldata(String query)
	 {
		 Connection conn = null;
		  Statement stmt = null;
		  try
		  {
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     conn=DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
		     int m =stmt.executeUpdate(query);
		     JOptionPane.showMessageDialog(null,"Record deleted Successfully..");
	  
		     }
		    catch(Exception e)
		    {     
		    	System.out.println(e.toString());
		    
		    	
		    } 
	 }
	  public void savedata(String query)
	  {
		  Connection conn = null;
		  Statement stmt = null;
		  try
		  {
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     conn=DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
		     stmt.executeUpdate(query);
		     JOptionPane.showMessageDialog(null,"Record Sumitted Successfully...");
	  
		     }
		    catch(Exception e)
		    {     
		    	System.out.println(e.toString());
		    
		    	
		    }
	
	
	  }
	
}
