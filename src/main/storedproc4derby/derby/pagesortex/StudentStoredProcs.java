package derby.pagesortex;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class StudentStoredProcs {

  public static String GET_STUDENTS_QRY="SELECT roll,firstName,lastName,"
                                   +"dob,marks1,marks2,marks3,percent"
                                   +" FROM tbl_students WHERE roll IN (";

  private static Connection getConnection() throws SQLException {
    Connection con=DriverManager.getConnection("jdbc:default:connection");
    con.setAutoCommit(false);
    return con;
  }

  public final static void pageStudents(int pageNum,int maxRows,ResultSet[] rs) throws SQLException {
	int totalRows=-1,pages=-1,searchVal=-1;
	final String countQry="SELECT COUNT(*) FROM tbl_students";
	StringBuffer buff;
	Connection con=null;
	Statement ps=null;
    //rs=null;
	try {
	  con = getConnection();
	  ps=con.createStatement();
	  rs[0]=ps.executeQuery(countQry);
	  /*
	   Calculate pageNum from totalNumofrows with maxRecs
	   fetch next batch of records into resultset as next page
	   using get_students_qry
	  */
	  while(rs[0].next())
		totalRows=rs[0].getInt(1);
	  rs[0].close();
	  ps.close();
      System.out.println("Total Rows:"+totalRows);
	  //calculate pagenum and determine from which to which index
	  //to be searched and prepare the query.
	  pages=totalRows/maxRows;
	  System.out.println("total%maxRows="+(totalRows%maxRows));
	  if(totalRows%maxRows!=0)
		pages+=1;
	  System.out.println("Total pages:"+pages);
	  if(pageNum==1)
		searchVal=1;
	  else
	    searchVal=(pageNum*maxRows)-maxRows+1;
	  buff=new StringBuffer();
	  buff.append(GET_STUDENTS_QRY);
	  for(int i=1;i<=maxRows;i++) {
	    buff.append(searchVal+",");
		System.out.println("searchVal["+i+"]="+searchVal);
		searchVal+=1;
	  }
	  String modQry=buff.toString();
	  modQry=modQry.substring(0,modQry.lastIndexOf(','));
	  buff=new StringBuffer(modQry+")");
      System.out.println("\nSQL Query:\n"+buff.toString());
	  ps=con.createStatement();
	  rs[0]=ps.executeQuery(buff.toString());
	} catch (SQLException sqlex) {
	  System.out.println("Error in SP:"+sqlex.getMessage());
	  throw sqlex;
	} finally {
	  try {
	    if(con != null)
	     con.close();
	  } catch (SQLException sqlex1) {
		sqlex1.printStackTrace();
	  }
	}
  }

}
