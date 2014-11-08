package net.vo;

import java.util.Date;
import java.io.Serializable;

public class StudentBean implements Serializable {

  private int roll=0;
  private Date dob=null;
  private String firstName=null,lastName=null;
  private double marks1,marks2,marks3,percent;
  
  public StudentBean(int _roll, Date _dob,String _firstName, String _lastName, double _marks1, double _marks2, double _marks3, double _percent) {
    setRoll(_roll);
    setDob(_dob);
    setFirstName(_firstName);
    setLastName(_lastName);
    setMarks1(_marks1);
    setMarks2(_marks2);
    setMarks3(_marks3);
    setPercent(_percent);
  }
  
  public void setRoll(int _roll) { this.roll = _roll; }
  public int getRoll() { return this.roll; }
  
  public void setDob(Date _dob) { this.dob = _dob; } 
  public Date getDob() { return this.dob; }
  
  public void setFirstName(String _firstName) { this.firstName = _firstName; }
  public String getFirstName() { return this.firstName; }
  
  public void setLastName(String _lastName) { this.lastName = _lastName; }
  public String getLastName() { return this.lastName; }
  
  public void setMarks1(double _marks1) { this.marks1 = _marks1; }
  public double getMarks1() { return this.marks1; }
  
  public void setMarks2(double _marks2) { this.marks2 = _marks2; }
  public double getMarks2() { return this.marks2; }
  
  public void setMarks3(double _marks3) { this.marks3 = _marks3; }
  public double getMarks3() { return this.marks3; }
  
  public void setPercent(double _percent) { this.percent = _percent; }
  public double getPercent() { return this.percent; }
  
  public void calculatePercent() { setPercent(((getMarks1() * getMarks2 () * getMarks3() )/3)); }
  
  public String toString() {
	StringBuilder builder=new StringBuilder("Student[");
	builder.append("roll:"+getRoll()+",");
	builder.append("dob:"+getDob()+",");
	builder.append("firstName:"+getFirstName()+",");
	builder.append("lastName:"+getLastName()+",");
	builder.append("marks1:"+getMarks1()+",");
	builder.append("marks2:"+getMarks2()+",");
	builder.append("marks3:"+getMarks3()+",");
	builder.append("percent:"+getPercent()+"]");
	return builder.toString();
  }
}
