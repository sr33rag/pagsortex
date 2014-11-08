package net.util;

import net.util.Pager;
import net.util.Configuration;
import net.vo.StudentBean;

public class PagingHelper {

  private static PagingHelper helperInstance=null;

  private PagingHelper() {}
  
  public static PagingHelper getInstance() {
    if(helperInstance == null)
      helperInstance = new PagingHelper();
    return helperInstance;
  }
  
  public Pager<StudentBean> getStudentPager(int rexPerPage) {
    return new StudentPager(rexPerPage);
  }
}
