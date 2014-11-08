package net.forms;

import java.util.List;
import java.io.Serializable;

import net.vo.StudentBean;

public class StudentForm implements Serializable {

  private List<StudentBean> students=null;
  private int pageNum=0,numOfPages=-1,totalRex=-1,sortBy=1;
  private boolean order=true;

  public StudentForm() {}

  public StudentForm(List<StudentBean> _students) {
    setStudents(_students);
  }

  public void setStudents(List<StudentBean> _students) {
    this.students=_students;
  }

  public List<StudentBean> getStudents() {
    return this.students;
  }

  public void setPageNum(int _pageNum) {
	this.pageNum = _pageNum;
  }

  public int getPageNum() {
	return this.pageNum;
  }

  public void setNumOfPages(int _numOfPages) {
    this.numOfPages = _numOfPages;
  }

  public int getNumOfPages() {
    return this.numOfPages; 
  }
  
  public void setTotalRex(int _totalRex) {
	this.totalRex=_totalRex;
  }
  
  public int getTotalRex() {
	return this.totalRex;
  }

  public boolean isOrder() {
	return this.order;
  }

  public void setSortBy(int _sortBy) {
    this.sortBy=_sortBy;
  }

  public int getSortBy() {
	return this.sortBy;
  }

  public void setOrder(boolean _order) {
	this.order=_order;
  }
}
