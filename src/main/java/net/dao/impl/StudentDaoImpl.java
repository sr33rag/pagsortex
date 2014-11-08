package net.dao.impl;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import java.sql.SQLException;
import javax.sql.DataSource;

import net.dao.StudentDao;
import net.vo.StudentBean;

public class StudentDaoImpl extends AbstractDaoImpl implements StudentDao {

  private DataSource ds=null;
  
  public StudentDaoImpl(DataSource _ds) {	 
	super(_ds); 
	tbl_name="tbl_students";
  }
  
  public boolean saveStudent(StudentBean student) {
    boolean saved=false;
    return saved;
  }
  
  public boolean updateStudent(StudentBean student) {
    boolean updated=false;
    return updated;
  }
  
 public List<StudentBean> getStudentsWithinLimits(int pageNum, int limit) {
	int searchVal=-1,pages=-1;
	StringBuilder builder=new StringBuilder("SELECT roll,dob,firstName,lastName,marks1,marks2,marks3,percent FROM "+tbl_name+" WHERE roll IN (");
	List<StudentBean> students=new ArrayList<StudentBean>(); 
	StudentBean student=null;
	try {
	  if(totalRows<=0)
	    totalRows=calculateTotalRows();
	  pages=totalRows/limit;
	  if(totalRows%limit!=0)
	    pages+=1;
	  if(pageNum==1)
	    searchVal=1;
	  else
		searchVal=(limit*pageNum)-limit+1;
	  for(int i=1;i<=limit;i++)
		builder.append("?,");
	  String temp=builder.toString();
	  temp=temp.substring(0,temp.lastIndexOf(','));
	  builder=new StringBuilder(temp);	  
	  builder.append(")");
	  //System.out.println("SQL Query from Dao: \n"+builder.toString());
	  ps=con.prepareStatement(builder.toString());
	  for(int i=1;i<=limit;i++) {
		ps.setInt(i, searchVal);
		searchVal+=1;
	  }
	  get();
	  while(rs.next()) {
		student = new StudentBean(
		       rs.getInt("roll"),new Date(rs.getDate("dob").getTime()),rs.getString("firstName"),
	           rs.getString("lastName"),rs.getDouble("marks1"),rs.getDouble("marks2"),
	           rs.getDouble("marks3"),rs.getDouble("percent"));
		students.add(student);
	  }	
	} catch (SQLException sqlex) {
	  System.out.println("DaoImpl Error: "+sqlex.getMessage());
	  sqlex.printStackTrace();
	} catch(Exception ex) {
	  System.out.println("DaoImpl Error: "+ex.getMessage());
	  ex.printStackTrace();
	} finally {
	  release();
	  releaseConnection();
	}	
	return students;
 }
 
 public List<StudentBean> getStudentsUsingSP(int offset, int limit) {
   List<StudentBean> students=null;
   StudentBean student=null;
   int fetchedRows=-1;
   try {
	 cs=con.prepareCall("CALL pageStudents(?,?);");
	 cs.setInt(1, offset);
	 cs.setInt(2, limit);
	 cs.execute();
	 rs=cs.getResultSet();
	 while(rs.next()) {
		student=new StudentBean(rs.getInt("roll"),
		         new Date(rs.getDate("dob").getTime()),
		         rs.getString("firstName"),
		         rs.getString("lastName"),
		         rs.getDouble("marks1"),
		         rs.getDouble("marks2"),
		         rs.getDouble("marks3"),
		         rs.getDouble("percent"));
		students.add(student);
	 }
   } catch (SQLException sqlex) {
	 sqlex.printStackTrace();
   } finally {
     release();
     releaseConnection();
   }
   return students;
 }
}
