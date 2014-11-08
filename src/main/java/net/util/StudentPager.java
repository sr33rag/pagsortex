package net.util;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import net.dao.StudentDao;
import net.vo.StudentBean;

public class StudentPager implements Pager<StudentBean> {

  int numOfPages,rexPerPage=5,count;
  Map<Integer, StudentBean> rexTillNow=null;
  
  public StudentPager(int _rexPerPage) {
	this.rexPerPage=_rexPerPage;
	rexTillNow=new HashMap<>();
  } 
  
  public Collection<StudentBean> getUpToNow() {
	return this.rexTillNow.values();
  }
  
  public Collection<StudentBean> getNext(int currPageNum) {	
	int searchPageNum=-1;
	if(currPageNum>=numOfPages)
	  return null;
	else
	  searchPageNum=currPageNum+1;
    return getPage(searchPageNum);
  }
  
  public Collection<StudentBean> getPrevious(int currPageNum) {
	int searchPageNum=-1;
	if(currPageNum<=1)
	  searchPageNum=1;
	else
	  searchPageNum=currPageNum-1;
    return getPage(searchPageNum);
  }
  
  public Collection<StudentBean> getPage(int index) {
	int startKey=0,till=-1;
	StudentBean student=null;
	List<StudentBean> students=new ArrayList<>();
	if(index>0 || index<numOfPages) {
	  startKey=getStartKey(index);
	  till=rexPerPage+startKey-1;
	  if(till>count)
	    till=count;
	  //System.out.println("startKey:"+startKey);	  
	  //System.out.println("startKey+rexPerPage:"+till);
	  for(int i=startKey;i<=till;i++) {
		student=rexTillNow.get(new Integer(i));
		students.add(student);
	  }	  
	}
	//System.out.println("TRACE: Students size:"+students.size());
    return students;
  }
  
  public void addToCollection(Collection<StudentBean> newRex) {
	for(StudentBean student : newRex) {
	  rexTillNow.put(new Integer(student.getRoll()),student);
	  //System.out.println("Student roll:"+student.getRoll()+" added.");
    }
    calculateNumOfPagesTillNow();
    //System.out.println("TRACE: Student list size:"+rexTillNow.size());
  }
  
  void calculateNumOfPagesTillNow() {
    calculateTotalRowsTillNow();
	numOfPages=count/rexPerPage;
	if(count%rexPerPage!=0)
	  numOfPages+=1;	
  }
  
  void calculateTotalRowsTillNow() {
	count=rexTillNow.size();
  }
  
  public int getNumOfPages() {
	return numOfPages;
  }
  
  public int getTotalRexTillNow() {
	return count;
  }

  int getStartKey(int pNum) {
	 return (pNum*rexPerPage)-rexPerPage+1;
  }
}
