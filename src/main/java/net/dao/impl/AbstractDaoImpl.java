package net.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

import javax.sql.DataSource;

public abstract class AbstractDaoImpl {

  protected int totalRows=-1;
  protected boolean isconnected=false;
  protected String tbl_name=null;
  
  protected ResultSet rs=null;
  protected Statement st=null;
  protected Connection con=null;
  protected PreparedStatement ps=null;
  protected CallableStatement cs=null;

  protected DataSource ds=null;

  AbstractDaoImpl(DataSource _ds) {
    this.ds=_ds;
	try {
	  con=ds.getConnection();
	  con.setAutoCommit(false);
	  isconnected=true;
	} catch (SQLException sqlex) {
	  System.err.println("Error connecting: "+sqlex.getMessage());
	}	
  }
  
  public int calculateTotalRows() {
	if(isconnected) {
	  try {
		 st=con.createStatement();
		 rs=st.executeQuery("SELECT COUNT(*) FROM "+tbl_name);
		 while(rs.next()) 
		   totalRows=rs.getInt(1);		 
	  } catch (SQLException sqlex) {
		System.err.println("Error in calculating max records:"+sqlex.getMessage());
		sqlex.printStackTrace();
	  } finally {
		release();
	  }
    }
    return totalRows;
  }

  protected final void get() {
	if(isconnected) {
      try {
       rs=ps.executeQuery();
	  } catch (SQLException sqlex) {
	   System.out.println("Error in fetching:"+sqlex.getMessage());
       sqlex.printStackTrace();
	  }
	}    
  }

  protected final int set() {
    int affected=0;
	if(isconnected) {
      try {
       affected=ps.executeUpdate();
	  } catch (SQLException sqlex) {		
		System.out.println("Error in save:"+sqlex.getMessage());
        sqlex.printStackTrace();
	  }
	}
    return affected;
  }

  public final void release() {
    try {
      if(rs != null ) {        
        rs.close();
		//System.out.println("ResulSet closed.");
      }
      if(st!=null) {
		st.close();
		//System.out.println("Statement closed.");
	  } 
	  if(ps != null) {        
	    ps.clearWarnings();
	    ps.clearParameters();
	    ps.close();
		//System.out.println("PreparedStatement closed");
      }
	  if(cs != null) {
	    cs.close();
	    //System.out.println("CallableStatement closed");
	  }	       
    } catch (SQLException sqlex) {
      System.err.println("Error in releasing:"+sqlex.getMessage());
      //sqlex.printStackTrace();
    }
  }
  
  public final void releaseConnection() {
	if(isconnected && con != null)
	  try {
	    con.close();
	    //System.out.println("TRACE: Connection closed!");
      } catch(SQLException sqlex) {
		System.err.println("Error in releasing: "+sqlex.getMessage());
	  } 
  }
}
