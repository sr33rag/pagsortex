package net.util;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

import net.vo.StudentBean;

public class StudentSortHelper 
{
  private StudentSortHelper() {}

  public static StudentSortHelper getSortHelper() {
    return new StudentSortHelper();
  }

  public List<StudentBean> sortStudents(List<StudentBean> students,int by,boolean order) {
	Comparator<StudentBean> comparator=null;
	switch(by) {
	  case 8:
	    comparator=getPercentComparator();
		break;
	  case 7:
	    comparator=getMarks3Comparator();
		break;
	  case 6:
	    comparator=getMarks2Comparator();
		break;
	  case 5:
	    comparator=getMarks1Comparator();
		break;
	  case 4:
	    comparator=getDobComparator();
		break;
	  case 3:
	    comparator=getLastNameComparator();
		break;
	  case 2:
	    comparator=getFirstNameComparator();
		break;
	  default:
	  case 1:
	    comparator=getRollComparator();	    
		break;
	}
	//System.out.println("TRACE: order:"+order);
	if(order)
	 Collections.sort(students,Collections.reverseOrder(comparator));		 
	 //Collections.sort(students,comparator);		 
	else 
	 //Collections.sort(students, Collections.reverseOrder(comparator));
	 Collections.sort(students, comparator);
	/*
	System.out.println("\nTRACE: After sorting:\n");
	for(StudentBean stud:students) {
	  System.out.println("\n"+stud);
	}
	System.out.println("TRACE: completed.");
	System.out.println();
	*/
	return students;
  }
  
  //for sorting by rollno
  private Comparator<StudentBean> getRollComparator() {
    Comparator<StudentBean> comparator = new Comparator<StudentBean>() {
	public int compare(StudentBean student1,StudentBean student2) {
	  //System.out.println("\nstudent1:"+student1+"\nstudent2:"+student2);
	  return (student1.getRoll() - student2.getRoll());		  
	}
   };
   return comparator;
  }
  
  private Comparator<StudentBean> getFirstNameComparator() {
    Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	 String s1fname,s2fname;
	 public int compare(StudentBean student1, StudentBean student2) {
	  s1fname=student1.getFirstName();
	  s2fname=student2.getFirstName();
	  return s1fname.compareTo(s2fname);
	 }
    };
    return comparator;
  }
  
  private Comparator<StudentBean> getLastNameComparator() {
    Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  String s1lname,s2lname;
	  public int compare(StudentBean student1, StudentBean student2) {
		s1lname=student1.getLastName();
		s2lname=student2.getLastName();
		return s1lname.compareTo(s2lname);
	  }
    };
    return comparator;
  }
  
  private Comparator<StudentBean> getDobComparator() {
	Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  public int compare(StudentBean student1,StudentBean student2) {		
		return student1.getDob().compareTo(student2.getDob());
	  }
	};
	return comparator;
  }
  
  private Comparator<StudentBean> getMarks1Comparator() {
    Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  double s1marks1,s2marks1;
	  public int compare(StudentBean student1,StudentBean student2) {
		s1marks1=student1.getMarks1();
		s2marks1=student2.getMarks1();
		return new Double(s1marks1-s2marks1).intValue();
	  }
	};
	return comparator;
  }
  
  private Comparator<StudentBean> getMarks2Comparator() {
    Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  double s1marks2,s2marks2;
	  public int compare(StudentBean student1,StudentBean student2) {
		s1marks2=student1.getMarks2();
		s2marks2=student2.getMarks2();
		return new Double(s1marks2-s2marks2).intValue();
	  }
    };
    return comparator;
  }
  
  private Comparator<StudentBean> getMarks3Comparator() {
    Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  double s1marks3,s2marks3;
	  public int compare(StudentBean student1, StudentBean student2) {
		s1marks3=student1.getMarks3();
		s2marks3=student2.getMarks3();
		return new Double(s1marks3-s2marks3).intValue();
	  }
	};
	return comparator;
  }
  
  private Comparator<StudentBean> getPercentComparator() {
	Comparator<StudentBean> comparator=new Comparator<StudentBean>() {
	  double s1percent,s2percent;
	  public int compare(StudentBean student1,StudentBean student2) {		
		s1percent=student1.getPercent();
		s2percent=student2.getPercent();
		return new Double(s1percent-s2percent).intValue();
	  }
	};
	return comparator;
  }
}
