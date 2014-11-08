package net.actions;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vo.StudentBean;
import net.forms.StudentForm;

import net.dao.StudentDao;

import net.util.Pager;
import net.util.StudentSortHelper;
import net.actions.AbstractAction;

public class ViewStudentsAction extends AbstractAction {
  
  int pageNum=1,sort=1,rexPerPage=5;
  String REQ_PAGE_NUM=null,REQ_SORT=null,REX_PER_PAGE=null,PAGER=null,SORTER=null;
  
  Pager studentPager=null;
  StudentSortHelper studentsortHelper=null;
  StudentDao dao=null;
  StudentForm form=null;
  List<StudentBean> students=null;

  public ViewStudentsAction() {
	REQ_PAGE_NUM="page";
	REX_PER_PAGE="rexPerPage";
	PAGER="pager";
	SORTER="sorter";
    url="viewstudents";
  }

  public void preProcess() throws Exception {
	String name=null;
	String[] pageNumStr=null,sortStr=null;
	Enumeration reqParams=null;
	if(sess==null) {
	  sess=req.getSession(true);
	  form=new StudentForm();
	  studentsortHelper=StudentSortHelper.getSortHelper();
	  sess.setAttribute(REX_PER_PAGE,rexPerPage);
	  sess.setAttribute(SORTER,studentsortHelper);
	} else {	  
	  rexPerPage=(int) sess.getAttribute(REX_PER_PAGE);	  
	  studentPager=(Pager) sess.getAttribute(PAGER);
	  studentsortHelper=(StudentSortHelper) sess.getAttribute(SORTER);
	  reqParams=req.getParameterNames();
	  while(reqParams.hasMoreElements()) {
		name=(String)reqParams.nextElement();
		if(name.equals(REQ_PAGE_NUM)) {
		  pageNumStr=req.getParameterValues(REQ_PAGE_NUM);
		  pageNum=Integer.parseInt(pageNumStr[0]);
		} else if(name.equals(REQ_SORT)) {
		  sortStr=req.getParameterValues(REQ_SORT);
		  sort=Integer.parseInt(sortStr[0]);
	    }
	  }
	  form=(StudentForm)sess.getAttribute("form");
	}
  }

  public void processRequest() throws Exception {
	boolean sortOrder=false;
	if(studentPager==null)	
	  studentPager=appConfig.getPagingHelper().getStudentPager(rexPerPage);
	if(studentPager.getNumOfPages()<pageNum) {
      dao=appConfig.getDaoFactory().getStudentDao();
	  students=dao.getStudentsWithinLimits(pageNum, rexPerPage);
	  studentPager.addToCollection(students);
	} else {
		//fetch from already fetched records from paging helper
		students=new ArrayList(studentPager.getPage(pageNum));
	}
	if(sort==form.getSortBy() && form.isOrder()==false)
	  sortOrder=true;
	//System.out.println("TRACE: sortOrder:"+sortOrder);
    students=studentsortHelper.sortStudents(students,sort,sortOrder);
	form.setStudents(students);	
	form.setPageNum(pageNum);
	form.setSortBy(sort);
	form.setOrder(sortOrder);
	form.setTotalRex(students.size());
	form.setNumOfPages(studentPager.getNumOfPages());	
	sess.setAttribute("form",form);
	sess.setAttribute(PAGER,studentPager);
  }

  public void postProcess() throws Exception {
    forward();
  }

}
