package sptest;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.CallableStatement;

public class StudentStoredProcTest {

  void testIt() {    
    Connection conn=null;
    CallableStatement cs=null;
    ResultSet rs=null;
    try {
     Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
     //conn=DriverManager.getConnection("jdbc:derby:D:\\Sree19\\custom\\derbydbs\\pagesortex","user12","34klq*");
     conn=DriverManager.getConnection("jdbc:derby:/home/sree25/derbydbs/pagesortex","user12","34klq*");
     System.out.println("Connection established successfully!");
     cs=conn.prepareCall("{CALL pageStudents(?,?)}");
     cs.setInt(1,3);
     cs.setInt(2,5);	 
     cs.execute();
     System.out.println("TRACE: Inside Test class after going inside Procedure Call");
     rs=cs.getResultSet();
     while(rs.next()) {
       System.out.println("Student Data:["+
         rs.getInt("roll")+","+
         rs.getString("firstName")+","+
         rs.getString("lastName")+","+
         new java.util.Date(rs.getDate("dob").getTime())+","+
         rs.getDouble("marks1")+","+
         rs.getDouble("marks2")+","+
         rs.getDouble("marks3")+","+
         rs.getDouble("percent")+"]"
         );
     }
    } catch (SQLException sqlex) {
      System.err.println("SQL Error:"+sqlex.getMessage());
      sqlex.printStackTrace();
    } catch (Exception ex) {
      System.err.println("Error:"+ex.getMessage());
      ex.printStackTrace();
    } finally {
      try {
        if(rs!=null)
          rs.close();
        if(cs!=null)
          cs.close();
        if(conn!=null)
          conn.close();
      } catch (SQLException ex2) {
        ex2.printStackTrace();
      }
    }
  }

  public static void main(String args[]) {
    StudentStoredProcTest procTest=new StudentStoredProcTest();
    procTest.testIt();
  }

}

