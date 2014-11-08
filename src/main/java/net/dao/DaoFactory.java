package net.dao;

import java.sql.SQLException;
import javax.sql.DataSource;

import net.dao.impl.StudentDaoImpl;

public class DaoFactory {

  private static DataSource dSource=null;
  private static DaoFactory daoFactory=null;
  
  private DaoFactory(DataSource ds) {
	dSource=ds;
  }
  
  public static DaoFactory getFactory(DataSource ds) {
    if(daoFactory==null)
	  daoFactory = new DaoFactory(ds);	  
	return daoFactory;
  }
  
  public StudentDao getStudentDao() {
    return new StudentDaoImpl(dSource);
  }

}
